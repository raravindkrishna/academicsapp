app.controller("StudentController", function ($scope, $http, $window) {
  $scope.selectedAction = "";

  $scope.fetchStudents = function () {
    $http
      .get("http://localhost:8080/api/student")
      .then(function (response) {
        $scope.students = response.data;
      })
      .catch(function (error) {
        console.error("Error fetching students:", error);
      });
  };
  $scope.fetchStudents();

  $scope.toggleSelectAll = function () {
    angular.forEach($scope.students, function (student) {
      student.selected = $scope.selectAll;
    });
  };

  $scope.closeStudentRemoveModal = function () {
    $scope.StudentRemoveModal = false;
  };

  $scope.deleteSelectedStudents = function () {
    var selectedStudentIds = [];
    angular.forEach($scope.students, function (student) {
      if (student.selected) {
        selectedStudentIds.push(student.id);
      }
    });
    if (selectedStudentIds.length === 0) {
      alert("Please select at least one student to delete.");
      return;
    }

    $scope.StudentRemoveModal = true;
    $scope.selectedStudentIds = selectedStudentIds;
  };

  $scope.deleteStudentConfirm = function () {
    var selectedStudentIds = $scope.selectedStudentIds;
    $http({
      method: "DELETE",
      url: "http://localhost:8080/api/student",
      params: { id: selectedStudentIds },
    })
      .then(function (response) {
        $scope.fetchStudents();
        $scope.closeStudentRemoveModal();
        $scope.selectAll = false;
      })
      .catch(function (error) {
        alert("Cannot delete students mapped with classGroup Students");
        console.error("Error deleting students:", error);
      });
  };

  $scope.editStudent = function () {
    var selectedStudents = $scope.students.filter(function (student) {
      return student.selected;
    });
    if (selectedStudents.length !== 1) {
      alert("Please select exactly one student to edit.");
      return;
    }
    $scope.editedStudent = angular.copy(selectedStudents[0]);
    $scope.showStudentEditModal = true;
  };

  $scope.updateStudent = function () {
    $http
      .put(
        "http://localhost:8080/api/student/" + $scope.editedStudent.id,
        $scope.editedStudent
      )
      .then(function (response) {
        $scope.fetchStudents();

        $scope.closeStudentEditModal();
      })
      .catch(function (error) {
        console.error("Error updating student:", error);
      });
  };

  $scope.closeStudentEditModal = function () {
    $scope.showStudentEditModal = false;
    $scope.editedStudent = null;
  };

  $scope.createStudent = function () {
    $scope.newStudent = {};
    $scope.showStudentCreateModal = true;
  };

  $scope.addStudent = function () {
    $http
      .post("http://localhost:8080/api/student", $scope.newStudent)
      .then(function (response) {
        $scope.fetchStudents();
      })
      .catch(function (error) {
        console.error("Error creating student:", error);
      });
  };

  $scope.closeStudentCreateModal = function () {
    $scope.showStudentCreateModal = false;
    $scope.newStudent = null;
  };
});
