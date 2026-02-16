# School Management System

A comprehensive school management system built with Spring Boot backend and vanilla JavaScript frontend.

## Features

- **Student Management**: Add, update, delete, and view student records
- **Teacher Management**: Manage teacher profiles and assignments
- **Course Management**: Create and manage courses/subjects
- **Class Management**: Organize students into classes and sections
- **Attendance Tracking**: Record and view student attendance
- **Grade Management**: Record and manage student grades
- **Fee Management**: Track fee payments and dues
- **User Authentication**: Secure login and role-based access
- **Dashboard**: Analytics and overview of school operations
- **Reports**: Generate various reports

## Tech Stack

### Backend
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- MySQL/PostgreSQL (configurable)
- Maven

### Frontend
- HTML5
- CSS3
- Vanilla JavaScript
- Fetch API for REST calls

## Project Structure

```
school-management/
├── backend/                 # Spring Boot application
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       └── resources/
│   └── pom.xml
├── frontend/               # Web application
│   ├── index.html
│   ├── css/
│   ├── js/
│   └── assets/
└── README.md
```

## Setup Instructions

### Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Configure database in `src/main/resources/application.properties`

3. Run the application:
```bash
mvn spring-boot:run
```

Backend will run on `http://localhost:8080`

### Frontend Setup

1. Open `frontend/index.html` in a web browser
2. Or use a local server:
```bash
cd frontend
python -m http.server 8000
```

Then access `http://localhost:8000`

## API Endpoints

- `/api/auth/**` - Authentication endpoints
- `/api/students/**` - Student management
- `/api/teachers/**` - Teacher management
- `/api/courses/**` - Course management
- `/api/classes/**` - Class management
- `/api/attendance/**` - Attendance tracking
- `/api/grades/**` - Grade management
- `/api/fees/**` - Fee management
- `/api/dashboard/**` - Dashboard statistics

## Default Credentials

- Admin: admin@school.com / admin123
- Teacher: teacher@school.com / teacher123

## License

MIT License

