const API_BASE_URL = 'http://localhost:8080/api';

class ApiService {
    constructor() {
        this.token = localStorage.getItem('token');
    }

    setToken(token) {
        this.token = token;
        localStorage.setItem('token', token);
    }

    clearToken() {
        this.token = null;
        localStorage.removeItem('token');
    }

    async request(endpoint, options = {}) {
        const url = `${API_BASE_URL}${endpoint}`;
        const config = {
            ...options,
            headers: {
                'Content-Type': 'application/json',
                ...options.headers,
            },
        };

        if (this.token) {
            config.headers['Authorization'] = `Bearer ${this.token}`;
        }

        try {
            const response = await fetch(url, config);
            const data = await response.json();
            
            if (!response.ok) {
                throw new Error(data.message || 'Request failed');
            }
            
            return data;
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    }

    // Auth
    async login(email, password) {
        const response = await this.request('/auth/login', {
            method: 'POST',
            body: JSON.stringify({ email, password }),
        });
        this.setToken(response.token);
        return response;
    }

    // Students
    async getStudents() {
        return this.request('/students');
    }

    async getStudent(id) {
        return this.request(`/students/${id}`);
    }

    async createStudent(student) {
        return this.request('/students', {
            method: 'POST',
            body: JSON.stringify(student),
        });
    }

    async updateStudent(id, student) {
        return this.request(`/students/${id}`, {
            method: 'PUT',
            body: JSON.stringify(student),
        });
    }

    async deleteStudent(id) {
        return this.request(`/students/${id}`, {
            method: 'DELETE',
        });
    }

    // Teachers
    async getTeachers() {
        return this.request('/teachers');
    }

    async getTeacher(id) {
        return this.request(`/teachers/${id}`);
    }

    async createTeacher(teacher) {
        return this.request('/teachers', {
            method: 'POST',
            body: JSON.stringify(teacher),
        });
    }

    async updateTeacher(id, teacher) {
        return this.request(`/teachers/${id}`, {
            method: 'PUT',
            body: JSON.stringify(teacher),
        });
    }

    async deleteTeacher(id) {
        return this.request(`/teachers/${id}`, {
            method: 'DELETE',
        });
    }

    // Courses
    async getCourses() {
        return this.request('/courses');
    }

    async getCourse(id) {
        return this.request(`/courses/${id}`);
    }

    async createCourse(course) {
        return this.request('/courses', {
            method: 'POST',
            body: JSON.stringify(course),
        });
    }

    async updateCourse(id, course) {
        return this.request(`/courses/${id}`, {
            method: 'PUT',
            body: JSON.stringify(course),
        });
    }

    async deleteCourse(id) {
        return this.request(`/courses/${id}`, {
            method: 'DELETE',
        });
    }

    // Classes
    async getClasses() {
        return this.request('/classes');
    }

    async getClass(id) {
        return this.request(`/classes/${id}`);
    }

    async createClass(schoolClass) {
        return this.request('/classes', {
            method: 'POST',
            body: JSON.stringify(schoolClass),
        });
    }

    async updateClass(id, schoolClass) {
        return this.request(`/classes/${id}`, {
            method: 'PUT',
            body: JSON.stringify(schoolClass),
        });
    }

    async deleteClass(id) {
        return this.request(`/classes/${id}`, {
            method: 'DELETE',
        });
    }

    // Attendance
    async getAttendance() {
        return this.request('/attendance');
    }

    async markAttendance(attendance) {
        return this.request('/attendance', {
            method: 'POST',
            body: JSON.stringify(attendance),
        });
    }

    async getAttendanceByStudent(studentId) {
        return this.request(`/attendance/student/${studentId}`);
    }

    async getAttendanceByClass(classId, date) {
        return this.request(`/attendance/class/${classId}?date=${date}`);
    }

    // Grades
    async getGrades() {
        return this.request('/grades');
    }

    async createGrade(grade) {
        return this.request('/grades', {
            method: 'POST',
            body: JSON.stringify(grade),
        });
    }

    async getGradesByStudent(studentId) {
        return this.request(`/grades/student/${studentId}`);
    }

    async getGradesByCourse(courseId) {
        return this.request(`/grades/course/${courseId}`);
    }

    // Fees
    async getFees() {
        return this.request('/fees');
    }

    async createFee(fee) {
        return this.request('/fees', {
            method: 'POST',
            body: JSON.stringify(fee),
        });
    }

    async updateFeePayment(id, paidAmount) {
        return this.request(`/fees/${id}/payment`, {
            method: 'PUT',
            body: JSON.stringify({ paidAmount }),
        });
    }

    async getFeesByStudent(studentId) {
        return this.request(`/fees/student/${studentId}`);
    }

    // Dashboard
    async getDashboardStats() {
        return this.request('/dashboard/stats');
    }
}

const api = new ApiService();

