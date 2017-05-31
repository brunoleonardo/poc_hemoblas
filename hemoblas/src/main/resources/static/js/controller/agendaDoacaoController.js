angular.module("hemoblas").controller(
		"agendaDoacaoController",
		function($scope, $rootScope, $location, $window, autenticacaoService, municipioService, estabelecimentoSaudeService,
				agendaDoacaoService, doadorService) {
			$scope.app = "Agendar Doação";

			$scope.agenda = {};

			$scope.estados = [ {
				sigla : 'AC',
				nome : 'Acre'
			}, {
				sigla : 'AL',
				nome : 'Alagoas'
			}, {
				sigla : 'AM',
				nome : 'Amazonas'
			}, {
				sigla : 'AP', 
				nome : 'Amapá'
			}, {
				sigla : 'BA',
				nome : 'Bahia'
			}, {
				sigla : 'CE',
				nome : 'Ceará'
			}, {
				sigla : 'DF',
				nome : 'Distrito Federal'
			}, {
				sigla : 'ES',
				nome : 'Espírito Santo'
			}, {
				sigla : 'GO',
				nome : 'Goiás'
			}, {
				sigla : 'MA',
				nome : 'Maranhão'
			}, {
				sigla : 'MG',
				nome : 'Minas Gerais'
			}, {
				sigla : 'MS',
				nome : 'Mato Grosso do Sul'
			}, {
				sigla : 'MT',
				nome : 'Mato Grosso'
			}, {
				sigla : 'PA',
				nome : 'Pará'
			}, {
				sigla : 'PB',
				nome : 'Paraíba'
			}, {
				sigla : 'PE',
				nome : 'Pernambuco'
			}, {
				sigla : 'PI',
				nome : 'Piauí'
			}, {
				sigla : 'PR',
				nome : 'Paraná'
			}, {
				sigla : 'RJ',
				nome : 'Rio de Janeiro'
			}, {
				sigla : 'RN',
				nome : 'Rio Grande do Norte'
			}, {
				sigla : 'RO',
				nome : 'Rondônia'
			}, {
				sigla : 'RR',
				nome : 'Roraima'
			}, {
				sigla : 'RS',
				nome : 'Rio Grande do Sul'
			}, {
				sigla : 'SC',
				nome : 'Santa Catarina'
			}, {
				sigla : 'SE',
				nome : 'Sergipe'
			}, {
				sigla : 'SP',
				nome : 'São Paulo'
			}, {
				sigla : 'TO',
				nome : 'Tocantins'
			} ];

			$scope.estadoSelecionado = {};
			$scope.municipioSelecionado = {};
			$scope.hemocentroSelecionado = {};

			// CARREGA OS MUNICÍPIOS A PARTIR DO ESTADO SELECIONADO.
			$scope.getMunicipiosPorEstado = function() {
				if ($scope.estadoSelecionado) {
					municipioService.getMunicipiosPorEstado($scope.estadoSelecionado.sigla).success(function(data) {
						$scope.municipios = data;
					}).error(function(data, status) {
						$scope.errors = data.errors;
					});
				} else {
					$scope.municipios = [];
				}
			};

			// CARREGA OS HEMOCENTROS A PARTIR DO MUNICÍPIO SELECIONADO.
			$scope.getHemocentrosPorMunicipio = function() {
				if ($scope.municipioSelecionado) {
					estabelecimentoSaudeService.getHemocentrosPorMunicipio($scope.municipioSelecionado.id).success(function(data) {
						$scope.estabelecimentosSaude = data;
					}).error(function(data, status) {
						$scope.errors = data.errors;
					});
				} else {
					$scope.estabelecimentosSaude = [];
				}
			};

			// CARREGA AS AGENDAS DO HEMOCENTRO SELECIONADO.
			$scope.getAgendasPorHemocentro = function() {

				if ($scope.hemocentroSelecionado) {
					agendaDoacaoService.getAgendasAbertasPorHemocentro($scope.hemocentroSelecionado.id).success(function(data) {
						$scope.agendasHemocentro = data;
					}).error(function(data, status) {
						$scope.errors = data.errors;
					});
				} else {
					$scope.agendas = [];
				}
			};

			// AGENDAR DOAÇÃO
			$scope.agendarDoacao = function(agenda) {

				// BUSCA O DOADOR E SETA NA AGENDA
				doadorService.getDoadorPorCpf($window.sessionStorage.cpfDoador).success(function(data) {
					$scope.agenda.doador = data;

					// AGENDA A DOAÇÃO
					agendaDoacaoService.agendarDoacao(agenda).success(function(data) {
						$scope.numeroProtocolo = agenda.numeroProtocolo;
						
						$scope.obterComprovanteAgendamento();
																		
						delete $scope.agenda;

						// Seta o formulário para o estado "virgem, não tocado",
						// para que as msgs de validação não apareçam.
						$scope.doacaoForm.$setUntouched();
						$scope.doacaoForm.$setPristine();
					}).error(function(data, status) {
						$scope.errors = data.errors;
					});
				}).error(function(data, status) {
					$scope.errors = data.errors;
				});

			};
			
			$scope.obterComprovanteAgendamento = function() {
								
				agendaDoacaoService.obterComprovanteAgendamento($scope.numeroProtocolo).success(function(data) {
					var ieEDGE = navigator.userAgent.match(/Edge/g);
		            var ie = navigator.userAgent.match(/.NET/g); // IE 11+
		            var oldIE = navigator.userAgent.match(/MSIE/g); 
		            var name = "comprovanteAgendamento";
		            var blob = new window.Blob([data], { type: 'application/pdf' });

		            if (ie || oldIE || ieEDGE) {
		                var fileName = name + ".pdf";
		                window.navigator.msSaveBlob(blob, fileName);
		            } else {
		                var file = new Blob([ data ], {
		                    type : 'application/pdf'
		                });
		                var fileURL = URL.createObjectURL(file);
		                var a         = document.createElement('a');
		                a.href        = fileURL; 
		                a.target      = '_blank';
		                a.download    = name + ".pdf";
		                document.body.appendChild(a);
		                a.click();
		            }	            
		            
				}).error(function(data, status) {
					$scope.errors = data.errors;
				});	
				
			}
			
			// LOGOUT
			$scope.logout = function() {
				$window.sessionStorage.cpfDoador = null;
				
				$location.path("#/");
			};
			
		});