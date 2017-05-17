angular.module("hemoblas").config(function($routeProvider) {

	$routeProvider.when("/", {
		templateUrl : "view/login.html",
		controller : "loginController"
	});

	$routeProvider.when("/agendaDoacao", {
		templateUrl : "view/doacao/agendaDoacao.html",
		controller : "agendaDoacaoController",
		authorize : true
	});

	$routeProvider.when("/cadastroDoador", {
		templateUrl : "view/doador/cadastroDoador.html",
		controller : "cadastroDoadorController",
		authorize : true
	});

	$routeProvider.when("/error", {
		templateUrl : "view/error.html"
	});

});
