app.service("ClassGroupService", function ($http) {
  this.getClassGroups = function () {
    return $http.get("http://localhost:8080/api/classGroup");
  };
});

app.service("CourseService", function ($http) {
  this.getCourses = function () {
    return $http.get("http://localhost:8080/api/course");
  };
});

app.service("StudentService", function ($http) {
  this.getCourses = function () {
    return $http.get("http://localhost:8080/api/student");
  };
});

app.service("ClassGroupStudentService", function ($http) {
  this.getStudents = function (classGroupId) {
    return $http.get(
      "http://localhost:8080/api/classGroup/" + classGroupId + "/students"
    );
  };
});
