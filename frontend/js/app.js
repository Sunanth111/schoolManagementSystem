// Application State
let currentPage = 'dashboard';
let currentUser = null;
let students = [];
let teachers = [];
let courses = [];
let classes = [];
let fees = [];

// Initialize App
document.addEventListener('DOMContentLoaded', () => {
    checkAuth();
    setupEventListeners();
});

function checkAuth() {
    const token = localStorage.getItem('token');
    if (token) {
        showApp();
        loadDashboard();
    } else {
        showLogin();
    }
}

function showLogin() {
    document.getElementById('loginPage').classList.add('active');
    document.getElementById('appPage').classList.remove('active');
}

function showApp() {
    document.getElementById('loginPage').classList.remove('active');
    document.getElementById('appPage').classList.add('active');
}

function setupEventListeners() {
    // Login Form
    document.getElementById('loginForm').addEventListener('submit', handleLogin);
    document.getElementById('logoutBtn').addEventListener('click', handleLogout);

    // Navigation
    document.querySelectorAll('.nav-item').forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            const page = item.dataset.page;
            navigateToPage(page);
        });
    });

    // Modal
    document.querySelector('.close').addEventListener('click', closeModal);
    window.addEventListener('click', (e) => {
        if (e.target.classList.contains('modal')) {
            closeModal();
        }
    });
}

async function handleLogin(e) {
    e.preventDefault();
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;
    const errorDiv = document.getElementById('loginError');

    try {
        const response = await api.login(email, password);
        currentUser = response;
        document.getElementById('userName').textContent = response.name;
        showApp();
        navigateToPage('dashboard');
        errorDiv.classList.remove('show');
    } catch (error) {
        errorDiv.textContent = 'Invalid email or password';
        errorDiv.classList.add('show');
    }
}

function handleLogout() {
    api.clearToken();
    currentUser = null;
    showLogin();
}

function navigateToPage(page) {
    currentPage = page;
    
    // Update navigation
    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('active');
        if (item.dataset.page === page) {
            item.classList.add('active');
        }
    });

    // Update page title
    const titles = {
        dashboard: 'Dashboard',
        students: 'Students',
        teachers: 'Teachers',
        courses: 'Courses',
        classes: 'Classes',
        attendance: 'Attendance',
        grades: 'Grades',
        fees: 'Fees'
    };
    document.getElementById('pageTitle').textContent = titles[page] || 'Dashboard';

    // Show/hide add button
    const addBtn = document.getElementById('addBtn');
    if (['students', 'teachers', 'courses', 'classes', 'grades', 'fees'].includes(page)) {
        addBtn.style.display = 'block';
        addBtn.onclick = () => showAddModal(page);
    } else {
        addBtn.style.display = 'none';
    }

    // Show correct page
    document.querySelectorAll('.content-page').forEach(p => p.classList.remove('active'));
    document.getElementById(`${page}Page`).classList.add('active');

    // Load page data
    switch(page) {
        case 'dashboard':
            loadDashboard();
            break;
        case 'students':
            loadStudents();
            break;
        case 'teachers':
            loadTeachers();
            break;
        case 'courses':
            loadCourses();
            break;
        case 'classes':
            loadClasses();
            break;
        case 'attendance':
            loadAttendancePage();
            break;
        case 'grades':
            loadGrades();
            break;
        case 'fees':
            loadFees();
            break;
    }
}

// Dashboard
async function loadDashboard() {
    try {
        const stats = await api.getDashboardStats();
        document.getElementById('statStudents').textContent = stats.totalStudents || 0;
        document.getElementById('statTeachers').textContent = stats.totalTeachers || 0;
        document.getElementById('statCourses').textContent = stats.totalCourses || 0;
        document.getElementById('statClasses').textContent = stats.totalClasses || 0;
        document.getElementById('statRevenue').textContent = `$${formatNumber(stats.totalRevenue || 0)}`;
        document.getElementById('statPendingFees').textContent = stats.pendingFees || 0;
    } catch (error) {
        console.error('Error loading dashboard:', error);
    }
}

