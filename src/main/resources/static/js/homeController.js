app.controller("HomeController", function ($scope, $http, $location) {
  $scope.getClassGroups = function () {
    $http
      .get("http://localhost:8080/api/classGroup")
      .then(function (response) {
        $scope.classGroups = response.data;
      })
      .catch(function (error) {
        console.error("Error fetching class groups:", error);
      });
    let theClassGroupId;
    $scope.selectClassGroup = function (classGroupId) {
      this.classGroupId = theClassGroupId;
      $location.path("/classGroup/" + classGroupId + "/students");
    };
    console.log(theClassGroupId);

    $scope.addCg = function () {
      $scope.showCgCreateModal = true;
      $scope.newClassGroup = {};
      $scope.fetchCourses = function () {
        $http
          .get("http://localhost:8080/api/course")
          .then(function (response) {
            $scope.courses = response.data;
          })
          .catch(function (error) {
            console.error("Error fetching courses:", error);
          });
      };
      $scope.fetchCourses();

      $scope.fetchFaculties = function () {
        $http
          .get("http://localhost:8080/api/faculty")
          .then(function (response) {
            $scope.faculties = response.data;
          })
          .catch(function (error) {
            console.error("Error fetching faculties:", error);
          });
      };
      $scope.fetchFaculties();
    };

    $scope.addClassGroup = function () {
      $http
        .post("http://localhost:8080/api/classGroup", $scope.newClassGroup)
        .then(function (response) {
          $scope.getClassGroups();
        })
        .catch(function (error) {
          console.error("Error creating faculty:", error);
        });
    };

    $scope.closeCgCreateModal = function () {
      $scope.showCgCreateModal = false;
    };
  };

  $scope.getClassGroups();
});
