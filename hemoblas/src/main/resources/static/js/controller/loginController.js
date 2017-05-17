angular.module("hemoblas").controller("loginController", function($scope, $rootScope, $location, $window, autenticacaoService) {

	// LOGIN
	$scope.login = function(dadosLogin) {

		autenticacaoService.autenticarUsuario(dadosLogin).success(function(data) {
			// $rootScope.cpfDoador = dadosLogin.cpf;
			$window.sessionStorage.cpfDoador = dadosLogin.cpf;

			autenticacaoService.setToken(data.token);

			$location.path("/agendaDoacao");
		}).error(function(data, status) {
			$scope.autenticacaoInvalida = true;
		});

	};

});