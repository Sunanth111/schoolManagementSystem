# School Registration Instructions - ABC School

## Your School Details

**School Information:**
- School Name: ABC
- School Type: Private
- Email: abcschools@gmail.com
- Phone: 9988776655
- Address: MUTTUMON
- City: THiRUVALLA
- State: Kerala
- Country: India
- Principal Name: JOE
- Principal Email: joe@gmail.com

**Admin Account:**
- First Name: ADMINISTRATOR
- Last Name: HEAD
- Email: admin@gmail.com
- Password: admin

## Method 1: Using the Registration Script (Recommended)

1. **Start the Backend Server:**
   ```powershell
   cd backend
   .\mvnw.cmd spring-boot:run
   ```
   Wait for "Started SchoolManagementApplication" message (1-2 minutes)

2. **Run the Registration Script:**
   ```powershell
   cd ..
   .\register-school.ps1
   ```

3. **The script will:**
   - Wait for backend to be ready
   - Register your school
   - Display your school code
   - Save credentials to `school-credentials.txt`

## Method 2: Using the Web Interface

1. **Start Backend:**
   ```powershell
   cd backend
   .\mvnw.cmd spring-boot:run
   ```

2. **Start Frontend:**
   ```powershell
   cd frontend
   python -m http.server 8000
   ```

3. **Open Browser:**
   - Go to: http://localhost:8000
   - Click "Register School" link on login page
   - Fill in the form with your details
   - Submit the form

## Method 3: Using API Directly (Once Backend is Running)

```powershell
$body = @{
    schoolName = "ABC"
    schoolType = "Private"
    email = "abcschools@gmail.com"
    phone = "9988776655"
    address = "MUTTUMON"
    city = "THiRUVALLA"
    state = "Kerala"
    country = "India"
    principalName = "JOE"
    principalEmail = "joe@gmail.com"
    adminFirstName = "ADMINISTRATOR"
    adminLastName = "HEAD"
    adminEmail = "admin@gmail.com"
    adminPassword = "admin"
    adminPhone = ""
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/auth/register-school" -Method Post -Body $body -ContentType "application/json"
```

## After Registration

Once registered, you will receive:
- **School Code**: A unique code for your school (e.g., ABC1234)
- **Admin Account**: Ready to use
  - Email: admin@gmail.com
  - Password: admin

## Login

1. Go to: http://localhost:8000
2. Enter:
   - Email: admin@gmail.com
   - Password: admin
3. Click "Sign In"

## Important Notes

- **Save your School Code** - You'll need it for user signups
- **Change Admin Password** - After first login, change the password
- **Backend must be running** - The registration requires the backend server
- **Database** - Using H2 in-memory (data resets on restart) or MySQL for persistence

## Troubleshooting

**Backend won't start?**
- Check Java version: `java -version` (needs Java 17+)
- Check if port 8080 is available
- Check backend terminal for errors

**Registration fails?**
- Ensure backend is fully started
- Check if email already exists
- Verify database connection
- Check backend logs for errors

---

**Your school registration is ready! Just start the backend and run the script or use the web interface.**