// Students
async function loadStudents() {
    try {
        students = await api.getStudents();
        renderStudentsTable();
    } catch (error) {
        console.error('Error loading students:', error);
    }
}

function renderStudentsTable() {
    const tbody = document.getElementById('studentsTableBody');
    tbody.innerHTML = students.map(student => `
        <tr>
            <td>${student.studentId}</td>
            <td>${student.firstName} ${student.lastName}</td>
            <td>${student.email || '-'}</td>
            <td>${student.phone || '-'}</td>
            <td>${student.schoolClass ? student.schoolClass.className : '-'}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-sm btn-primary" onclick="editStudent(${student.id})">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteStudent(${student.id})">Delete</button>
                </div>
            </td>
        </tr>
    `).join('');
}

function showAddModal(type) {
    const modal = document.getElementById('modal');
    const modalTitle = document.getElementById('modalTitle');
    const modalBody = document.getElementById('modalBody');

    modalTitle.textContent = `Add New ${type.charAt(0).toUpperCase() + type.slice(1).slice(0, -1)}`;

    switch(type) {
        case 'students':
            modalBody.innerHTML = getStudentForm();
            break;
        case 'teachers':
            modalBody.innerHTML = getTeacherForm();
            break;
        case 'courses':
            modalBody.innerHTML = getCourseForm();
            break;
        case 'classes':
            modalBody.innerHTML = getClassForm();
            break;
        case 'grades':
            modalBody.innerHTML = getGradeForm();
            break;
        case 'fees':
            modalBody.innerHTML = getFeeForm();
            break;
    }

    modal.classList.add('show');
    setupFormSubmit(type);
}

function getStudentForm() {
    return `
        <form id="studentForm">
            <div class="form-group">
                <label>First Name</label>
                <input type="text" id="studentFirstName" required>
            </div>
            <div class="form-group">
                <label>Last Name</label>
                <input type="text" id="studentLastName" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" id="studentEmail">
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" id="studentPhone">
            </div>
            <div class="form-group">
                <label>Date of Birth</label>
                <input type="date" id="studentDob">
            </div>
            <div class="form-group">
                <label>Gender</label>
                <select id="studentGender">
                    <option value="">Select</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label>Address</label>
                <textarea id="studentAddress"></textarea>
            </div>
            <div class="form-group">
                <label>Class</label>
                <select id="studentClass"></select>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    `;
}

function getTeacherForm() {
    return `
        <form id="teacherForm">
            <div class="form-group">
                <label>First Name</label>
                <input type="text" id="teacherFirstName" required>
            </div>
            <div class="form-group">
                <label>Last Name</label>
                <input type="text" id="teacherLastName" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" id="teacherEmail">
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" id="teacherPhone">
            </div>
            <div class="form-group">
                <label>Qualification</label>
                <input type="text" id="teacherQualification">
            </div>
            <div class="form-group">
                <label>Specialization</label>
                <input type="text" id="teacherSpecialization">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    `;
}

function getCourseForm() {
    return `
        <form id="courseForm">
            <div class="form-group">
                <label>Course Code</label>
                <input type="text" id="courseCode" required>
            </div>
            <div class="form-group">
                <label>Course Name</label>
                <input type="text" id="courseName" required>
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea id="courseDescription"></textarea>
            </div>
            <div class="form-group">
                <label>Credits</label>
                <input type="number" id="courseCredits">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    `;
}

function getClassForm() {
    return `
        <form id="classForm">
            <div class="form-group">
                <label>Class Name</label>
                <input type="text" id="className" required>
            </div>
            <div class="form-group">
                <label>Section</label>
                <input type="text" id="classSection">
            </div>
            <div class="form-group">
                <label>Capacity</label>
                <input type="number" id="classCapacity">
            </div>
            <div class="form-group">
                <label>Room Number</label>
                <input type="text" id="classRoom">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    `;
}

