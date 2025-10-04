README - HC Conecta
Descrição
HC Conecta é um sistema de gerenciamento para clínicas e hospitais, desenvolvido em Java, que permite o cadastro e controle de pacientes, médicos, consultas, agendamentos, notificações e outras entidades relacionadas à área da saúde. O sistema utiliza JDBC para conexão com banco de dados Oracle.

Estrutura do Projeto
Funcionalidades
Cadastro, listagem, atualização e remoção de:
Pacientes
Médicos
Consultas
Agendamentos
Contatos de pacientes
Diagnósticos
Especialidades
Histórico de consultas
Unidades de saúde
Logins
Notificações (texto e áudio)
ChatBot FAQ para dúvidas rápidas
Testes unitários para conexão com banco
Como Executar
Pré-requisitos:

Java 21+
Oracle JDBC Driver (ojdbc11-23.9.0.25.07.jar)
Banco Oracle disponível (configuração em br.com.hc.dao.ConnectionFactory)
Compilação:

Compile o projeto usando sua IDE (Eclipse/IntelliJ) ou via terminal:
Execução:

Execute a classe principal:
Testes:

Os testes unitários estão em Test/br/com/hc/banco/ConnectionFactoryTest.java e podem ser executados com JUnit 5.
Observações
As operações CRUD são realizadas via menus interativos no console.
O ChatBot responde perguntas frequentes sobre o uso do sistema.
As credenciais do banco estão em ConnectionFactory e devem ser ajustadas conforme seu ambiente.
Licença
Este projeto é apenas para fins acadêmicos.

Autores:
Equipe HC Conecta

Para dúvidas, consulte o FAQ do ChatBot ou entre em contato com o suporte do HC.4. Testes:

Os testes unitários estão em Test/br/com/hc/banco/ConnectionFactoryTest.java e podem ser executados com JUnit 5.
Observações
As operações CRUD são realizadas via menus interativos no console.
O ChatBot responde perguntas frequentes sobre o uso do sistema.
As credenciais do banco estão em ConnectionFactory e devem ser ajustadas conforme seu ambiente.
Licença
Este projeto é apenas para fins acadêmicos.
