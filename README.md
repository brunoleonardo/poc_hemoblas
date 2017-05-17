# Sistema de Controle de Doação de Sangue - Hemoblas

Projeto que implementa uma prova de conceito (protótipo arquitetural), apresentado como parte do Trabalho de Conclusão do Curso de Arquitetura de Software Distribuído da PUC Minas. 

Foi utilizada uma arquitetura baseada em Microsserviços, sendo assim foram criados 5 projetos para compor a prova de conceito. São eles:

# hemoblas:
* Aplicação de front-end implementada utilizando basicamente as tecnologias AngularJS, Bootstrap e Spring Boot.

# hemoblasDiscoveryServer:
* Aplicação que permite que os microsserviços registrem seus endereços em tempo de execução à medida que novas instâncias deles são iniciadas.  Implementada utilizando basicamente as tecnologias Spring Boot e Spring Cloud Netflix.

# hemoblasApiGateway:
* Aplicação responsável basicamente por receber as chamadas do front-end e fazer o “roteamento” dessas chamadas para os microsserviços, utilizando as informações disponíveis no componente “hemoblasDiscoveryServer” para localização das instâncias dos microsserviços. Responsável ainda por atuar como um Balanceador de Carga e por implementar a camada de Segurança, sendo responsável pela autenticação dos usuários e geração de tokens. Implementada utilizando basicamente as tecnologias Spring Boot, Spring Cloud Netflix, Spring Data, JWT (JSON Web Tokens) e MySQL.

# agendamentoService:
* Aplicação responsável por implementar as funcionalidades relativas ao processo de agendamento de doações de sangue, como cadastro da agenda de doação, cadastro de doador de sangue, entre outros. Implementada utilizando basicamente as tecnologias Spring Boot, Spring Cloud Netflix, Spring Data, Bean Validation e MySQL.

# administrativoService:
* Aplicação responsável por implementar funcionalidades administrativas do sistema, tais como cadastro de usuários, estabelecimentos de saúde, municípios, entre outros. Implementada utilizando basicamente as tecnologias Spring Boot,  Spring Cloud Netflix, Spring Data, Bean Validation e MySQL.

# Instalação

Projetos criados com o Apache Maven, bastando assim importá-lo em sua IDE e construí-los para resolver as dependências.

# Configuração do Banco de Dados


Crie dois esquemas no MySQL chamados **agendamento** e **administrativo** e altere as credenciais de acesso ao banco de dados nos arquivos **application.yml** dos projetos "hemoblasApiGateway", "agendamentoService" e "administrativoService". 

Em seguida execute o Script **Dump20170516.sql** para criação da estrutura de tabelas e carga de dados básicos.

# Acesso

Execute o projeto e acesse [https://localhost:8080/hemoblas](https://localhost:8080/hemoblas). Você será redirecionado para a tela de login:

![login](https://github.com/brunoleonardo/poc_hemoblas/blob/master/login.png)

Para criar um usuário, basta clicar no link **Registrar**. Você será redirecionado para a Tela para de Cadastro de Doador.

![cadastroDoador](https://github.com/brunoleonardo/poc_hemoblas/blob/master/cadastroDoador.png)

Após cadastrado, basta realizar login no sistema e agendar uma doação.

![agendarDoacao](https://github.com/brunoleonardo/poc_hemoblas/blob/master/agendarDoacao.png)


