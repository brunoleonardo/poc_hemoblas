angular.module("hemoblas").controller("loginController", function($scope, $rootScope, $location, $window, autenticacaoService) {

	// LOGIN
	$scope.login = function(dadosLogin) {
		$scope.autenticando = true;
		
		autenticacaoService.autenticarUsuario(dadosLogin).success(function(data) {		
			$window.sessionStorage.cpfDoador = dadosLogin.cpf;

			autenticacaoService.setToken(data.token);

			$location.path("/agendaDoacao");
		}).error(function(data, status) {
			$scope.autenticando = false;
			
			$scope.autenticacaoInvalida = true;
		});

	};

});