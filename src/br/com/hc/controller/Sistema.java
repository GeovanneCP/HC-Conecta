package br.com.hc.controller;

import java.util.Scanner;

public class Sistema {
    private Scanner scanner;
    private PacienteController pacienteController;
    private ConsultaController consultaController;
    private AgendamentoController agendamentoController;
    private ContatoPacienteController contatoPacienteController;
    private DiagnosticoController diagnosticoController;
    private EspecialidadeController especialidadeController;
    private HistoricoConsultaController historicoConsultaController;
    private LoginController loginController;
    private MedicoController medicoController;
    private UnidadeSaudeController unidadeSaudeController;
    private NotificacaoController notificacaoController;
    
    public Sistema() {
        scanner = new Scanner(System.in);
        pacienteController = new PacienteController(scanner);
        consultaController = new ConsultaController(scanner);
        agendamentoController = new AgendamentoController(scanner);
        contatoPacienteController = new ContatoPacienteController(scanner);
        diagnosticoController = new DiagnosticoController(scanner);
        especialidadeController = new EspecialidadeController(scanner);
        historicoConsultaController = new HistoricoConsultaController(scanner);
        loginController = new LoginController(scanner);
        medicoController = new MedicoController(scanner);
        unidadeSaudeController = new UnidadeSaudeController(scanner);
        notificacaoController = new NotificacaoController(scanner);
    }
    
    public void menu() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 1:
                    pacienteController.menuPaciente();
                    break;
                case 2:
                    consultaController.menuConsulta();
                    break;
                case 3:
                    agendamentoController.menuAgendamento();
                    break;
                case 4:
                    contatoPacienteController.menuContatoPaciente();
                    break;
                case 5:
                    diagnosticoController.menuDiagnostico();
                    break;
                case 6:
                    especialidadeController.menuEspecialidade();
                    break;
                case 7:
                    historicoConsultaController.menuHistoricoConsulta();
                    break;
                case 8:
                    loginController.menuLogin();
                    break;
                case 9:
                    medicoController.menuMedico();
                    break;
                case 10:
                    unidadeSaudeController.menuUnidadeSaude();
                    break;
                case 11:
                    notificacaoController.gerenciarNotificacoes();
                    break;
                case 0:
                    System.out.println("encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }
    
    private void exibirMenu() {
        System.out.println("\n=== Sistema HC Conecta (CRUD) ===");
        System.out.println("1. Pacientes (CRUD)");
        System.out.println("2. Consultas (CRUD)");
        System.out.println("3. Agendamento (CRUD)");
        System.out.println("4. ContatoPaciente (CRUD)");
        System.out.println("5. Diagnostico (CRUD)");
        System.out.println("6. Especialidade (CRUD)");
        System.out.println("7. HistoricoConsulta (CRUD)");
        System.out.println("8. Login (CRUD)");
        System.out.println("9. Medico (CRUD)");
        System.out.println("10. UnidadeSaude (CRUD)");
        System.out.println("11. Gerenciar Notificações");
        System.out.println("0. Sair");
        System.out.println("========================");
        System.out.print("Escolha uma opção: ");
    }
    }
