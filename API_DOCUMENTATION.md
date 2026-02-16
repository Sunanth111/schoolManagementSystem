# School Management System - API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication

All endpoints (except `/api/auth/**`) require JWT authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

---

## Authentication Endpoints

### 1. Login
**POST** `/api/auth/login`

**Request Body:**
```json
{
  "email": "admin@gmail.com",
  "password": "admin"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "admin@gmail.com",
  "role": "ADMIN",
  "name": "Administrator Head"
}
```

**Status Codes:**
- `200 OK` - Login successful
- `400 Bad Request` - Invalid credentials

---

### 2. Register School
**POST** `/api/auth/register-school`

**Request Body:**
```json
{
  "schoolName": "ABC School",
  "schoolType": "Private",
  "email": "school@example.com",
  "phone": "1234567890",
  "address": "123 Main St",
  "city": "City",
  "state": "State",
  "country": "Country",
  "postalCode": "12345",
  "website": "https://school.com",
  "principalName": "John Doe",
  "principalEmail": "principal@school.com",
  "principalPhone": "0987654321",
  "affiliation": "CBSE",
  "adminFirstName": "Admin",
  "adminLastName": "User",
  "adminEmail": "admin@school.com",
  "adminPassword": "password123",
  "adminPhone": "1234567890"
}
```

**Response:**
```json
{
  "schoolCode": "ABC1234",
  "schoolName": "ABC School",
  "message": "School registered successfully!",
  "adminAccount": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "email": "admin@school.com",
    "role": "ADMIN",
    "name": "Admin User"
  }
}
```

---

### 3. User Signup
**POST** `/api/auth/signup`

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phone": "1234567890",
  "role": "STUDENT",
  "schoolCode": "ABC1234",
  "studentId": "STU12345",
  "teacherId": null,
  "parentId": null
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "user@example.com",
  "role": "STUDENT",
  "name": "John Doe"
}
```

---

## School Management

### 4. Get All Schools
**GET** `/api/schools`

**Response:**
```json
[
  {
    "id": 1,
    "schoolCode": "ABC1234",
    "schoolName": "ABC School",
    "email": "school@example.com",
    "phone": "1234567890",
    "address": "123 Main St",
    "city": "City",
    "state": "State",
    "country": "Country",
    "active": true
  }
]
```

---

### 5. Get School by ID
**GET** `/api/schools/{id}`

**Response:**
```json
{
  "id": 1,
  "schoolCode": "ABC1234",
  "schoolName": "ABC School",
  ...
}
```

---

### 6. Get School by Code
**GET** `/api/schools/code/{code}`

**Response:**
```json
{
  "id": 1,
  "schoolCode": "ABC1234",
  "schoolName": "ABC School",
  ...
}
```

---

### 7. Update School
**PUT** `/api/schools/{id}`

**Request Body:** (Same as School entity fields)

---

## Student Management

### 8. Get All Students
**GET** `/api/students`

**Response:**
```json
[
  {
    "id": 1,
    "studentId": "STU12345",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "1234567890",
    "dateOfBirth": "2010-01-15",
    "gender": "Male",
    "address": "123 Main St",
    "schoolClass": {
      "id": 1,
      "className": "Grade 10",
      "section": "A"
    },
    "active": true
  }
]
```

---

### 9. Get Student by ID
**GET** `/api/students/{id}`

---

### 10. Create Student
**POST** `/api/students`

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "dateOfBirth": "2010-01-15",
  "gender": "Male",
  "address": "123 Main St",
  "schoolClass": {
    "id": 1
  }
}
```

---

### 11. Update Student
**PUT** `/api/students/{id}`

---

### 12. Delete Student
**DELETE** `/api/students/{id}`

**Note:** Soft delete - sets `active` to `false`

---

### 13. Get Students by Class
**GET** `/api/students/class/{classId}`

---

## Teacher Management

