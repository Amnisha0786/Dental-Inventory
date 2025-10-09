# PowerShell script to test the Dental Inventory API endpoints
# Run this script from the project root directory

$baseUrl = "http://localhost:8080"

Write-Host "Testing Dental Inventory API Endpoints..." -ForegroundColor Green
Write-Host "==========================================" -ForegroundColor Green

# Test Core Inventory Management
Write-Host "`n1. Core Inventory Management:" -ForegroundColor Yellow
Write-Host "GET /api/products (List all products)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/products" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Products count: $(($response.Content | ConvertFrom-Json).Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/products/1 (Get specific product)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/products/1" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $product = $response.Content | ConvertFrom-Json
    Write-Host "Product: $($product.name)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test Inventory Management
Write-Host "`n2. Inventory Management:" -ForegroundColor Yellow
Write-Host "GET /api/inventory (List all inventory items)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/inventory" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Inventory items count: $(($response.Content | ConvertFrom-Json).Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/inventory/low-stock (Get items below threshold)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/inventory/low-stock" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $lowStock = $response.Content | ConvertFrom-Json
    Write-Host "Low stock items count: $($lowStock.Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test Stock Movements
Write-Host "`n3. Stock Movements:" -ForegroundColor Yellow
Write-Host "GET /api/stock-movements (Get movement history)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/stock-movements" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Stock movements count: $(($response.Content | ConvertFrom-Json).Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test Categories & Suppliers
Write-Host "`n4. Categories & Suppliers:" -ForegroundColor Yellow
Write-Host "GET /api/categories (Get all categories)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/categories" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Categories count: $(($response.Content | ConvertFrom-Json).Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/suppliers (Get all suppliers)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/suppliers" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Suppliers count: $(($response.Content | ConvertFrom-Json).Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test Reports & Analytics
Write-Host "`n5. Reports & Analytics:" -ForegroundColor Yellow
Write-Host "GET /api/reports/usage (Usage analytics)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/reports/usage" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Usage report generated successfully"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/reports/low-stock-summary (Low stock by category)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/reports/low-stock-summary" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Low stock summary generated successfully"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/reports/stock-value (Total inventory value)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/reports/stock-value" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Stock value report generated successfully"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n==========================================" -ForegroundColor Green
Write-Host "API Testing Complete!" -ForegroundColor Green
Write-Host "Note: Authentication endpoints require proper JWT tokens for testing." -ForegroundColor Cyan
