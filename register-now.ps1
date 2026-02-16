# Quick School Registration
$data = '{"schoolName":"ABC","schoolType":"Private","email":"abcschools@gmail.com","phone":"9988776655","address":"MUTTUMON","city":"THiRUVALLA","state":"Kerala","country":"India","principalName":"JOE","principalEmail":"joe@gmail.com","adminFirstName":"ADMINISTRATOR","adminLastName":"HEAD","adminEmail":"admin@gmail.com","adminPassword":"admin","adminPhone":""}'
try {
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/register-school" -Method Post -Body $data -ContentType "application/json"
    Write-Host "`n========================================" -ForegroundColor Green
    Write-Host "SCHOOL REGISTERED SUCCESSFULLY!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "School Code: $($result.schoolCode)" -ForegroundColor Yellow
    Write-Host "School Name: $($result.schoolName)" -ForegroundColor White
    Write-Host "`nAdmin Login:" -ForegroundColor Cyan
    Write-Host "  Email: admin@gmail.com" -ForegroundColor Yellow
    Write-Host "  Password: admin" -ForegroundColor Yellow
    Write-Host "`nLogin at: http://localhost:8000" -ForegroundColor Green
    $result | ConvertTo-Json | Out-File "registration-result.json"
} catch {
    Write-Host "`nError: Backend not ready. Please ensure backend is running." -ForegroundColor Red
    Write-Host "Start backend: cd backend && .\mvnw.cmd spring-boot:run" -ForegroundColor Yellow
}

