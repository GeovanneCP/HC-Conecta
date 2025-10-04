package br.com.hc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.hc.dao.HistoricoConsultaDAO;
import br.com.hc.model.HistoricoConsulta;

public class HistoricoConsultaController {
    private Scanner scanner;
    private HistoricoConsultaDAO historicoConsultaDAO;
    
    public HistoricoConsultaController(Scanner scanner) {
        this.scanner = scanner;
        this.historicoConsultaDAO = new HistoricoConsultaDAO();
    }
    
    public void menuHistoricoConsulta() {
        int opcao;
        do {
            System.out.println("\n=== HistoricoConsulta (CRUD) ===");
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
                    criarHistoricoConsulta();
                    break;
                case 2:
                    listarHistoricosConsulta();
                    break;
                case 3:
                    atualizarHistoricoConsulta();
                    break;
                case 4:
                    removerHistoricoConsulta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarHistoricoConsulta() {
        System.out.println("\n=== Criar HistoricoConsulta ===");
        System.out.print("ID Consulta: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Status Final: ");
        String statusFinal = scanner.nextLine();
        System.out.print("Observações: ");
        String observacoes = scanner.nextLine();
        System.out.print("Data Registro (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime dataRegistro = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        HistoricoConsulta historico = new HistoricoConsulta();
        historico.setIdConsulta(idConsulta);
        historico.setStatusFinal(statusFinal);
        historico.setObservacoes(observacoes);
        historico.setDataRegistro(dataRegistro);
        
        historicoConsultaDAO.adicionar(historico);
        System.out.println("HistoricoConsulta criado com sucesso!");
    }

    private void listarHistoricosConsulta() {
        System.out.println("\n=== Lista de HistoricosConsulta ===");
        var historicos = historicoConsultaDAO.obterTodosHistoricos();
        if (historicos.isEmpty()) {
            System.out.println("Não há históricos cadastrados.");
            return;
        }
        for (HistoricoConsulta hist : historicos) {
            System.out.println("Consulta: " + hist.getIdConsulta() + 
                             " | Status: " + hist.getStatusFinal() + 
                             " | Observações: " + hist.getObservacoes() + 
                             " | Data: " + hist.getDataRegistro());
            System.out.println("-----------------------------");
        }
    }

    private void atualizarHistoricoConsulta() {
        System.out.println("\n=== Atualizar HistoricoConsulta ===");
        System.out.print("ID da Consulta: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();
        if (!historicoConsultaDAO.idExiste(idConsulta)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("Status Final: ");
        String statusFinal = scanner.nextLine();
        System.out.print("Observações: ");
        String observacoes = scanner.nextLine();
        System.out.print("Data Registro (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime dataRegistro = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        HistoricoConsulta historico = new HistoricoConsulta();
        historico.setIdConsulta(idConsulta);
        historico.setStatusFinal(statusFinal);
        historico.setObservacoes(observacoes);
        historico.setDataRegistro(dataRegistro);
        
        boolean ok = historicoConsultaDAO.atualizar(historico);
        System.out.println(ok ? "HistoricoConsulta atualizado com sucesso!" : "Falha ao atualizar histórico.");
    }

    private void removerHistoricoConsulta() {
        System.out.println("\n=== Remover HistoricoConsulta ===");
        System.out.print("ID da Consulta: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();
        boolean ok = historicoConsultaDAO.removerPorId(idConsulta);
        System.out.println(ok ? "HistoricoConsulta removido com sucesso!" : "Falha ao remover histórico.");
    }
}
