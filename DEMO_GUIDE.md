# School Management System - Demo Guide

## 🚀 Quick Start Demo

### Step 1: Start Backend Server

Open a terminal in the `backend` directory and run:

```bash
cd backend
mvn spring-boot:run
```

**Wait for:** "Started SchoolManagementApplication" message
**Backend URL:** http://localhost:8080

**Note:** First run may take 2-3 minutes to download dependencies and compile.

### Step 2: Start Frontend Server

Open a **NEW** terminal in the `frontend` directory and run:

```bash
cd frontend
python -m http.server 8000
```

**Frontend URL:** http://localhost:8000

### Step 3: Access the Application

1. Open your browser and go to: **http://localhost:8000**
2. You'll see the login page

### Step 4: Login Credentials

**Admin Account:**
- Email: `admin@school.com`
- Password: `admin123`

**Teacher Account:**
- Email: `teacher@school.com`
- Password: `teacher123`

## 🎯 Demo Walkthrough

### 1. Login
- Enter admin credentials
- Click "Sign In"
- You'll be redirected to the Dashboard

### 2. Dashboard
- View statistics:
  - Total Students
  - Total Teachers
  - Total Courses
  - Total Classes
  - Total Revenue
  - Pending Fees

### 3. Student Management
- Click "Students" in sidebar
- Click "Add New" button
- Fill in student details:
  - First Name: John
  - Last Name: Doe
  - Email: john.doe@example.com
  - Phone: 123-456-7890
  - Date of Birth: 2010-01-15
  - Gender: Male
  - Address: 123 Main St
  - Class: Select a class (create one first if needed)
- Click "Save"
- View the student in the table
- Try editing or deleting

### 4. Teacher Management
- Click "Teachers" in sidebar
- Click "Add New"
- Fill in teacher details:
  - First Name: Jane
  - Last Name: Smith
  - Email: jane.smith@example.com
  - Phone: 987-654-3210
  - Qualification: M.Sc. Mathematics
  - Specialization: Algebra
- Click "Save"

### 5. Course Management
- Click "Courses" in sidebar
- Click "Add New"
- Fill in course details:
  - Course Code: MATH101
  - Course Name: Mathematics
  - Description: Introduction to Mathematics
  - Credits: 3
- Click "Save"

### 6. Class Management
- Click "Classes" in sidebar
- Click "Add New"
- Fill in class details:
  - Class Name: Grade 10
  - Section: A
  - Capacity: 30
  - Room Number: 101
- Click "Save"

### 7. Attendance Tracking
- Click "Attendance" in sidebar
- Select a Class
- Select Date (defaults to today)
- Mark attendance for each student:
  - Present
  - Absent
  - Late
  - Excused
- Click "Save Attendance"

### 8. Grade Management
- Click "Grades" in sidebar
- Click "Add New"
- Select Student
- Select Course
- Enter Marks: 85
- Select Exam Type: Midterm
- Click "Save"
- Grade will be automatically calculated (A)

### 9. Fee Management
- Click "Fees" in sidebar
- Click "Add New"
- Select Student
- Fee Type: Tuition
- Amount: 1000.00
- Due Date: Select a future date
- Description: Semester Fee
- Click "Save"
- To record payment, click "Pay" button
- Enter payment amount
- Status will update automatically

## 🔍 Testing API Endpoints

You can also test the API directly using:

### Using Browser (GET requests only)
- Dashboard Stats: http://localhost:8080/api/dashboard/stats
  (Requires authentication token)

### Using Postman/curl

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@school.com","password":"admin123"}'
```

**Get Students (with token):**
```bash
curl -X GET http://localhost:8080/api/students \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

## 🐛 Troubleshooting

### Backend won't start
1. **Check Java version:** `java -version` (needs Java 17+)
2. **Check Maven:** `mvn -version`
3. **Database connection:** 
   - If using MySQL, ensure MySQL is running
   - Or switch to H2 in `application.properties`
4. **Port 8080 in use:** Change port in `application.properties`

### Frontend won't load
1. **Check Python:** `python --version`
2. **Port 8000 in use:** Use different port: `python -m http.server 8001`
3. **CORS errors:** Ensure backend is running on port 8080

### Login not working
1. Check browser console for errors
2. Verify backend is running
3. Check network tab for API calls
4. Verify default users were created (check backend logs)

## 📊 Database

### Using H2 (No setup required)
The project is configured to use MySQL by default. To use H2 for quick testing:

Edit `backend/src/main/resources/application.properties`:

```properties
# Comment out MySQL config
# spring.datasource.url=jdbc:mysql://localhost:3306/school_management

# Uncomment H2 config
spring.datasource.url=jdbc:h2:mem:school_management
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Then access H2 console at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:school_management`
- Username: `sa`
- Password: (leave empty)

## 🎬 Demo Script

1. **Login** → Show dashboard statistics
2. **Create a Class** → Grade 10-A
3. **Create a Course** → Mathematics (MATH101)
4. **Add a Student** → Assign to Grade 10-A
5. **Add a Teacher** → Assign Mathematics course
6. **Mark Attendance** → For the student
7. **Add Grade** → Mathematics grade for student
8. **Create Fee** → Tuition fee for student
9. **Record Payment** → Partial payment
10. **View Dashboard** → Updated statistics

## ✨ Features to Highlight

- ✅ Modern, responsive UI
- ✅ Real-time data updates
- ✅ JWT authentication
- ✅ RESTful API
- ✅ Database relationships
- ✅ Form validation
- ✅ Error handling
- ✅ Dashboard analytics

---

**Enjoy your demo!** 🎉

