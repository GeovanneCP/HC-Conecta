package br.com.hc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.hc.dao.AgendamentoDAO;
import br.com.hc.model.Agendamento;

public class AgendamentoController {
    private Scanner scanner;
    private AgendamentoDAO agendamentoDAO;
    
    public AgendamentoController(Scanner scanner) {
        this.scanner = scanner;
        this.agendamentoDAO = new AgendamentoDAO();
    }
    
    public void menuAgendamento() {
        int opcao;
        do {
            System.out.println("\n=== Agendamento (CRUD) ===");
            System.out.println("1. Criar (POST)");
            System.out.println("2. Listar todos (GET)");
            System.out.println("3. Atualizar (PUT)");
            System.out.println("4. Remover (DELETE)");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    criarAgendamento();
                    break;
                case 2:
                    listarAgendamentos();
                    break;
                case 3:
                    atualizarAgendamento();
                    break;
                case 4:
                    removerAgendamento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarAgendamento() {
        System.out.println("\n=== Criar Agendamento ===");
        System.out.print("ID Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        
        System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        System.out.print("Status ('PENDENTE','CONFIRMADO','CANCELADO'): ");
        String status = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("Notificado (S/N): ");
        String notificado = scanner.nextLine().trim().toUpperCase();
        
        LocalDateTime dtHrNt = null; 
        if (notificado.equals("S")) {
            System.out.print("Data e Hora que o paciente foi notificado (dd/MM/yyyy HH:mm): ");
            String dataHoraNotifi = scanner.nextLine();
            dtHrNt = LocalDateTime.parse(dataHoraNotifi, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setIdPaciente(idPaciente);
        agendamento.setDataHora(dataHora);
        agendamento.setStatus(status);
        agendamento.setNotificado(notificado);
        agendamento.setDataHoraNotificacao(dtHrNt); // se não for notificado,campo fica null
        
        agendamentoDAO.adicionar(agendamento);
        System.out.println("Agendamento criado com sucesso!");
    }

    private void listarAgendamentos() {
        System.out.println("\n=== Lista de Agendamentos ===");
        var agendamentos = agendamentoDAO.obterTodosAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Não há agendamentos cadastrados.");
            return;
        }
        for (Agendamento ag : agendamentos) {
            System.out.println("ID: " + ag.getIdAgendamento() + 
                             " | Paciente: " + ag.getIdPaciente() + 
                             " | Data: " + ag.getDataHora() + 
                             " | Status: " + ag.getStatus() +
                             " | Notificado?: " + ag.getNotificado()); 
            System.out.println("-----------------------------");
        }
    }

    private void atualizarAgendamento() {
        System.out.println("\n=== Atualizar Agendamento ===");
        System.out.print("ID do Agendamento: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (!agendamentoDAO.idExiste(id)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("ID Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.print("Status: ");
        String status = scanner.nextLine().trim().toUpperCase();
        System.out.print("Notificado (S/N): ");
        String notificado = scanner.nextLine().trim().toUpperCase();
        
        Agendamento agendamento = new Agendamento();
        agendamento.setIdAgendamento(id);
        agendamento.setIdPaciente(idPaciente);
        agendamento.setDataHora(dataHora);
        agendamento.setStatus(status);
        agendamento.setNotificado(notificado);
        
        boolean ok = agendamentoDAO.atualizar(agendamento);
        System.out.println(ok ? "Agendamento atualizado com sucesso!" : "Falha ao atualizar agendamento.");
    }

    private void removerAgendamento() {
        System.out.println("\n=== Remover Agendamento ===");
        System.out.print("ID do Agendamento: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean ok = agendamentoDAO.removerPorId(id);
        System.out.println(ok ? "Agendamento removido com sucesso!" : "Falha ao remover agendamento.");
    }
}