function getGradeForm() {
    return `
        <form id="gradeForm">
            <div class="form-group">
                <label>Student</label>
                <select id="gradeStudent" required></select>
            </div>
            <div class="form-group">
                <label>Course</label>
                <select id="gradeCourse" required></select>
            </div>
            <div class="form-group">
                <label>Marks</label>
                <input type="number" step="0.01" id="gradeMarks" required>
            </div>
            <div class="form-group">
                <label>Exam Type</label>
                <select id="gradeExamType">
                    <option value="Midterm">Midterm</option>
                    <option value="Final">Final</option>
                    <option value="Quiz">Quiz</option>
                    <option value="Assignment">Assignment</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    `;
}

function getFeeForm() {
    return `
        <form id="feeForm">
            <div class="form-group">
                <label>Student</label>
                <select id="feeStudent" required></select>
            </div>
            <div class="form-group">
                <label>Fee Type</label>
                <select id="feeType" required>
                    <option value="Tuition">Tuition</option>
                    <option value="Library">Library</option>
                    <option value="Lab">Lab</option>
                    <option value="Sports">Sports</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label>Amount</label>
                <input type="number" step="0.01" id="feeAmount" required>
            </div>
            <div class="form-group">
                <label>Due Date</label>
                <input type="date" id="feeDueDate" required>
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea id="feeDescription"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    `;
}

function setupFormSubmit(type) {
    setTimeout(() => {
        const form = document.getElementById(`${type.slice(0, -1)}Form`);
        if (form) {
            form.addEventListener('submit', async (e) => {
                e.preventDefault();
                await handleFormSubmit(type);
            });
        }

        // Load dropdowns
        if (type === 'students' || type === 'grades' || type === 'fees') {
            loadClassesForSelect();
        }
        if (type === 'grades' || type === 'fees') {
            loadStudentsForSelect();
        }
        if (type === 'grades') {
            loadCoursesForSelect();
        }
    }, 100);
}

async function handleFormSubmit(type) {
    try {
        let data = {};
        
        switch(type) {
            case 'students':
                data = {
                    firstName: document.getElementById('studentFirstName').value,
                    lastName: document.getElementById('studentLastName').value,
                    email: document.getElementById('studentEmail').value,
                    phone: document.getElementById('studentPhone').value,
                    dateOfBirth: document.getElementById('studentDob').value,
                    gender: document.getElementById('studentGender').value,
                    address: document.getElementById('studentAddress').value,
                    schoolClass: { id: parseInt(document.getElementById('studentClass').value) }
                };
                await api.createStudent(data);
                break;
            case 'teachers':
                data = {
                    firstName: document.getElementById('teacherFirstName').value,
                    lastName: document.getElementById('teacherLastName').value,
                    email: document.getElementById('teacherEmail').value,
                    phone: document.getElementById('teacherPhone').value,
                    qualification: document.getElementById('teacherQualification').value,
                    specialization: document.getElementById('teacherSpecialization').value
                };
                await api.createTeacher(data);
                break;
            case 'courses':
                data = {
                    courseCode: document.getElementById('courseCode').value,
                    courseName: document.getElementById('courseName').value,
                    description: document.getElementById('courseDescription').value,
                    credits: parseInt(document.getElementById('courseCredits').value) || 0
                };
                await api.createCourse(data);
                break;
            case 'classes':
                data = {
                    className: document.getElementById('className').value,
                    section: document.getElementById('classSection').value,
                    capacity: parseInt(document.getElementById('classCapacity').value) || 0,
                    roomNumber: document.getElementById('classRoom').value
                };
                await api.createClass(data);
                break;
            case 'grades':
                data = {
                    student: { id: parseInt(document.getElementById('gradeStudent').value) },
                    course: { id: parseInt(document.getElementById('gradeCourse').value) },
                    marks: parseFloat(document.getElementById('gradeMarks').value),
                    examType: document.getElementById('gradeExamType').value
                };
                await api.createGrade(data);
                break;
            case 'fees':
                data = {
                    student: { id: parseInt(document.getElementById('feeStudent').value) },
                    feeType: document.getElementById('feeType').value,
                    amount: parseFloat(document.getElementById('feeAmount').value),
                    dueDate: document.getElementById('feeDueDate').value,
                    description: document.getElementById('feeDescription').value
                };
                await api.createFee(data);
                break;
        }

        closeModal();
        navigateToPage(type);
    } catch (error) {
        alert('Error saving: ' + error.message);
    }
}

