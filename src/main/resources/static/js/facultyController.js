app.controller("FacultyController", function ($scope, $http, $window) {
  $scope.selectedAction = "";

  $scope.performAction = function () {
    if ($scope.selectedAction === "delete") {
      $scope.deleteSelectedFaculties();
    } else if ($scope.selectedAction === "edit") {
      $scope.editFaculty();
    } else if ($scope.selectedAction === "create") {
      $scope.createFaculty();
    }

    $scope.selectedAction = "";
  };

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

  $scope.toggleSelectAll = function () {
    angular.forEach($scope.faculties, function (faculty) {
      faculty.selected = $scope.selectAll;
    });
  };

  $scope.deleteSelectedFaculties = function () {
    var selectedFacultyIds = [];
    angular.forEach($scope.faculties, function (faculty) {
      if (faculty.selected) {
        selectedFacultyIds.push(faculty.id);
      }
    });
    if (selectedFacultyIds.length === 0) {
      alert("Please select at least one faculty to delete.");
      return;
    }
    var confirmation = $window.confirm(
      "Are you sure you want to delete selected faculties?"
    );
    if (confirmation) {
      $http({
        method: "DELETE",
        url: "http://localhost:8080/api/faculty",
        params: { id: selectedFacultyIds },
      })
        .then(function (response) {
          $scope.fetchFaculties();

          $scope.selectAll = false;
        })
        .catch(function (error) {
          alert("Cannot delete faculties mapped with classGroups");
          console.error("Error deleting faculties:", error);
        });
    }
  };

  $scope.editFaculty = function () {
    var selectedFaculties = $scope.faculties.filter(function (faculty) {
      return faculty.selected;
    });
    if (selectedFaculties.length !== 1) {
      alert("Please select exactly one faculty to edit.");
      return;
    }
    $scope.editedFaculty = angular.copy(selectedFaculties[0]);
    $scope.showFacultyEditModal = true;
  };

  $scope.updateFaculty = function () {
    $http
      .put(
        "http://localhost:8080/api/faculty/" + $scope.editedFaculty.id,
        $scope.editedFaculty
      )
      .then(function (response) {
        $scope.fetchFaculties();

        $scope.closeFacultyEditModal();
      })
      .catch(function (error) {
        console.error("Error updating faculty:", error);
      });
  };

  $scope.closeFacultyEditModal = function () {
    $scope.showFacultyEditModal = false;
    $scope.editedFaculty = null;
  };

  $scope.createFaculty = function () {
    $scope.newFaculty = {};
    $scope.showFacultyCreateModal = true;
  };

  $scope.addFaculty = function () {
    $http
      .post("http://localhost:8080/api/faculty", $scope.newFaculty)
      .then(function (response) {
        $scope.fetchFaculties();
      })
      .catch(function (error) {
        console.error("Error creating faculty:", error);
      });
  };

  $scope.closeFacultyCreateModal = function () {
    $scope.showFacultyCreateModal = false;
    $scope.newFaculty = null;
  };
});
