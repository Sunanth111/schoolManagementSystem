# Setup Guide

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or PostgreSQL/H2 for development)
- A modern web browser

## Backend Setup

1. **Navigate to backend directory:**
   ```bash
   cd backend
   ```

2. **Configure Database:**
   
   Edit `src/main/resources/application.properties`:
   
   For MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/school_management?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
   
   For H2 (Development - No setup required):
   ```properties
   spring.datasource.url=jdbc:h2:mem:school_management
   spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   ```

3. **Build and Run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   
   The backend will start on `http://localhost:8080`

4. **Verify:**
   - Check `http://localhost:8080/api/dashboard/stats` (requires authentication)
   - H2 Console (if using H2): `http://localhost:8080/h2-console`

## Frontend Setup

### Option 1: Direct File Access
Simply open `frontend/index.html` in your web browser.

### Option 2: Local Server (Recommended)

**Using Python:**
```bash
cd frontend
python -m http.server 8000
```

**Using Node.js (if you have it):**
```bash
cd frontend
npx http-server -p 8000
```

Then access `http://localhost:8000`

### Option 3: VS Code Live Server
Use the Live Server extension in VS Code.

## Default Login Credentials

- **Admin:**
  - Email: `admin@school.com`
  - Password: `admin123`

- **Teacher:**
  - Email: `teacher@school.com`
  - Password: `teacher123`

## API Endpoints

All endpoints require JWT authentication (except `/api/auth/**`)

### Authentication
- `POST /api/auth/login` - Login

### Students
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student
- `GET /api/students/class/{classId}` - Get students by class

### Teachers
- `GET /api/teachers` - Get all teachers
- `GET /api/teachers/{id}` - Get teacher by ID
- `POST /api/teachers` - Create teacher
- `PUT /api/teachers/{id}` - Update teacher
- `DELETE /api/teachers/{id}` - Delete teacher

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course by ID
- `POST /api/courses` - Create course
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course

### Classes
- `GET /api/classes` - Get all classes
- `GET /api/classes/{id}` - Get class by ID
- `POST /api/classes` - Create class
- `PUT /api/classes/{id}` - Update class
- `DELETE /api/classes/{id}` - Delete class

### Attendance
- `GET /api/attendance` - Get all attendance records
- `POST /api/attendance` - Mark attendance
- `GET /api/attendance/student/{studentId}` - Get attendance by student
- `GET /api/attendance/class/{classId}?date=YYYY-MM-DD` - Get attendance by class and date

### Grades
- `GET /api/grades` - Get all grades
- `POST /api/grades` - Create grade
- `GET /api/grades/student/{studentId}` - Get grades by student
- `GET /api/grades/course/{courseId}` - Get grades by course

### Fees
- `GET /api/fees` - Get all fees
- `POST /api/fees` - Create fee
- `PUT /api/fees/{id}/payment` - Update fee payment
- `GET /api/fees/student/{studentId}` - Get fees by student
- `GET /api/fees/status/{status}` - Get fees by status

### Dashboard
- `GET /api/dashboard/stats` - Get dashboard statistics

## Troubleshooting

### Backend Issues

1. **Port 8080 already in use:**
   - Change port in `application.properties`: `server.port=8081`

2. **Database connection error:**
   - Verify database is running
   - Check credentials in `application.properties`
   - Ensure database exists (or use `createDatabaseIfNotExist=true`)

3. **CORS errors:**
   - Ensure frontend URL is in allowed origins in `SecurityConfig.java`

### Frontend Issues

1. **API calls failing:**
   - Verify backend is running on `http://localhost:8080`
   - Check browser console for errors
   - Verify CORS configuration

2. **Login not working:**
   - Check backend logs
   - Verify default users were created
   - Check JWT token in browser localStorage

## Development Tips

1. Use H2 database for quick development/testing
2. Check browser console and network tab for debugging
3. Backend logs show SQL queries when `spring.jpa.show-sql=true`
4. Use Postman or similar tool to test API endpoints directly

