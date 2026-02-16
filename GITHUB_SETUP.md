# GitHub Setup Instructions

## Prerequisites
- Git installed on your system
- GitHub account
- GitHub repository created (or we'll create one)

## Steps to Push to GitHub

### 1. Initialize Git Repository (if not already initialized)

```bash
git init
```

### 2. Add All Files

```bash
git add .
```

### 3. Create Initial Commit

```bash
git commit -m "Initial commit: School Management System with Spring Boot backend and vanilla JS frontend"
```

### 4. Add Remote Repository

Replace `YOUR_USERNAME` and `YOUR_REPO_NAME` with your actual GitHub username and repository name:

```bash
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
```

Or if using SSH:
```bash
git remote add origin git@github.com:YOUR_USERNAME/YOUR_REPO_NAME.git
```

### 5. Push to GitHub

```bash
git branch -M main
git push -u origin main
```

## Project Structure

The project is organized as follows:

```
school-management/
├── backend/                    # Spring Boot Backend
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/school/
│   │       │       ├── config/      # Security, JWT, CORS config
│   │       │       ├── controller/  # REST Controllers
│   │       │       ├── dto/         # Data Transfer Objects
│   │       │       ├── entity/      # JPA Entities
│   │       │       ├── repository/  # JPA Repositories
│   │       │       └── service/     # Business Logic
│   │       └── resources/
│   │           └── application.properties
│   └── pom.xml
├── frontend/                   # Frontend Application
│   ├── css/
│   │   └── style.css
│   ├── js/
│   │   ├── api.js            # API service
│   │   └── app.js             # Application logic
│   └── index.html
├── .gitignore
├── README.md
├── SETUP.md
├── FEATURES.md
└── GITHUB_SETUP.md
```

## Important Notes

1. **Database Configuration**: Update `backend/src/main/resources/application.properties` with your database credentials before pushing to production.

2. **JWT Secret**: Change the JWT secret in `application.properties` for production use.

3. **CORS**: Update CORS allowed origins in `SecurityConfig.java` if deploying to a different domain.

4. **Environment Variables**: Consider using environment variables for sensitive configuration in production.

## Creating a New GitHub Repository

If you haven't created a repository yet:

1. Go to https://github.com/new
2. Repository name: `school-management-system` (or your preferred name)
3. Description: "Comprehensive School Management System with Spring Boot backend and vanilla JavaScript frontend"
4. Choose Public or Private
5. **DO NOT** initialize with README, .gitignore, or license (we already have these)
6. Click "Create repository"
7. Follow steps 4-5 above to push your code

## Branch Strategy (Optional)

For better organization, you can use branches:

```bash
# Create and switch to develop branch
git checkout -b develop

# Make changes and commit
git add .
git commit -m "Your commit message"

# Push develop branch
git push -u origin develop

# Merge to main when ready
git checkout main
git merge develop
git push origin main
```

## Continuous Integration (Optional)

You can add GitHub Actions for CI/CD. Create `.github/workflows/ci.yml`:

```yaml
name: CI

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
    - name: Build with Maven
      run: |
        cd backend
        mvn clean install
```

## License

Consider adding a LICENSE file. Common choices:
- MIT License (permissive)
- Apache License 2.0
- GPL v3 (copyleft)

## Repository Description Template

Use this description for your GitHub repository:

```
A comprehensive School Management System built with Spring Boot (backend) and vanilla JavaScript (frontend). Features include student management, teacher management, course management, attendance tracking, grade management, fee management, and a dashboard with analytics.
```

## Tags/Labels

Consider adding these topics to your repository:
- `spring-boot`
- `java`
- `javascript`
- `html`
- `css`
- `school-management`
- `rest-api`
- `jwt-authentication`
- `mysql`
- `education`