async function loadClassesForSelect() {
    try {
        classes = await api.getClasses();
        const select = document.getElementById('studentClass');
        if (select) {
            select.innerHTML = '<option value="">Select Class</option>' + 
                classes.map(c => `<option value="${c.id}">${c.className} ${c.section || ''}</option>`).join('');
        }
    } catch (error) {
        console.error('Error loading classes:', error);
    }
}

async function loadStudentsForSelect() {
    try {
        students = await api.getStudents();
        const gradeSelect = document.getElementById('gradeStudent');
        const feeSelect = document.getElementById('feeStudent');
        
        const options = '<option value="">Select Student</option>' + 
            students.map(s => `<option value="${s.id}">${s.firstName} ${s.lastName} (${s.studentId})</option>`).join('');
        
        if (gradeSelect) gradeSelect.innerHTML = options;
        if (feeSelect) feeSelect.innerHTML = options;
    } catch (error) {
        console.error('Error loading students:', error);
    }
}

async function loadCoursesForSelect() {
    try {
        courses = await api.getCourses();
        const select = document.getElementById('gradeCourse');
        if (select) {
            select.innerHTML = '<option value="">Select Course</option>' + 
                courses.map(c => `<option value="${c.id}">${c.courseName} (${c.courseCode})</option>`).join('');
        }
    } catch (error) {
        console.error('Error loading courses:', error);
    }
}

// Teachers
async function loadTeachers() {
    try {
        teachers = await api.getTeachers();
        renderTeachersTable();
    } catch (error) {
        console.error('Error loading teachers:', error);
    }
}

function renderTeachersTable() {
    const tbody = document.getElementById('teachersTableBody');
    tbody.innerHTML = teachers.map(teacher => `
        <tr>
            <td>${teacher.teacherId}</td>
            <td>${teacher.firstName} ${teacher.lastName}</td>
            <td>${teacher.email || '-'}</td>
            <td>${teacher.phone || '-'}</td>
            <td>${teacher.qualification || '-'}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-sm btn-primary" onclick="editTeacher(${teacher.id})">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteTeacher(${teacher.id})">Delete</button>
                </div>
            </td>
        </tr>
    `).join('');
}

// Courses
async function loadCourses() {
    try {
        courses = await api.getCourses();
        renderCoursesTable();
    } catch (error) {
        console.error('Error loading courses:', error);
    }
}

function renderCoursesTable() {
    const tbody = document.getElementById('coursesTableBody');
    tbody.innerHTML = courses.map(course => `
        <tr>
            <td>${course.courseCode}</td>
            <td>${course.courseName}</td>
            <td>${course.description || '-'}</td>
            <td>${course.credits || '-'}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-sm btn-primary" onclick="editCourse(${course.id})">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteCourse(${course.id})">Delete</button>
                </div>
            </td>
        </tr>
    `).join('');
}

// Classes
async function loadClasses() {
    try {
        classes = await api.getClasses();
        renderClassesTable();
    } catch (error) {
        console.error('Error loading classes:', error);
    }
}

function renderClassesTable() {
    const tbody = document.getElementById('classesTableBody');
    tbody.innerHTML = classes.map(cls => `
        <tr>
            <td>${cls.className}</td>
            <td>${cls.section || '-'}</td>
            <td>${cls.capacity || '-'}</td>
            <td>${cls.roomNumber || '-'}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-sm btn-primary" onclick="editClass(${cls.id})">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteClass(${cls.id})">Delete</button>
                </div>
            </td>
        </tr>
    `).join('');
}

