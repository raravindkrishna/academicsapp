app.controller("StudentController", function ($scope, $http, $window) {
  $scope.selectedAction = "";

  // Function to perform selected action
  $scope.performAction = function () {
    if ($scope.selectedAction === "delete") {
      $scope.deleteSelectedStudents();
    } else if ($scope.selectedAction === "edit") {
      $scope.editStudent();
    } else if ($scope.selectedAction === "create") {
      $scope.createStudent();
    }
    // Reset selected action
    $scope.selectedAction = "";
  };

  // Fetch all students from the backend
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

  // Toggle select all checkboxes
  $scope.toggleSelectAll = function () {
    angular.forEach($scope.students, function (student) {
      student.selected = $scope.selectAll;
    });
  };

  // Delete selected students
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
    var confirmation = $window.confirm(
      "Are you sure you want to delete selected students?"
    );
    if (confirmation) {
      $http({
        method: "DELETE",
        url: "http://localhost:8080/api/student",
        params: { id: selectedStudentIds },
      })
        .then(function (response) {
          // Refresh the list of students after deletion
          $scope.fetchStudents();
          // Reset select all checkbox
          $scope.selectAll = false;
        })
        .catch(function (error) {
          alert("Cannot delete students mapped with classGroup Students");
          console.error("Error deleting students:", error);
        });
    }
  };

  // Edit Student
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

  // Update Student
  $scope.updateStudent = function () {
    $http
      .put(
        "http://localhost:8080/api/student/" + $scope.editedStudent.id,
        $scope.editedStudent
      )
      .then(function (response) {
        // Refresh the list of students after update
        $scope.fetchStudents();
        // Close the edit modal
        $scope.closeStudentEditModal();
      })
      .catch(function (error) {
        console.error("Error updating student:", error);
      });
  };

  // Close Edit Modal
  $scope.closeStudentEditModal = function () {
    $scope.showStudentEditModal = false;
    $scope.editedStudent = null;
  };

  // Create Student
  $scope.createStudent = function () {
    $scope.newStudent = {};
    $scope.showStudentCreateModal = true;
  };

  // Add Student
  $scope.addStudent = function () {
    $http
      .post("http://localhost:8080/api/student", $scope.newStudent)
      .then(function (response) {
        // Refresh the list of students after creation
        $scope.fetchStudents();
        // Close the create modal
        $scope.closeStudentCreateModal();
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
