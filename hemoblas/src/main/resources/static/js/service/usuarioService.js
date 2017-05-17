angular.module("hemoblas").service("usuarioService", function($http, config) {

	this.cadastrarUsuario = function(usuario) {
		return $http.post(config.baseUrlAdministrativoPublic + "/usuarios/", usuario);
	};

});