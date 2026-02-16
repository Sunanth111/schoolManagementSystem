@echo off
echo ========================================
echo School Management System - Starting All Services
echo ========================================
echo.

echo Starting Frontend Server...
start "Frontend Server" cmd /k "cd frontend && python -m http.server 8000"

timeout /t 2 /nobreak >nul

echo Starting Backend Server...
start "Backend Server" cmd /k "cd backend && if exist mvnw.cmd (call mvnw.cmd spring-boot:run) else (mvn spring-boot:run)"

echo.
echo ========================================
echo Services Starting...
echo ========================================
echo Frontend: http://localhost:8000
echo Backend:  http://localhost:8080
echo.
echo Login Credentials:
echo   Email: admin@school.com
echo   Password: admin123
echo.
echo Press any key to open browser...
pause >nul

start http://localhost:8000

