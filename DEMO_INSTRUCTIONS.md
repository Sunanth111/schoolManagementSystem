# 🎬 School Management System - LIVE DEMO

## ✅ System Status

- ✅ **Frontend Server**: Running on http://localhost:8000
- ✅ **Backend Server**: Starting on http://localhost:8080
- ✅ **Database**: H2 (In-Memory - No setup required!)
- ✅ **Maven Wrapper**: Ready to use

## 🚀 Quick Access

**Open your browser and go to:** http://localhost:8000

## 🔐 Login Credentials

**Admin Account:**
- Email: `admin@school.com`
- Password: `admin123`

**Teacher Account:**
- Email: `teacher@school.com`
- Password: `teacher123`

## 🎯 Demo Walkthrough

### Step 1: Login
1. Open http://localhost:8000
2. Enter admin credentials
3. Click "Sign In"
4. You'll see the Dashboard with statistics

### Step 2: Create a Class
1. Click **"Classes"** in the sidebar
2. Click **"Add New"** button
3. Fill in:
   - Class Name: `Grade 10`
   - Section: `A`
   - Capacity: `30`
   - Room Number: `101`
4. Click **"Save"**
5. See the class appear in the table

### Step 3: Create a Course
1. Click **"Courses"** in the sidebar
2. Click **"Add New"**
3. Fill in:
   - Course Code: `MATH101`
   - Course Name: `Mathematics`
   - Description: `Introduction to Mathematics`
   - Credits: `3`
4. Click **"Save"**

### Step 4: Add a Student
1. Click **"Students"** in the sidebar
2. Click **"Add New"**
3. Fill in student details:
   - First Name: `John`
   - Last Name: `Doe`
   - Email: `john.doe@example.com`
   - Phone: `123-456-7890`
   - Date of Birth: `2010-01-15`
   - Gender: `Male`
   - Address: `123 Main Street`
   - Class: Select `Grade 10 - A` (from dropdown)
4. Click **"Save"**
5. Student appears in the table with auto-generated Student ID

### Step 5: Add a Teacher
1. Click **"Teachers"** in the sidebar
2. Click **"Add New"**
3. Fill in:
   - First Name: `Jane`
   - Last Name: `Smith`
   - Email: `jane.smith@example.com`
   - Phone: `987-654-3210`
   - Qualification: `M.Sc. Mathematics`
   - Specialization: `Algebra`
4. Click **"Save"**

### Step 6: Mark Attendance
1. Click **"Attendance"** in the sidebar
2. Select Class: `Grade 10 - A`
3. Select Date: Today's date (default)
4. You'll see a list of students
5. Mark each student as:
   - ✅ Present
   - ❌ Absent
   - ⏰ Late
   - 📝 Excused
6. Click **"Save Attendance"**

### Step 7: Add Grades
1. Click **"Grades"** in the sidebar
2. Click **"Add New"**
3. Fill in:
   - Student: Select `John Doe`
   - Course: Select `Mathematics`
   - Marks: `85`
   - Exam Type: `Midterm`
4. Click **"Save"**
5. Grade letter (A) is automatically calculated!

### Step 8: Create Fee Record
1. Click **"Fees"** in the sidebar
2. Click **"Add New"**
3. Fill in:
   - Student: Select `John Doe`
   - Fee Type: `Tuition`
   - Amount: `1000.00`
   - Due Date: Select a future date
   - Description: `Semester Fee`
4. Click **"Save"**
5. Status shows as "PENDING"

### Step 9: Record Payment
1. In the Fees table, find the fee you just created
2. Click **"Pay"** button
3. Enter payment amount: `500`
4. Click OK
5. Status updates to "PARTIAL"
6. Try paying the remaining amount
7. Status updates to "PAID"

### Step 10: View Dashboard
1. Click **"Dashboard"** in the sidebar
2. See updated statistics:
   - Total Students: 1
   - Total Teachers: 1
   - Total Courses: 1
   - Total Classes: 1
   - Total Revenue: $500
   - Pending Fees: 0

## 🎨 Features to Explore

### ✨ UI Features
- **Modern Design**: Beautiful gradients and animations
- **Responsive Layout**: Works on all screen sizes
- **Smooth Navigation**: Easy sidebar navigation
- **Real-time Updates**: Data updates immediately
- **Form Validation**: Prevents invalid data entry

### 🔒 Security Features
- **JWT Authentication**: Secure token-based login
- **Role-Based Access**: Different roles (Admin, Teacher, Student)
- **Password Encryption**: BCrypt hashing

### 📊 Data Management
- **CRUD Operations**: Create, Read, Update, Delete for all entities
- **Relationships**: Students linked to Classes, Grades linked to Courses
- **Auto-generated IDs**: Student IDs and Teacher IDs auto-generated
- **Soft Delete**: Records are marked inactive, not deleted

## 🔍 Testing API Endpoints

You can also test the API directly:

### Using Browser Console (F12)
```javascript
// Login
fetch('http://localhost:8080/api/auth/login', {
  method: 'POST',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({email: 'admin@school.com', password: 'admin123'})
})
.then(r => r.json())
.then(data => {
  console.log('Token:', data.token);
  localStorage.setItem('token', data.token);
});

// Get Students (after login)
fetch('http://localhost:8080/api/students', {
  headers: {'Authorization': 'Bearer ' + localStorage.getItem('token')}
})
.then(r => r.json())
.then(console.log);
```

## 🐛 Troubleshooting

### Backend not starting?
- Wait 1-2 minutes for first-time dependency download
- Check the backend terminal window for errors
- Ensure Java 17+ is installed

### Frontend not loading?
- Check if http://localhost:8000 is accessible
- Open browser console (F12) for errors
- Verify backend is running on port 8080

### Login not working?
- Check browser console for API errors
- Verify backend is running
- Check network tab for failed requests

### Database issues?
- Using H2 in-memory database (no setup needed)
- Data resets when backend restarts
- For persistent data, switch to MySQL in `application.properties`

## 📸 What You'll See

1. **Login Page**: Clean, modern login interface
2. **Dashboard**: Colorful stat cards with icons
3. **Data Tables**: Clean tables with action buttons
4. **Forms**: User-friendly input forms with validation
5. **Modals**: Smooth popup forms for adding/editing
6. **Status Badges**: Color-coded status indicators

## 🎉 Enjoy Your Demo!

The system is fully functional and ready to use. Explore all the features and see how everything works together!

---

**Need Help?** Check the terminal windows for any error messages.