// Attendance
async function loadAttendancePage() {
    await loadClassesForSelect();
    const select = document.getElementById('attendanceClass');
    if (select) {
        select.innerHTML = '<option value="">Select Class</option>' + 
            classes.map(c => `<option value="${c.id}">${c.className} ${c.section || ''}</option>`).join('');
    }
    document.getElementById('attendanceDate').valueAsDate = new Date();
}

// Grades
async function loadGrades() {
    try {
        const grades = await api.getGrades();
        renderGradesTable(grades);
    } catch (error) {
        console.error('Error loading grades:', error);
    }
}

function renderGradesTable(grades) {
    const tbody = document.getElementById('gradesTableBody');
    tbody.innerHTML = grades.map(grade => `
        <tr>
            <td>${grade.student ? grade.student.firstName + ' ' + grade.student.lastName : '-'}</td>
            <td>${grade.course ? grade.course.courseName : '-'}</td>
            <td>${grade.marks || '-'}</td>
            <td>${grade.grade || '-'}</td>
            <td>${grade.examType || '-'}</td>
            <td>
                <button class="btn btn-sm btn-danger" onclick="deleteGrade(${grade.id})">Delete</button>
            </td>
        </tr>
    `).join('');
}

// Fees
async function loadFees() {
    try {
        fees = await api.getFees();
        renderFeesTable();
    } catch (error) {
        console.error('Error loading fees:', error);
    }
}

function renderFeesTable() {
    const tbody = document.getElementById('feesTableBody');
    tbody.innerHTML = fees.map(fee => `
        <tr>
            <td>${fee.student ? fee.student.firstName + ' ' + fee.student.lastName : '-'}</td>
            <td>${fee.feeType || '-'}</td>
            <td>$${fee.amount || 0}</td>
            <td>$${fee.paidAmount || 0}</td>
            <td>${fee.dueDate || '-'}</td>
            <td><span class="status-badge ${fee.status?.toLowerCase()}">${fee.status || 'PENDING'}</span></td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-sm btn-success" onclick="payFee(${fee.id})">Pay</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteFee(${fee.id})">Delete</button>
                </div>
            </td>
        </tr>
    `).join('');
}

// Delete functions
async function deleteStudent(id) {
    if (confirm('Are you sure you want to delete this student?')) {
        try {
            await api.deleteStudent(id);
            loadStudents();
        } catch (error) {
            alert('Error deleting student: ' + error.message);
        }
    }
}

async function deleteTeacher(id) {
    if (confirm('Are you sure you want to delete this teacher?')) {
        try {
            await api.deleteTeacher(id);
            loadTeachers();
        } catch (error) {
            alert('Error deleting teacher: ' + error.message);
        }
    }
}

async function deleteCourse(id) {
    if (confirm('Are you sure you want to delete this course?')) {
        try {
            await api.deleteCourse(id);
            loadCourses();
        } catch (error) {
            alert('Error deleting course: ' + error.message);
        }
    }
}

async function deleteClass(id) {
    if (confirm('Are you sure you want to delete this class?')) {
        try {
            await api.deleteClass(id);
            loadClasses();
        } catch (error) {
            alert('Error deleting class: ' + error.message);
        }
    }
}

async function payFee(id) {
    const amount = prompt('Enter payment amount:');
    if (amount) {
        try {
            await api.updateFeePayment(id, parseFloat(amount));
            loadFees();
        } catch (error) {
            alert('Error updating payment: ' + error.message);
        }
    }
}

function closeModal() {
    document.getElementById('modal').classList.remove('show');
}

function formatNumber(num) {
    return num.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

// Edit functions (simplified - can be enhanced)
function editStudent(id) {
    alert('Edit functionality - To be implemented');
}

function editTeacher(id) {
    alert('Edit functionality - To be implemented');
}

function editCourse(id) {
    alert('Edit functionality - To be implemented');
}

function editClass(id) {
    alert('Edit functionality - To be implemented');
}

function deleteGrade(id) {
    alert('Delete grade functionality - To be implemented');
}

function deleteFee(id) {
    if (confirm('Are you sure you want to delete this fee record?')) {
        alert('Delete fee functionality - To be implemented');
    }
}

