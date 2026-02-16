# School Management System - Complete Product Documentation

## Table of Contents

1. [Product Overview](#product-overview)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Technology Stack](#technology-stack)
5. [Database Schema](#database-schema)
6. [API Documentation](#api-documentation)
7. [Installation & Setup](#installation--setup)
8. [User Guide](#user-guide)
9. [Security](#security)
10. [Deployment](#deployment)
11. [Troubleshooting](#troubleshooting)
12. [Future Enhancements](#future-enhancements)

---

## Product Overview

### What is School Management System?

School Management System is a comprehensive, multi-tenant web application designed to manage all aspects of school operations. It provides a complete solution for administrators, teachers, students, and parents to interact and manage academic and administrative tasks efficiently.

### Key Highlights

- **Multi-Tenant Architecture**: Support for multiple schools with isolated data
- **Role-Based Access Control**: Different access levels for Admin, Teacher, Student, and Parent
- **Comprehensive Features**: Student management, attendance, grades, fees, timetables, exams, and more
- **Modern UI**: Responsive, user-friendly interface
- **RESTful API**: Well-structured API for integration
- **Secure**: JWT authentication and data encryption

### Target Users

- **School Administrators**: Manage school operations, users, and data
- **Teachers**: Manage classes, attendance, grades, and timetables
- **Students**: View grades, attendance, fees, and timetables
- **Parents**: Monitor their children's academic progress and fees

---

## Features

### 1. School Registration & Multi-Tenancy
- **School Registration**: New schools can register with complete details
- **Unique School Codes**: Automatic generation of unique school identifiers
- **Data Isolation**: Complete data segregation between schools
- **Admin Account Creation**: Automatic admin account creation during registration

### 2. User Management
- **User Signup**: Students, Teachers, and Parents can create accounts
- **Role-Based Access**: Different roles with appropriate permissions
- **Account Linking**: Link user accounts to student/teacher/parent records
- **Profile Management**: Complete user profiles with extended information

### 3. Student Management
- **Student Profiles**: Comprehensive student information
- **Class Assignment**: Assign students to classes and sections
- **Parent Linking**: Link students to parent accounts
- **Medical Information**: Store medical conditions and emergency contacts
- **Transport & Hostel**: Track transportation and hostel assignments
- **Admission Details**: Complete admission information

### 4. Teacher Management
- **Teacher Profiles**: Detailed teacher information
- **Course Assignment**: Assign teachers to courses
- **Employment Details**: Track employment type, salary, contracts
- **Leave Balance**: Monitor teacher leave balances
- **Qualification Tracking**: Store qualifications and specializations

### 5. Parent Management
- **Parent Profiles**: Complete parent information
- **Student Linking**: Link parents to multiple students
- **Relationship Tracking**: Track relationship type (Father, Mother, Guardian)
- **Contact Information**: Multiple contact methods

### 6. Course Management
- **Course Creation**: Create and manage courses/subjects
- **Course Details**: Code, name, description, credits
- **School Association**: Courses linked to schools

### 7. Class Management
- **Class Creation**: Create classes with sections
- **Capacity Management**: Set and track class capacity
- **Room Assignment**: Assign rooms to classes
- **Class Teacher**: Assign class teachers

### 8. Attendance Management
- **Daily Attendance**: Mark attendance for students
- **Multiple Statuses**: Present, Absent, Late, Excused
- **Class-wise Attendance**: Mark attendance for entire class
- **Attendance Reports**: View attendance by student, class, or date
- **Course Attendance**: Track attendance per course

### 9. Grade Management
- **Grade Entry**: Record student grades
- **Automatic Grading**: Automatic grade letter calculation
- **Multiple Exam Types**: Midterm, Final, Quiz, Assignment, Project
- **Grade Reports**: View grades by student or course
- **Performance Tracking**: Monitor student performance

### 10. Fee Management
- **Fee Creation**: Create fee records for students
- **Multiple Fee Types**: Tuition, Library, Lab, Sports, etc.
- **Payment Tracking**: Track partial and full payments
- **Status Management**: Pending, Paid, Partial, Overdue
- **Due Date Tracking**: Monitor payment due dates
- **Fee Reports**: Generate fee reports by student or status

### 11. Notification System
- **Notifications**: Send notifications to users
- **Multiple Types**: Announcements, Exams, Fees, Attendance, Grades, Leave
- **Priority Levels**: Low, Normal, High, Urgent
- **Read/Unread Tracking**: Track notification status
- **School-Wide Announcements**: Broadcast messages to entire school

### 12. Leave Management
- **Leave Requests**: Submit leave requests
- **Multiple Leave Types**: Sick, Casual, Emergency, Vacation, Personal
- **Approval Workflow**: Approve/reject leave requests
- **Automatic Calculation**: Calculate number of leave days
- **Leave History**: Track leave history

### 13. Timetable Management
- **Class Schedules**: Create and manage class timetables
- **Day-wise Organization**: Organize by day of week
- **Time Slots**: Define start and end times
- **Room Assignment**: Assign rooms to classes
- **Teacher Assignment**: Link teachers to time slots

### 14. Exam Management
- **Exam Scheduling**: Create and schedule exams
- **Multiple Exam Types**: Midterm, Final, Quiz, Assignment, Project
- **Exam Details**: Date, time, duration, marks, passing marks
- **Status Tracking**: Scheduled, Ongoing, Completed, Cancelled, Postponed
- **Room Assignment**: Assign exam rooms

### 15. Dashboard & Analytics
- **Statistics Overview**: Real-time statistics
- **Key Metrics**: Students, Teachers, Courses, Classes counts
- **Revenue Tracking**: Total revenue and fee status
- **Quick Access**: Quick links to major features

---

## Architecture

### System Architecture

```
┌─────────────────┐
│   Frontend      │
│  (HTML/CSS/JS)  │
└────────┬────────┘
         │ HTTP/REST
         │
┌────────▼────────┐
│   Backend API   │
│  (Spring Boot)  │
└────────┬────────┘
         │
┌────────▼────────┐
│   Database      │
│ (MySQL/H2/PSQL)│
└─────────────────┘
```

### Component Architecture

**Frontend:**
- Single Page Application (SPA)
- Vanilla JavaScript for logic
- CSS for styling
- Fetch API for HTTP requests

**Backend:**
- Spring Boot REST API
- Spring Security for authentication
- Spring Data JPA for database access
- JWT for token-based authentication

**Database:**
- MySQL/PostgreSQL for production
- H2 for development/testing
- JPA/Hibernate for ORM

### Multi-Tenant Architecture

- Each school has a unique `schoolCode`
- All entities are linked to a `School` entity
- Data is automatically filtered by school
- Complete data isolation between schools

---

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17+
- **Security**: Spring Security
- **Authentication**: JWT (JSON Web Tokens)
- **ORM**: Spring Data JPA / Hibernate
- **Database**: MySQL 8.0+ / PostgreSQL / H2
- **Build Tool**: Maven
- **Password Encryption**: BCrypt

### Frontend
- **HTML5**: Structure
- **CSS3**: Styling with modern features
- **JavaScript**: Vanilla JS (ES6+)
- **Icons**: Font Awesome 6.4.0
- **HTTP Client**: Fetch API

### Development Tools
- **IDE**: IntelliJ IDEA / Eclipse / VS Code
- **Version Control**: Git
- **API Testing**: Postman / cURL

---

## Database Schema

### Entity Relationship Diagram

```
School (1) ──< (Many) User
School (1) ──< (Many) Student
School (1) ──< (Many) Teacher
School (1) ──< (Many) Course
School (1) ──< (Many) SchoolClass

User (1) ── (1) Student
User (1) ── (1) Teacher
User (1) ── (1) Parent

Student (Many) ──< (Many) Parent
Student (Many) ──< (Many) Attendance
Student (Many) ──< (Many) Grade
Student (Many) ──< (Many) Fee

Teacher (Many) ──< (Many) Course
Teacher (Many) ──< (Many) Timetable
Teacher (Many) ──< (Many) Exam

SchoolClass (1) ──< (Many) Student
SchoolClass (1) ──< (Many) Attendance
SchoolClass (1) ──< (Many) Timetable
SchoolClass (1) ──< (Many) Exam

Course (1) ──< (Many) Grade
Course (1) ──< (Many) Attendance
Course (1) ──< (Many) Timetable
Course (1) ──< (Many) Exam

User (1) ──< (Many) Notification (as sender)
User (1) ──< (Many) Notification (as recipient)
User (1) ──< (Many) LeaveRequest (as applicant)
User (1) ──< (Many) LeaveRequest (as approver)
```

### Database Tables

1. **schools** - School information
2. **users** - User accounts and authentication
3. **students** - Student profiles
4. **teachers** - Teacher profiles
5. **parents** - Parent profiles
6. **courses** - Course/subject information
7. **classes** - Class and section information
8. **attendance** - Attendance records
9. **grades** - Student grades
10. **fees** - Fee records and payments
11. **notifications** - Notifications and announcements
12. **leave_requests** - Leave management
13. **timetables** - Class schedules
14. **exams** - Exam scheduling

### Key Relationships

- **One-to-One**: User ↔ Student, User ↔ Teacher, User ↔ Parent
- **Many-to-One**: Student → SchoolClass, Student → School
- **Many-to-Many**: Teacher ↔ Course, Parent ↔ Student
- **One-to-Many**: School → Students, School → Teachers, Class → Students

---

## API Documentation

See [API_DOCUMENTATION.md](./API_DOCUMENTATION.md) for complete API reference.

**Summary:**
- **Total Endpoints**: 63+
- **Authentication**: JWT-based
- **Base URL**: `http://localhost:8080/api`
- **Response Format**: JSON
- **Error Handling**: Standard HTTP status codes

---

## Installation & Setup

### Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use Maven wrapper)
- MySQL 8.0+ / PostgreSQL / H2 (for development)
- Modern web browser

### Backend Setup

1. **Clone Repository**
   ```bash
   git clone https://github.com/Sunanth111/schoolManagementSystem.git
   cd schoolManagementSystem/backend
   ```

2. **Configure Database**
   
   Edit `src/main/resources/application.properties`:
   
   **For MySQL:**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/school_management
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```
   
   **For H2 (Development):**
   ```properties
   spring.datasource.url=jdbc:h2:mem:school_management
   spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   ```

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   # Or use wrapper:
   ./mvnw spring-boot:run
   ```

4. **Verify**
   - Backend runs on `http://localhost:8080`
   - Check logs for "Started SchoolManagementApplication"

### Frontend Setup

1. **Navigate to Frontend**
   ```bash
   cd frontend
   ```

2. **Start Server**
   ```bash
   python -m http.server 8000
   # Or use Node.js:
   npx http-server -p 8000
   ```

3. **Access Application**
   - Open browser: `http://localhost:8000`

### Quick Start Scripts

**Windows:**
```bash
# Start backend
start-backend.bat

# Start frontend
start-frontend.bat

# Start both
start-all.bat
```

---

## User Guide

### For Administrators

#### School Registration
1. Open application
2. Click "Register School"
3. Fill in school details
4. Create admin account
5. Receive school code
6. Login with admin credentials

#### Managing Students
1. Navigate to "Students"
2. Click "Add New"
3. Fill student information
4. Assign to class
5. Save

#### Managing Teachers
1. Navigate to "Teachers"
2. Add teacher details
3. Assign courses
4. Set employment details

#### Managing Fees
1. Navigate to "Fees"
2. Create fee records
3. Record payments
4. Track status

### For Teachers

#### Marking Attendance
1. Navigate to "Attendance"
2. Select class and date
3. Mark each student's status
4. Save attendance

#### Recording Grades
1. Navigate to "Grades"
2. Select student and course
3. Enter marks
4. Grade is auto-calculated
5. Save

#### Viewing Timetable
1. Navigate to "Timetable"
2. View class schedule
3. See assigned courses

### For Students/Parents

#### Viewing Grades
1. Login to account
2. Navigate to "Grades"
3. View all grades

#### Viewing Attendance
1. Navigate to "Attendance"
2. View attendance records

#### Viewing Fees
1. Navigate to "Fees"
2. View fee status
3. See payment history

---

## Security

### Authentication
- **JWT Tokens**: Secure token-based authentication
- **Token Expiry**: 24 hours
- **Password Encryption**: BCrypt hashing
- **Session Management**: Stateless (JWT)

### Authorization
- **Role-Based Access Control**: Different roles have different permissions
- **Roles**: SUPER_ADMIN, ADMIN, TEACHER, STUDENT, PARENT
- **Endpoint Protection**: All endpoints require authentication (except auth endpoints)

### Data Security
- **Password Hashing**: BCrypt with salt
- **SQL Injection Prevention**: JPA/Hibernate parameterized queries
- **XSS Protection**: Input validation and sanitization
- **CORS Configuration**: Restricted origins

### Multi-Tenant Security
- **Data Isolation**: Complete separation between schools
- **School Code Verification**: Required for user signup
- **Automatic Filtering**: Data filtered by school automatically

---

## Deployment

### Production Deployment

#### Backend Deployment

1. **Build JAR**
   ```bash
   mvn clean package
   ```

2. **Run JAR**
   ```bash
   java -jar target/school-management-1.0.0.jar
   ```

3. **Environment Variables**
   ```bash
   export SPRING_DATASOURCE_URL=jdbc:mysql://...
   export SPRING_DATASOURCE_USERNAME=...
   export SPRING_DATASOURCE_PASSWORD=...
   export JWT_SECRET=your-secret-key
   ```

#### Frontend Deployment

1. **Static Hosting**
   - Upload files to web server (Apache, Nginx)
   - Configure CORS on backend for production domain
   - Update API_BASE_URL in `frontend/js/api.js`

2. **CDN Deployment**
   - Upload to CDN (CloudFlare, AWS CloudFront)
   - Configure CORS accordingly

### Docker Deployment (Optional)

**Dockerfile Example:**
```dockerfile
FROM openjdk:17-jdk-slim
COPY backend/target/school-management-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

---

## Troubleshooting

### Common Issues

#### Backend Won't Start
- **Check Java Version**: `java -version` (needs 17+)
- **Check Port**: Ensure port 8080 is available
- **Check Database**: Verify database connection
- **Check Logs**: Review application logs for errors

#### Frontend Can't Connect to Backend
- **Check Backend**: Ensure backend is running
- **Check CORS**: Verify CORS configuration
- **Check URL**: Verify API_BASE_URL in `api.js`
- **Check Browser Console**: Look for CORS errors

#### Database Connection Issues
- **Check Credentials**: Verify username/password
- **Check Database**: Ensure database server is running
- **Check URL**: Verify connection URL format
- **Check Firewall**: Ensure port is accessible

#### Login Not Working
- **Check Credentials**: Verify email/password
- **Check Backend Logs**: Look for authentication errors
- **Check Token**: Verify JWT token in browser storage
- **Check User Status**: Ensure user account is active

### Debug Mode

Enable debug logging in `application.properties`:
```properties
logging.level.com.school=DEBUG
logging.level.org.springframework.security=DEBUG
```

---

## Future Enhancements

### Planned Features
- [ ] Email notifications
- [ ] SMS notifications
- [ ] File uploads (photos, documents)
- [ ] Advanced reporting with charts
- [ ] PDF/Excel export
- [ ] Mobile app (React Native)
- [ ] Online payment integration
- [ ] Calendar integration
- [ ] Event management
- [ ] Library management
- [ ] Transportation management
- [ ] Hostel management
- [ ] Parent-teacher messaging
- [ ] Video conferencing integration
- [ ] Multi-language support
- [ ] Dark mode

### Technical Improvements
- [ ] Unit tests
- [ ] Integration tests
- [ ] API documentation (Swagger/OpenAPI)
- [ ] CI/CD pipeline
- [ ] Docker containerization
- [ ] Kubernetes deployment
- [ ] Monitoring and logging (ELK stack)
- [ ] Caching (Redis)
- [ ] Message queue (RabbitMQ/Kafka)

---

## Support & Contact

### Documentation
- **API Documentation**: See `API_DOCUMENTATION.md`
- **Setup Guide**: See `SETUP.md`
- **Features List**: See `FEATURES.md`
- **Enhanced Features**: See `ENHANCED_FEATURES.md`

### Repository
- **GitHub**: https://github.com/Sunanth111/schoolManagementSystem

### Getting Help
1. Check documentation files
2. Review API documentation
3. Check GitHub issues
4. Review code comments

---

## License

MIT License - See LICENSE file for details

---

## Version History

### Version 1.0.0 (Current)
- Initial release
- Core features implemented
- Multi-tenant support
- Complete CRUD operations
- Authentication and authorization
- Dashboard and analytics

---

**Last Updated**: 2024
**Version**: 1.0.0
**Status**: Production Ready

