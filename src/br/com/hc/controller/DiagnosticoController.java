package br.com.hc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.hc.dao.DiagnosticoDAO;
import br.com.hc.model.Diagnostico;

public class DiagnosticoController {
    private Scanner scanner;
    private DiagnosticoDAO diagnosticoDAO;
    
    public DiagnosticoController(Scanner scanner) {
        this.scanner = scanner;
        this.diagnosticoDAO = new DiagnosticoDAO();
    }
    
    public void menuDiagnostico() {
        int opcao;
        do {
            System.out.println("\n=== Diagnostico (CRUD) ===");
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
                    criarDiagnostico();
                    break;
                case 2:
                    listarDiagnosticos();
                    break;
                case 3:
                    atualizarDiagnostico();
                    break;
                case 4:
                    removerDiagnostico();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarDiagnostico() {
        System.out.println("\n=== Criar Diagnostico ===");
        System.out.print("ID Consulta: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data Registro (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime dataRegistro = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdConsulta(idConsulta);
        diagnostico.setDescricao(descricao);
        diagnostico.setDataRegistro(dataRegistro);
        
        diagnosticoDAO.adicionar(diagnostico);
        System.out.println("Diagnostico criado com sucesso!");
    }

    private void listarDiagnosticos() {
        System.out.println("\n=== Lista de Diagnosticos ===");
        var diagnosticos = diagnosticoDAO.obterTodosDiagnosticos();
        if (diagnosticos.isEmpty()) {
            System.out.println("Não há diagnosticos cadastrados.");
            return;
        }
        for (Diagnostico diag : diagnosticos) {
            System.out.println("ID: " + diag.getIdDiagnostico() + 
                             " | Consulta: " + diag.getIdConsulta() + 
                             " | Descrição: " + diag.getDescricao() + 
                             " | Data: " + diag.getDataRegistro());
            System.out.println("-----------------------------");
        }
    }

    private void atualizarDiagnostico() {
        System.out.println("\n=== Atualizar Diagnostico ===");
        System.out.print("ID do Diagnostico: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (!diagnosticoDAO.idExiste(id)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("ID Consulta: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data Registro (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime dataRegistro = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(id);
        diagnostico.setIdConsulta(idConsulta);
        diagnostico.setDescricao(descricao);
        diagnostico.setDataRegistro(dataRegistro);
        
        boolean ok = diagnosticoDAO.atualizar(diagnostico);
        System.out.println(ok ? "Diagnostico atualizado com sucesso!" : "Falha ao atualizar diagnostico.");
    }

    private void removerDiagnostico() {
        System.out.println("\n=== Remover Diagnostico ===");
        System.out.print("ID do Diagnostico: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean ok = diagnosticoDAO.removerPorId(id);
        System.out.println(ok ? "Diagnostico removido com sucesso!" : "Falha ao remover diagnostico.");
    }
}
