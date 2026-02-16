@echo off
echo ========================================
echo Starting School Management System Backend
echo ========================================
echo.

cd backend

echo Checking for Maven Wrapper...
if exist mvnw.cmd (
    echo Maven Wrapper found! Starting Spring Boot...
    echo This may take a few minutes on first run (downloading dependencies)...
    echo.
    call mvnw.cmd spring-boot:run
) else (
    echo Checking for Maven...
    where mvn >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo Maven found! Starting Spring Boot...
        echo.
        mvn spring-boot:run
    ) else (
        echo.
        echo ERROR: Maven is not installed and wrapper not found!
        echo.
        echo Please install Maven:
        echo 1. Download from: https://maven.apache.org/download.cgi
        echo 2. Extract to C:\Program Files\Apache\maven
        echo 3. Add C:\Program Files\Apache\maven\bin to PATH
        echo.
        echo OR use an IDE (IntelliJ IDEA, Eclipse, VS Code) to run:
        echo    backend\src\main\java\com\school\SchoolManagementApplication.java
        echo.
        pause
    )
)

