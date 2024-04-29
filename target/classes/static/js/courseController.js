app.controller("CourseController", function ($scope, $http, $window) {
  $scope.selectedAction = "";

  $scope.performAction = function () {
    if ($scope.selectedAction === "delete") {
      $scope.deleteSelectedCourses();
    } else if ($scope.selectedAction === "edit") {
      $scope.editCourse();
    } else if ($scope.selectedAction === "create") {
      $scope.createCourse();
    }

    $scope.selectedAction = "";
  };

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

  // Toggle select all checkboxes
  $scope.toggleSelectAll = function () {
    angular.forEach($scope.courses, function (course) {
      course.selected = $scope.selectAll;
    });
  };

  $scope.deleteSelectedCourses = function () {
    var selectedCourseIds = [];
    angular.forEach($scope.courses, function (course) {
      if (course.selected) {
        selectedCourseIds.push(course.id);
      }
    });
    if (selectedCourseIds.length === 0) {
      alert("Please select at least one course to delete.");
      return;
    }
    var confirmation = $window.confirm(
      "Are you sure you want to delete selected courses?"
    );
    if (confirmation) {
      $http({
        method: "DELETE",
        url: "http://localhost:8080/api/course",
        params: { id: selectedCourseIds },
      })
        .then(function (response) {
          $scope.fetchCourses();

          $scope.selectAll = false;
        })
        .catch(function (error) {
          alert("Cannot delete courses mapped with classGroups");
          console.error("Error deleting courses:", error);
        });
    }
  };

  // Edit Course
  $scope.editCourse = function () {
    var selectedCourses = $scope.courses.filter(function (course) {
      return course.selected;
    });
    if (selectedCourses.length !== 1) {
      alert("Please select exactly one course to edit.");
      return;
    }
    $scope.editedCourse = angular.copy(selectedCourses[0]);
    $scope.showCourseEditModal = true;
  };

  // Update Course
  $scope.updateCourse = function () {
    $http
      .put(
        "http://localhost:8080/api/course/" + $scope.editedCourse.id,
        $scope.editedCourse
      )
      .then(function (response) {
        $scope.fetchCourses();
        $scope.closeCourseEditModal();
      })
      .catch(function (error) {
        console.error("Error updating course:", error);
      });
  };

  $scope.closeCourseEditModal = function () {
    $scope.showCourseEditModal = false;
    $scope.editedCourse = null;
  };

  $scope.createCourse = function () {
    $scope.newCourse = {};
    $scope.showCourseCreateModal = true;
  };

  $scope.addCourse = function () {
    $http
      .post("http://localhost:8080/api/course", $scope.newCourse)
      .then(function (response) {
        $scope.fetchCourses();
      })
      .catch(function (error) {
        console.error("Error creating course:", error);
      });
  };

  $scope.closeCourseCreateModal = function () {
    $scope.showCourseCreateModal = false;
    $scope.newCourse = null;
  };
});
