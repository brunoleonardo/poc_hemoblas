angular.module('hemoblas').config([ '$httpProvider', function($httpProvider, $location, $q) {

	$httpProvider.interceptors.push(function($q, $injector) {

		return {
			request : function(config) {
				config.headers = config.headers || {};

				// Injetado manualmente para contornar o problema de dependência
				// circular.
				var autenticacaoService = $injector.get('autenticacaoService');
				if (autenticacaoService.getToken()) {
					config.headers['Authorization'] = autenticacaoService.getToken();
				}

				return config;
			},

			responseError : function(response) {
				if (response.status === 401 || response.status === 403) {
					$location.path('/login');
				}

				return $q.reject(response);
			}
		};

	});
} ]);