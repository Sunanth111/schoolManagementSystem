# School Registration Script
# This script will register ABC School with the provided details

$registrationData = @{
    schoolName = "ABC"
    schoolType = "Private"
    email = "abcschools@gmail.com"
    phone = "9988776655"
    address = "MUTTUMON"
    city = "THiRUVALLA"
    state = "Kerala"
    country = "India"
    principalName = "JOE"
    principalEmail = "joe@gmail.com"
    adminFirstName = "ADMINISTRATOR"
    adminLastName = "HEAD"
    adminEmail = "admin@gmail.com"
    adminPassword = "admin"
    adminPhone = ""
}

$body = $registrationData | ConvertTo-Json
$apiUrl = "http://localhost:8080/api/auth/register-school"

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "School Registration Script" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Waiting for backend server to be ready..." -ForegroundColor Yellow

# Wait for backend to be ready (max 2 minutes)
$maxAttempts = 24
$attempt = 0
$backendReady = $false

while ($attempt -lt $maxAttempts -and -not $backendReady) {
    try {
        $testResponse = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/login" -Method Post -Body '{"email":"test","password":"test"}' -ContentType "application/json" -ErrorAction Stop -TimeoutSec 2
        $backendReady = $true
    } catch {
        $attempt++
        Write-Host "Attempt $attempt/$maxAttempts - Backend not ready yet, waiting 5 seconds..." -ForegroundColor Gray
        Start-Sleep -Seconds 5
    }
}

if (-not $backendReady) {
    Write-Host ""
    Write-Host "Backend server is not responding." -ForegroundColor Red
    Write-Host "Please ensure the backend is running on http://localhost:8080" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "To start backend, run:" -ForegroundColor Yellow
    Write-Host "  cd backend" -ForegroundColor White
    Write-Host "  .\mvnw.cmd spring-boot:run" -ForegroundColor White
    exit 1
}

Write-Host "Backend is ready! Registering school..." -ForegroundColor Green
Write-Host ""
Write-Host "School Details:" -ForegroundColor Cyan
Write-Host "  Name: $($registrationData.schoolName)" -ForegroundColor White
Write-Host "  Type: $($registrationData.schoolType)" -ForegroundColor White
Write-Host "  Email: $($registrationData.email)" -ForegroundColor White
Write-Host "  Location: $($registrationData.city), $($registrationData.state), $($registrationData.country)" -ForegroundColor White

try {
    $response = Invoke-RestMethod -Uri $apiUrl -Method Post -Body $body -ContentType "application/json" -ErrorAction Stop
    
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "SCHOOL REGISTERED SUCCESSFULLY!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    
    Write-Host ""
    Write-Host "Registration Details:" -ForegroundColor Cyan
    Write-Host "  School Code: $($response.schoolCode)" -ForegroundColor Yellow
    Write-Host "  School Name: $($response.schoolName)" -ForegroundColor White
    Write-Host "  Message: $($response.message)" -ForegroundColor White
    
    Write-Host ""
    Write-Host "Admin Account Created:" -ForegroundColor Cyan
    Write-Host "  Email: admin@gmail.com" -ForegroundColor Yellow
    Write-Host "  Password: admin" -ForegroundColor Yellow
    Write-Host "  Name: ADMINISTRATOR HEAD" -ForegroundColor White
    Write-Host "  Role: ADMIN" -ForegroundColor White
    
    Write-Host ""
    Write-Host "Login Credentials:" -ForegroundColor Cyan
    Write-Host "  URL: http://localhost:8000" -ForegroundColor White
    Write-Host "  Email: admin@gmail.com" -ForegroundColor Yellow
    Write-Host "  Password: admin" -ForegroundColor Yellow
    
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "You can now login and start using the system!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    
    # Save credentials to file
    $credentials = "School Registration Successful`n==============================`n`nSchool Code: $($response.schoolCode)`nSchool Name: $($response.schoolName)`n`nAdmin Login Credentials:`n------------------------`nEmail: admin@gmail.com`nPassword: admin`n`nLogin URL: http://localhost:8000`n`nSave this information securely!"
    
    $credentials | Out-File -FilePath "school-credentials.txt" -Encoding UTF8
    Write-Host ""
    Write-Host "Credentials saved to: school-credentials.txt" -ForegroundColor Cyan
    
} catch {
    Write-Host ""
    Write-Host "Error registering school:" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host ""
        Write-Host "Response Details:" -ForegroundColor Yellow
        Write-Host $responseBody -ForegroundColor Red
    }
    
    Write-Host ""
    Write-Host "Possible issues:" -ForegroundColor Yellow
    Write-Host "  - School email or admin email may already be registered" -ForegroundColor White
    Write-Host "  - Backend may not be fully started" -ForegroundColor White
    Write-Host "  - Database connection issue" -ForegroundColor White
}
