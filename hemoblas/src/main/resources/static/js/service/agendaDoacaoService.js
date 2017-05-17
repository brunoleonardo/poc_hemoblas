angular.module("hemoblas").service(
		"agendaDoacaoService",
		function($http, config) {

			var statusAgenda = {
				ABERTA : 0,
				ALOCADA : 1,
				REALIZADA : 2,
				CANCELADA : 3
			};

			this.getAgendasAbertasPorHemocentro = function(estabelecimentoSaudeId) {

				return $http.get(config.baseUrlAgendamentoSecure + "/agendas/busca/estabelecimentoId=" + estabelecimentoSaudeId
						+ "&statusId=" + statusAgenda.ABERTA);

			};

			this.agendarDoacao = function(agenda) {

				return $http.post(config.baseUrlAgendamentoSecure + "/agendas/", agenda);

			};

		});