### 14. Get All Teachers
**GET** `/api/teachers`

**Response:**
```json
[
  {
    "id": 1,
    "teacherId": "TCH12345",
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane@example.com",
    "phone": "0987654321",
    "qualification": "M.Sc. Mathematics",
    "specialization": "Algebra",
    "active": true
  }
]
```

---

### 15. Get Teacher by ID
**GET** `/api/teachers/{id}`

---

### 16. Create Teacher
**POST** `/api/teachers`

**Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "phone": "0987654321",
  "qualification": "M.Sc. Mathematics",
  "specialization": "Algebra",
  "dateOfBirth": "1985-05-20",
  "gender": "Female",
  "address": "456 Oak St"
}
```

---

### 17. Update Teacher
**PUT** `/api/teachers/{id}`

---

### 18. Delete Teacher
**DELETE** `/api/teachers/{id}`

---

## Course Management

### 19. Get All Courses
**GET** `/api/courses`

**Response:**
```json
[
  {
    "id": 1,
    "courseCode": "MATH101",
    "courseName": "Mathematics",
    "description": "Introduction to Mathematics",
    "credits": 3,
    "active": true
  }
]
```

---

### 20. Get Course by ID
**GET** `/api/courses/{id}`

---

### 21. Create Course
**POST** `/api/courses`

**Request Body:**
```json
{
  "courseCode": "MATH101",
  "courseName": "Mathematics",
  "description": "Introduction to Mathematics",
  "credits": 3
}
```

---

### 22. Update Course
**PUT** `/api/courses/{id}`

---

### 23. Delete Course
**DELETE** `/api/courses/{id}`

---

## Class Management

### 24. Get All Classes
**GET** `/api/classes`

**Response:**
```json
[
  {
    "id": 1,
    "className": "Grade 10",
    "section": "A",
    "capacity": 30,
    "roomNumber": "101",
    "active": true
  }
]
```

---

### 25. Get Class by ID
**GET** `/api/classes/{id}`

---

### 26. Create Class
**POST** `/api/classes`

**Request Body:**
```json
{
  "className": "Grade 10",
  "section": "A",
  "capacity": 30,
  "roomNumber": "101"
}
```

---

### 27. Update Class
**PUT** `/api/classes/{id}`

---

### 28. Delete Class
**DELETE** `/api/classes/{id}`

---

## Attendance Management

### 29. Get All Attendance Records
**GET** `/api/attendance`

---

### 30. Mark Attendance
**POST** `/api/attendance`

**Request Body:**
```json
{
  "student": {
    "id": 1
  },
  "schoolClass": {
    "id": 1
  },
  "course": {
    "id": 1
  },
  "date": "2024-01-15",
  "status": "PRESENT",
  "remarks": "On time"
}
```

**Status Values:** `PRESENT`, `ABSENT`, `LATE`, `EXCUSED`

---

### 31. Get Attendance by Student
**GET** `/api/attendance/student/{studentId}`

---

### 32. Get Attendance by Class and Date
**GET** `/api/attendance/class/{classId}?date=2024-01-15`

---

## Grade Management

### 33. Get All Grades
**GET** `/api/grades`

**Response:**
```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe"
    },
    "course": {
      "id": 1,
      "courseName": "Mathematics"
    },
    "marks": 85.5,
    "grade": "A",
    "examType": "Midterm",
    "examDate": "2024-01-15T10:00:00"
  }
]
```

---

### 34. Create Grade
**POST** `/api/grades`

**Request Body:**
```json
{
  "student": {
    "id": 1
  },
  "course": {
    "id": 1
  },
  "marks": 85.5,
  "examType": "Midterm",
  "examDate": "2024-01-15T10:00:00",
  "remarks": "Good performance"
}
```

**Note:** Grade letter (A+, A, B+, B, C+, C, F) is automatically calculated based on marks.

---

### 35. Get Grades by Student
**GET** `/api/grades/student/{studentId}`

---

### 36. Get Grades by Course
**GET** `/api/grades/course/{courseId}`

---

## Fee Management

### 37. Get All Fees
**GET** `/api/fees`

**Response:**
```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe"
    },
    "amount": 1000.00,
    "paidAmount": 500.00,
    "dueDate": "2024-02-01",
    "paidDate": null,
    "status": "PARTIAL",
    "feeType": "Tuition",
    "description": "Semester Fee"
  }
]
```

**Status Values:** `PENDING`, `PAID`, `PARTIAL`, `OVERDUE`

---

### 38. Create Fee
**POST** `/api/fees`

**Request Body:**
```json
{
  "student": {
    "id": 1
  },
  "amount": 1000.00,
  "dueDate": "2024-02-01",
  "feeType": "Tuition",
  "description": "Semester Fee"
}
```

---

### 39. Update Fee Payment
**PUT** `/api/fees/{id}/payment`

**Request Body:**
```json
{
  "paidAmount": 500.00
}
```

**Note:** Status is automatically updated based on paid amount.

---

### 40. Get Fees by Student
**GET** `/api/fees/student/{studentId}`

---

### 41. Get Fees by Status
**GET** `/api/fees/status/{status}`

**Status Values:** `PENDING`, `PAID`, `PARTIAL`, `OVERDUE`

---

## Parent Management

### 42. Get All Parents
**GET** `/api/parents`

**Response:**
```json
[
  {
    "id": 1,
    "parentId": "PRT12345",
    "firstName": "Robert",
    "lastName": "Doe",
    "email": "robert@example.com",
    "phone": "1112223333",
    "relationship": "Father",
    "students": [
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe"
      }
    ]
  }
]
```

---

### 43. Get Parent by ID
**GET** `/api/parents/{id}`

---

### 44. Create Parent
**POST** `/api/parents`

**Request Body:**
```json
{
  "firstName": "Robert",
  "lastName": "Doe",
  "email": "robert@example.com",
  "phone": "1112223333",
  "relationship": "Father",
  "occupation": "Engineer",
  "address": "123 Main St"
}
```

---

### 45. Update Parent
**PUT** `/api/parents/{id}`

---

### 46. Link Student to Parent
**POST** `/api/parents/{parentId}/link-student`

**Request Body:**
```json
{
  "studentId": 1
}
```

---

### 47. Delete Parent
**DELETE** `/api/parents/{id}`

---

## Notification Management

### 48. Create Notification
**POST** `/api/notifications`

**Request Body:**
```json
{
  "title": "Exam Schedule",
  "message": "Midterm exams will begin next week",
  "type": "EXAM",
  "priority": "HIGH",
  "sender": {
    "id": 1
  },
  "recipient": {
    "id": 2
  },
  "school": {
    "id": 1
  }
}
```

**Type Values:** `ANNOUNCEMENT`, `EXAM`, `FEE`, `ATTENDANCE`, `GRADE`, `LEAVE`, `GENERAL`

**Priority Values:** `LOW`, `NORMAL`, `HIGH`, `URGENT`

---

### 49. Get Notifications by Recipient
**GET** `/api/notifications/recipient/{recipientId}`

---

### 50. Get Unread Notifications
**GET** `/api/notifications/recipient/{recipientId}/unread`

---

### 51. Mark Notification as Read
**PUT** `/api/notifications/{id}/read`

---

## Leave Management

### 52. Create Leave Request
**POST** `/api/leaves`

**Request Body:**
```json
{
  "applicant": {
    "id": 1
  },
  "leaveType": "SICK_LEAVE",
  "startDate": "2024-02-01",
  "endDate": "2024-02-03",
  "reason": "Medical emergency"
}
```

**Leave Types:** `SICK_LEAVE`, `CASUAL_LEAVE`, `EMERGENCY_LEAVE`, `VACATION`, `PERSONAL`, `OTHER`

---

### 53. Get Leave Requests by Applicant
**GET** `/api/leaves/applicant/{applicantId}`

---

### 54. Get Pending Leave Requests
**GET** `/api/leaves/pending`

---

### 55. Approve Leave Request
**PUT** `/api/leaves/{id}/approve`

**Request Body:**
```json
{
  "approvedById": 2
}
```

---

### 56. Reject Leave Request
**PUT** `/api/leaves/{id}/reject`

**Request Body:**
```json
{
  "approvedById": 2,
  "remarks": "Insufficient leave balance"
}
```

---

## Timetable Management

### 57. Create Timetable Entry
**POST** `/api/timetables`

**Request Body:**
```json
{
  "schoolClass": {
    "id": 1
  },
  "course": {
    "id": 1
  },
  "teacher": {
    "id": 1
  },
  "dayOfWeek": "MONDAY",
  "startTime": "09:00:00",
  "endTime": "10:00:00",
  "roomNumber": "101",
  "periodNumber": "1"
}
```

**Day Values:** `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`

---

### 58. Get Timetable by Class
**GET** `/api/timetables/class/{classId}`

---

### 59. Get Timetable by Teacher
**GET** `/api/timetables/teacher/{teacherId}`

---

## Exam Management

### 60. Create Exam
**POST** `/api/exams`

**Request Body:**
```json
{
  "examName": "Midterm Mathematics",
  "course": {
    "id": 1
  },
  "schoolClass": {
    "id": 1
  },
  "examDate": "2024-02-15",
  "startTime": "09:00:00",
  "endTime": "11:00:00",
  "duration": 120,
  "totalMarks": 100,
  "passingMarks": 40,
  "examType": "Midterm",
  "roomNumber": "Hall A",
  "conductedBy": {
    "id": 1
  }
}
```

**Status Values:** `SCHEDULED`, `ONGOING`, `COMPLETED`, `CANCELLED`, `POSTPONED`

---

### 61. Get Exams by Class
**GET** `/api/exams/class/{classId}`

---

### 62. Get Exams by Course
**GET** `/api/exams/course/{courseId}`

---

## Dashboard

### 63. Get Dashboard Statistics
**GET** `/api/dashboard/stats`

**Response:**
```json
{
  "totalStudents": 150,
  "totalTeachers": 25,
  "totalCourses": 15,
  "totalClasses": 10,
  "totalFees": 500,
  "paidFees": 350,
  "pendingFees": 100,
  "overdueFees": 50,
  "totalRevenue": 350000.00
}
```

---

## Error Responses

All endpoints may return the following error responses:

### 400 Bad Request
```json
{
  "message": "Invalid request data"
}
```

### 401 Unauthorized
```json
{
  "message": "Unauthorized - Invalid or missing token"
}
```

### 404 Not Found
```json
{
  "message": "Resource not found"
}
```

### 500 Internal Server Error
```json
{
  "message": "Internal server error"
}
```

---

## Rate Limiting

Currently, there are no rate limits. In production, consider implementing rate limiting.

---

## CORS

CORS is enabled for:
- `http://localhost:8000`
- `http://localhost:3000`
- `http://127.0.0.1:8000`

---

## Notes

1. All dates should be in ISO 8601 format: `YYYY-MM-DD` or `YYYY-MM-DDTHH:mm:ss`
2. All monetary values are in decimal format (e.g., `1000.00`)
3. Soft delete is used - deleted records have `active: false`
4. JWT tokens expire after 24 hours (86400000 ms)
5. Password must be at least 6 characters (enforced by frontend)

---

## Testing with cURL

### Login Example
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@gmail.com","password":"admin"}'
```

### Get Students (with token)
```bash
curl -X GET http://localhost:8080/api/students \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

### Create Student
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "1234567890"
  }'
```

---

**Total API Endpoints: 63+**

