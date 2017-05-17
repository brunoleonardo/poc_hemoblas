angular.module("hemoblas").run(function($rootScope, $location, autenticacaoService) {

	$rootScope.$on('$routeChangeStart', function(event, next, current) {

		if (next.authorize) {

			if (!autenticacaoService.getToken()) {
				$rootScope.$evalAsync(function() {
					$location.path('/');
				})
			}
		}
	});

});
