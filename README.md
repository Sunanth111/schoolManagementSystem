# School Management System

A comprehensive, production-ready school management system built with Spring Boot backend and vanilla JavaScript frontend. Features multi-tenant support, complete registration system, and extensive management capabilities.

## ✨ Key Features

### Authentication & Registration
- **School Registration**: Multi-tenant school registration with admin account creation
- **Admin Signup**: Create admin accounts during school registration
- **User Signup**: Students, Teachers, and Parents can sign up with school code verification
- **Secure Login**: JWT-based authentication with role-based access control

### Core Management
- **Student Management**: Complete profiles with parent links, medical info, transport, hostel
- **Teacher Management**: Employment details, salary tracking, leave balance
- **Parent Management**: Parent profiles with student linking
- **Course Management**: Create and manage courses/subjects
- **Class Management**: Organize students into classes and sections

### Academic Features
- **Attendance Tracking**: Record and view student attendance
- **Grade Management**: Record and manage student grades with auto-calculation
- **Timetable Management**: Create and manage class schedules
- **Exam Management**: Schedule and manage exams with status tracking

### Administrative Features
- **Fee Management**: Track fee payments and dues with status tracking
- **Notifications & Announcements**: Send notifications with priority levels
- **Leave Management**: Submit, approve, and track leave requests
- **Dashboard**: Real-time analytics and overview
- **Reports**: Generate various reports

### Multi-Tenant Support
- **School Isolation**: Each school has completely isolated data
- **School Code System**: Unique school codes for identification
- **Data Segregation**: Automatic filtering by school

## Tech Stack

### Backend
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- JWT Authentication
- MySQL/PostgreSQL/H2 (configurable)
- Maven

### Frontend
- HTML5
- CSS3
- Vanilla JavaScript
- Fetch API
- Font Awesome Icons

## Project Structure

```
school-management/
├── backend/                 # Spring Boot application
│   ├── src/
│   │   └── main/
│   │       ├── java/com/school/
│   │       │   ├── config/      # Security, JWT, CORS
│   │       │   ├── controller/  # REST Controllers (15+)
│   │       │   ├── dto/         # Data Transfer Objects
│   │       │   ├── entity/      # JPA Entities (14)
│   │       │   ├── repository/  # JPA Repositories (14)
│   │       │   └── service/     # Business Logic (15+)
│   │       └── resources/
│   │           └── application.properties
│   └── pom.xml
├── frontend/               # Web application
│   ├── index.html
│   ├── css/
│   │   └── style.css
│   └── js/
│       ├── api.js
│       └── app.js
└── README.md
```

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+ (or use Maven wrapper)
- MySQL/PostgreSQL (or use H2 for development)

### Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Configure database in `src/main/resources/application.properties`

3. Run the application:
```bash
mvn spring-boot:run
# Or use wrapper:
./mvnw spring-boot:run
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

### Authentication
- `POST /api/auth/login` - Login
- `POST /api/auth/register-school` - Register new school
- `POST /api/auth/signup` - User signup

### Schools
- `GET /api/schools` - Get all schools
- `GET /api/schools/{id}` - Get school by ID
- `GET /api/schools/code/{code}` - Get school by code

### Students (6 endpoints)
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student
- `GET /api/students/class/{classId}` - Get students by class

### Teachers, Courses, Classes, Attendance, Grades, Fees
- Similar CRUD endpoints for all entities

### New Features
- `GET /api/parents` - Get all parents
- `GET /api/notifications/recipient/{id}` - Get notifications
- `GET /api/leaves/pending` - Get pending leave requests
- `GET /api/timetables/class/{id}` - Get class timetable
- `GET /api/exams/class/{id}` - Get exams by class

**Total: 65+ API Endpoints**

## Default Credentials

After first school registration, use the admin account created during registration.

For testing, default users are created:
- Admin: admin@school.com / admin123
- Teacher: teacher@school.com / teacher123

## Features Overview

### 🏫 School Registration
1. Fill in school details
2. Create admin account
3. Receive unique school code
4. Start using the system

### 👤 User Signup
1. Enter school code
2. Select role (Student/Teacher/Parent)
3. Fill in personal details
4. Link to existing records (if applicable)
5. Account created and logged in

### 📊 Dashboard
- Total students, teachers, courses, classes
- Revenue tracking
- Fee status summary
- Real-time statistics

## Database

### Entities (14)
1. User - Authentication and roles
2. School - School registration
3. Student - Student profiles
4. Teacher - Teacher profiles
5. Parent - Parent profiles
6. Course - Course/subject details
7. SchoolClass - Class and section
8. Attendance - Attendance records
9. Grade - Student grades
10. Fee - Fee records
11. Notification - Notifications
12. LeaveRequest - Leave management
13. Timetable - Class schedules
14. Exam - Exam scheduling

## Security

- JWT token authentication
- BCrypt password encryption
- Role-based access control (SUPER_ADMIN, ADMIN, TEACHER, STUDENT, PARENT)
- CORS protection
- SQL injection prevention
- Multi-tenant data isolation

## Documentation

- `SETUP.md` - Detailed setup instructions
- `FEATURES.md` - Complete feature list
- `ENHANCED_FEATURES.md` - New features documentation
- `DEMO_GUIDE.md` - Demo walkthrough
- `GITHUB_SETUP.md` - GitHub deployment guide

## License

MIT License

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and questions, please check the documentation files or create an issue in the repository.

---

**Built with ❤️ using Spring Boot and Vanilla JavaScript**
