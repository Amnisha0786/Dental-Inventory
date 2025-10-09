# PowerShell script to test the Dental Inventory API endpoints with authentication
# Run this script from the project root directory

$baseUrl = "http://localhost:8080"

Write-Host "Testing Dental Inventory API Endpoints with Authentication..." -ForegroundColor Green
Write-Host "==========================================" -ForegroundColor Green

# Login to get JWT token
Write-Host "`nAuthenticating..." -ForegroundColor Yellow
$loginBody = @{
    username = "admin"
    password = "hashed_password_here"
} | ConvertTo-Json

try {
    $loginResponse = Invoke-WebRequest -Uri "$baseUrl/api/auth/login" -Method POST -Body $loginBody -ContentType "application/json"
    $loginData = $loginResponse.Content | ConvertFrom-Json
    $token = $loginData.token
    Write-Host "Login successful! Token obtained." -ForegroundColor Green
    $headers = @{
        "Authorization" = "Bearer $token"
        "Content-Type" = "application/json"
    }
} catch {
    Write-Host "Login failed: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "Trying alternative password..." -ForegroundColor Yellow

    # Try with plain password
    $loginBody = @{
        username = "admin"
        password = "admin"
    } | ConvertTo-Json

    try {
        $loginResponse = Invoke-WebRequest -Uri "$baseUrl/api/auth/login" -Method POST -Body $loginBody -ContentType "application/json"
        $loginData = $loginResponse.Content | ConvertFrom-Json
        $token = $loginData.token
        Write-Host "Login successful with alternative password! Token obtained." -ForegroundColor Green
        $headers = @{
            "Authorization" = "Bearer $token"
            "Content-Type" = "application/json"
        }
    } catch {
        Write-Host "Login failed with alternative password: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host "Proceeding without authentication for testing purposes..." -ForegroundColor Yellow
        $headers = @{
            "Content-Type" = "application/json"
        }
    }
}

# Test Core Inventory Management
Write-Host "`n1. Core Inventory Management:" -ForegroundColor Yellow
Write-Host "GET /api/products (List all products)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/products" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $products = $response.Content | ConvertFrom-Json
    Write-Host "Products count: $($products.Count)"
    if ($products.Count -gt 0) {
        Write-Host "Sample product: $($products[0].name)"
    }
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/products/1 (Get specific product)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/products/1" -Method GET -Headers $headers
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
    $response = Invoke-WebRequest -Uri "$baseUrl/api/inventory" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $inventory = $response.Content | ConvertFrom-Json
    Write-Host "Inventory items count: $($inventory.Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/inventory/low-stock (Get items below threshold)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/inventory/low-stock" -Method GET -Headers $headers
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
    $response = Invoke-WebRequest -Uri "$baseUrl/api/stock-movements" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $movements = $response.Content | ConvertFrom-Json
    Write-Host "Stock movements count: $($movements.Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test Categories & Suppliers
Write-Host "`n4. Categories & Suppliers:" -ForegroundColor Yellow
Write-Host "GET /api/categories (Get all categories)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/categories" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $categories = $response.Content | ConvertFrom-Json
    Write-Host "Categories count: $($categories.Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/suppliers (Get all suppliers)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/suppliers" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $suppliers = $response.Content | ConvertFrom-Json
    Write-Host "Suppliers count: $($suppliers.Count)"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test Reports & Analytics
Write-Host "`n5. Reports & Analytics:" -ForegroundColor Yellow
Write-Host "GET /api/reports/usage (Usage analytics)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/reports/usage" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Usage report generated successfully"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/reports/low-stock-summary (Low stock by category)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/reports/low-stock-summary" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Low stock summary generated successfully"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nGET /api/reports/stock-value (Total inventory value)"
try {
    $response = Invoke-WebRequest -Uri "$baseUrl/api/reports/stock-value" -Method GET -Headers $headers
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    Write-Host "Stock value report generated successfully"
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n==========================================" -ForegroundColor Green
Write-Host "API Testing Complete!" -ForegroundColor Green
