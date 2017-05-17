angular.module("hemoblas").service("municipioService", function($http, config) {

	this.getMunicipiosPorEstado = function(uf) {

		return $http.get(config.baseUrlAdministrativoSecure + "/municipios/busca/uf=" + uf);

	};

});