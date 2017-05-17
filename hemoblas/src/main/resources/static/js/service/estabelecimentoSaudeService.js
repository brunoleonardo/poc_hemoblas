angular.module("hemoblas").service(
		"estabelecimentoSaudeService",
		function($http, config) {

			var tipoUnidade = {
				CENTRO_ATENCAO_HEMOTERAPIA : 69
			};

			this.getHemocentrosPorMunicipio = function(municipioId) {

				return $http.get(config.baseUrlAdministrativoSecure + "/estabelecimentosSaude/busca/municipioId=" + municipioId
						+ "&tipoUnidadeId=" + tipoUnidade.CENTRO_ATENCAO_HEMOTERAPIA);

			};

		});