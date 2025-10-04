package br.com.hc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.hc.dao.ConsultaDAO;
import br.com.hc.model.Consulta;

public class ConsultaController {
    private Scanner scanner;
    private ConsultaDAO consultaDAO;
    
    public ConsultaController(Scanner scanner) {
        this.scanner = scanner;
        this.consultaDAO = new ConsultaDAO();
    }
    
    public void menuConsulta() {
        int opcao;
        do {
            System.out.println("\n=== Consulta (CRUD) ===");
            System.out.println("1. Criar (POST)");
            System.out.println("2. Listar todas (GET)");
            System.out.println("3. Atualizar (PUT)");
            System.out.println("4. Remover (DELETE)");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    criarConsulta();
                    break;
                case 2:
                    listarConsultas();
                    break;
                case 3:
                    atualizarConsulta();
                    break;
                case 4:
                    removerConsulta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarConsulta() {
        System.out.println("\n=== Criar Consulta ===");
        System.out.print("ID Agendamento: ");
        Long idAgendamento = scanner.nextLong();
        System.out.print("ID Médico: ");
        Long idMedico = scanner.nextLong();
        System.out.print("ID Unidade: ");
        Long idUnidade = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Tipo Atendimento('PRESENCIAL','ONLINE'): ");
        String tipoAtendimento = scanner.nextLine().trim().toUpperCase();
        System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        Consulta consulta = new Consulta();
        consulta.setIdAgendamento(idAgendamento);
        consulta.setIdMedico(idMedico);
        consulta.setIdUnidade(idUnidade);
        consulta.setTipoAtendimento(tipoAtendimento);
        consulta.setDataHora(dataHora);
        
        consultaDAO.adicionar(consulta);
        System.out.println("Consulta criada com sucesso!");
    }
    
    public void listarConsultas() {
        var todasConsultas = consultaDAO.obterTodasConsultas();
        if (todasConsultas.isEmpty()) {
            System.out.println("Não há consultas cadastradas.");
            return;
        }
        for (Consulta consulta : todasConsultas) {
            System.out.println(consulta);
            System.out.println("-----------------------------");
        }
    }

    private void atualizarConsulta() {
        System.out.println("\n=== Atualizar Consulta ===");
        System.out.print("ID da Consulta: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (!consultaDAO.idExiste(id)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("ID Agendamento: ");
        Long idAgendamento = scanner.nextLong();
        System.out.print("ID Médico: ");
        Long idMedico = scanner.nextLong();
        System.out.print("ID Unidade: ");
        Long idUnidade = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Tipo Atendimento('PRESENCIAL','ONLINE'): ");
        String tipoAtendimento = scanner.nextLine().trim().toUpperCase();
        System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(id);
        consulta.setIdAgendamento(idAgendamento);
        consulta.setIdMedico(idMedico);
        consulta.setIdUnidade(idUnidade);
        consulta.setTipoAtendimento(tipoAtendimento);
        consulta.setDataHora(dataHora);
        
        boolean ok = consultaDAO.atualizar(consulta);
        System.out.println(ok ? "Consulta atualizada com sucesso!" : "Falha ao atualizar consulta.");
    }

    private void removerConsulta() {
        System.out.println("\n=== Remover Consulta ===");
        System.out.print("ID da Consulta: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean ok = consultaDAO.removerPorId(id);
        System.out.println(ok ? "Consulta removida com sucesso!" : "Falha ao remover consulta.");
    }
}
