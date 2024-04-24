app.service("ClassGroupService", function ($http) {
  this.getClassGroups = function () {
    return $http.get("http://localhost:8080/home/classGroup");
  };
});
