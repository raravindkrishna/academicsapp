app.controller("CourseController", function ($scope, $http, $window) {
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

  $scope.toggleSelectAll = function () {
    angular.forEach($scope.courses, function (course) {
      course.selected = $scope.selectAll;
    });
  };

  $scope.closeCourseRemoveModal = function () {
    $scope.CourseRemoveModal = false;
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

    $scope.CourseRemoveModal = true;
    $scope.selectedCourseIds = selectedCourseIds;
  };

  $scope.deleteCourseConfirm = function () {
    var selectedCourseIds = $scope.selectedCourseIds;
    $http({
      method: "DELETE",
      url: "http://localhost:8080/api/course",
      params: { id: selectedCourseIds },
    })
      .then(function (response) {
        $scope.fetchCourses();
        $scope.closeCourseRemoveModal();
        $scope.selectAll = false;
      })
      .catch(function (error) {
        alert("Cannot delete courses mapped with classGroups");
        console.error("Error deleting courses:", error);
      });
  };

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

  $scope.updateCourse = function () {
    if (!$scope.editedCourse.description) {
      $scope.editedCourse.description = "-";
    }
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
    if (!$scope.newCourse.description) {
      $scope.newCourse.description = "-";
    }
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
