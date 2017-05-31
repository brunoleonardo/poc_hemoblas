angular.module("hemoblas").controller("cadastroDoadorController", function($scope, $location, doadorService, usuarioService) {
	$scope.app = "Cadastrar Doador";

	$scope.doador = {};

	$scope.generos = [ {
		id : "1",
		descricao : "Masculino"
	}, {
		id : "2",
		descricao : "Feminino"
	}, ];

	// CADASTRAR DOADOR
	$scope.cadastrarDoador = function(doador) {
		$scope.processando = true;
		
		doadorService.cadastrarDoador(doador).success(function(data) {

			// CADASTRA UM USUÁRIO PARA O DOADOR
			usuarioService.cadastrarUsuario({
				cpf : doador.cpf,
				senha : doador.senha,
				perfil : "DOADOR"
			});

			$scope.sucesso = true;

			delete $scope.doador;

			// Seta o formulário para o estado "virgem, não tocado", para que as
			// msgs de validação não apareçam.
			$scope.doadorForm.$setUntouched();
			$scope.doadorForm.$setPristine();

			$scope.processando = false;			
		}).error(function(data, status) {
			$scope.errors = data.errors;
			
			$scope.processando = false;
		});
	};
});