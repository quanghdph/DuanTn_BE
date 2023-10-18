window.homeController = function ($scope, $http) {
    const apiProduct = "/product";
        $http.get(apiProduct).then(function (response) {
            if (response.status == 200) {
                console.log(response);
                $scope.listProduct = response.data.data;
                console.log($scope.listProduct);
            }
        });
    }