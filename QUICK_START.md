# Quick Start Guide - School Management System

## 🚀 Running the Project

### Prerequisites Check

✅ **Java**: Installed (Java 19 detected)
❌ **Maven**: Not found in PATH

### Option 1: Install Maven (Recommended)

1. **Download Maven:**
   - Visit: https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.x-bin.zip`

2. **Install Maven:**
   - Extract to: `C:\Program Files\Apache\maven`
   - Add to PATH: `C:\Program Files\Apache\maven\bin`

3. **Verify Installation:**
   ```bash
   mvn -version
   ```

### Option 2: Use Maven Wrapper (Easier)

I'll add Maven wrapper to the project so you don't need to install Maven globally.

### Option 3: Use IDE (Easiest)

**IntelliJ IDEA / Eclipse:**
1. Open the `backend` folder as a project
2. Wait for Maven to download dependencies
3. Right-click `SchoolManagementApplication.java`
4. Click "Run" or "Debug"

**VS Code:**
1. Install "Extension Pack for Java"
2. Open `backend` folder
3. Click "Run" button on `SchoolManagementApplication.java`

## 🎯 Current Status

✅ **Frontend Server**: Running on http://localhost:8000
⏳ **Backend Server**: Waiting for Maven to start

## 📝 Manual Steps to Run

### Step 1: Start Backend

**If Maven is installed:**
```bash
cd backend
mvn spring-boot:run
```

**If using IDE:**
- Open `backend` folder
- Run `SchoolManagementApplication.java`

### Step 2: Access Frontend

Frontend is already running! Open your browser:
👉 **http://localhost:8000**

### Step 3: Login

- Email: `admin@school.com`
- Password: `admin123`

## 🔧 Alternative: Use H2 Database (No MySQL needed)

Edit `backend/src/main/resources/application.properties`:

```properties
# Comment MySQL lines (add # at start)
# spring.datasource.url=jdbc:mysql://localhost:3306/school_management
# spring.datasource.username=root
# spring.datasource.password=
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Uncomment H2 lines (remove #)
spring.datasource.url=jdbc:h2:mem:school_management
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

This way you don't need MySQL installed!

## 🎬 Demo Steps

Once backend is running:

1. **Login** → http://localhost:8000
2. **Dashboard** → View statistics
3. **Add Class** → Create "Grade 10-A"
4. **Add Course** → Create "Mathematics"
5. **Add Student** → Create a student
6. **Add Teacher** → Create a teacher
7. **Mark Attendance** → Record attendance
8. **Add Grade** → Record student grades
9. **Create Fee** → Add fee record
10. **View Dashboard** → See updated stats

## ⚡ Quick Fix: Install Maven via Chocolatey

If you have Chocolatey installed:
```bash
choco install maven
```

## 📞 Need Help?

- Check `DEMO_GUIDE.md` for detailed walkthrough
- Check `SETUP.md` for complete setup instructions
- Backend logs will show any errors

---

**Next Step:** Install Maven or use an IDE to run the backend! 🚀

