var app = angular.module("academicsApp", ["ngRoute"]);

app.config([
  "$routeProvider",
  function ($routeProvider) {
    $routeProvider
      .when("/home", {
        templateUrl: "views/home.html",
        controller: "HomeController",
      })
      .when("/course", {
        templateUrl: "views/course.html",
        controller: "CourseController",
      })
      .when("/student", {
        templateUrl: "views/student.html",
        controller: "StudentController",
      })
      .when("/faculty", {
        templateUrl: "views/faculty.html",
        controller: "FacultyController",
      })
      .when("/classGroup/:classGroupId/students", {
        templateUrl: "views/classGroupStudent.html",
        controller: "ClassGroupStudentController",
      })
      .otherwise({
        redirectTo: "/home",
      });
  },
]);
