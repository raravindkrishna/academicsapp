app.controller("HomeController", function ($scope, ClassGroupService) {
  ClassGroupService.getClassGroups()
    .then(function (response) {
      $scope.classGroups = response.data;
    })
    .catch(function (error) {
      console.error("Error fetching class groups:", error);
    });
});

app.controller("MainController", function ($scope, $http) {});

app.controller("CourseController", function ($scope, $http) {});

app.controller("StudentController", function ($scope, $http) {});

app.controller("ClassGroupStudentController", function ($scope, $http) {});
