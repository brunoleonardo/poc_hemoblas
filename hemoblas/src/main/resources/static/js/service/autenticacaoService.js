angular.module("hemoblas").service("autenticacaoService", function($http, $localStorage, $q, config) {
	
	this.autenticarUsuario = function(dadosLogin) {				
		return $http.post(config.baseUrlGeral + "/public/autenticarUsuario", dadosLogin);		
	};	
	
	this.getToken = function() {				
		return $localStorage.token;		
	};	
	
	this.setToken = function (token) {		
		$localStorage.token = token;      
    };
    
    this.logout = function() {
    	delete $localStorage.token;
    	$q.when();
    };
	
});