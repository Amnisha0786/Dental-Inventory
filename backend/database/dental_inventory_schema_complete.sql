-- Complete Dental Inventory Management System
-- Lightweight Database Schema with All Products

-- Drop existing tables if they exist
DROP TABLE IF EXISTS stock_movements;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

-- Create categories table
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create suppliers table
CREATE TABLE suppliers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create products table
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    category_id INT,
    supplier_id INT,
    unit_cost DECIMAL(8,2) DEFAULT 0.00,
    reorder_frequency ENUM('Weekly', 'Monthly', 'Quarterly', 'One-Time') DEFAULT 'Monthly',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

-- Create inventory table
CREATE TABLE inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    current_stock INT NOT NULL DEFAULT 0,
    min_stock INT NOT NULL DEFAULT 10,
    max_stock INT NOT NULL DEFAULT 100,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Create stock_movements table
CREATE TABLE stock_movements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT NOT NULL,
    movement_type ENUM('IN', 'OUT') NOT NULL,
    notes VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'staff') DEFAULT 'staff',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Simple view for low stock items
CREATE VIEW low_stock_view AS
SELECT 
    p.id,
    p.name,
    c.name as category,
    i.current_stock,
    i.min_stock,
    s.name as supplier,
    p.unit_cost
FROM inventory i
JOIN products p ON i.product_id = p.id
JOIN categories c ON p.category_id = c.id
JOIN suppliers s ON p.supplier_id = s.id
WHERE i.current_stock <= i.min_stock;