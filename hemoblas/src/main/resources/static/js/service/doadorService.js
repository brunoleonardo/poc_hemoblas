angular.module("hemoblas").service("doadorService", function($http, config) {

	this.getDoadorPorCpf = function(cpf) {

		// PARA ENVIAR PARÂMETROS COM PONTO SE FAZ NECESSÁRIO A BARRA NO FINAL
		return $http.get(config.baseUrlAgendamentoPublic + "/doadores/busca/cpf=" + cpf + "/");

	};

	this.cadastrarDoador = function(doador) {

		return $http.post(config.baseUrlAgendamentoPublic + "/doadores/", doador);

	};

});