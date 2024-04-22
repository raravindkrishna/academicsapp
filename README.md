applications.properties file is ignored


spring.application.name=academicsapp
spring.datasource.url={DB_URL}
spring.datasource.username={DB_USERNAME}
spring.datasource.password={DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true






Class Group Management System

Database

![image](https://github.com/raravindkrishna/academicsapp/assets/126563764/92266c6a-39d4-4470-b84a-f60117fab66b)

 
Models:

Faculty
Course
Student
ClassGroup
ClassGroupStudent

APIs:

Faculty APIs:
Create a new faculty: POST /home/faculty/{id}
Fetch all faculties: GET /home/faculty
Fetch a particular faculty by ID: GET /home/faculties/{id}
Update an existing faculty: PUT /home/faculty/{id}
Delete a faculty: DELETE /home/faculty?id=1&id=2

Fetch all the courses of a particular faculty: GET /home/faculty/{facultyId}/courses
Course APIs:
Create a new course: POST /home/course
Fetch all courses: GET /home/course
Fetch a particular course by ID: GET /home/course/{id}
Update an existing course: PUT /home/course/{id}
Delete a course: (Parameter id) DELETE /home/course/{id}

Student APIs:
Create a new student: POST /home/student
Fetch all students: GET /home/student
Fetch a particular student by ID: GET /home/student/{id}
Update an existing student: PUT /home/student/{id}
Delete a student: (Parameter id) DELETE /home/student?id=1&id=2



ClassGroup APIs:
a) Have to use Classgroup table repo
Create a new class group: POST /home/classGroup
Fetch all class groups: GET /home/classGroup
Fetch a particular class group by ID: GET /home/classGroup/{id}
Update an existing class group: PUT /home/classGroup/{id}
Delete a class group: (Parameter id) DELETE /home/classGroup?id=1&id=2
Update:
Update faculty of a classgroup: PUT /home/classGroup/{classGroupId}/faculty/{facultyId}
Update course of a classGroup: PUT /home/classGroup/{classGroupId}/course/{courseId}
ClassGroupStudent
b) Have to use Classgroup_Enrolled table repo
Add a student in a class group: POST /home/classGroup/{classGroupId}/students/{studentId}
Remove a student from a class group:  (Parameter id) DELETE /home/classGroup/{classgroupId}/students?id=1&id=2
Fetch all students enrolled in a particular class group: GET /home/classGroup/{classgroupId}/students (custom join query)

All the methods inside controller and service: (same method names but service layer methods will do the operations)
Controllers
FacultyController
Methods
•	createFaculty()
•	getAllFaculties()
•	getFacultyById()
•	updateFacultyById()
•	deleteFacultyById()
•	getAllFacultiesOfThisCourse()

CourseController
Methods
•	createCourse()
•	getAllCourses()
•	getCourseById()
•	updateCourseById()
•	deleteCourseById()
•	getAllFacultiesOfThisCourse()



StudentController
Methods
•	createStudent()
•	getAllStudents()
•	getStudentById()
•	updateStudentById()
•	deleteStudentById()

ClassGroupController
Methods
•	createClassGroup()
•	getAllClassGroups()
•	getClassGroupById()
•	updateClassGroupById()
•	deleteClassGroupById()
•	updateCourseOfClassGroup()
•	updateFacultyOfClassGroup()

ClassGroupEnrollmentController
Methods
•	addStudentToClassGroup() //will accept list of student ids to be enrolled and classgroupid
•	removeStudentsFromClassGroup() //will accept list of student ids to be deleted and classgroupid
•	getAllStudentsInClassGroup() 
Service
FacultyService
Methods
•	createFaculty()
•	getAllFaculties()
•	getFacultyById()
•	updateFacultyById()
•	deleteFacultyById()
CourseService 
Methods
•	createCourse()
•	getAllCourses()
•	getCourseById()
•	updateCourseById()
•	deleteCourseById()
StudentService
Methods
•	createStudent()
•	getAllStudents()
•	getStudentById()
•	updateStudentById()
•	deleteStudentById()
ClassGroupService
Methods
•	createClassGroup()
•	getAllClassGroups()
•	getClassGroupById()
•	updateClassGroupById()
•	deleteClassGroupById()
ClassGroupStudentService
Methods
•	addStudentToClassGroup() 
•	removeStudentsFromClassGroup() //will accept list of student ids to be deleted and classgroupid
•	getStudentsEnrolledInClassGroupById() 
DAO
All these will implement JPA repo
FacultyDAO
CourseDAO
StudentDAO
ClassGroupDAO 
ClassGroupStudentDAO
