package br.com.hc.controller;

import java.util.Scanner;

import br.com.hc.dao.MedicoDAO;
import br.com.hc.model.Medico;

public class MedicoController {
    private Scanner scanner;
    private MedicoDAO medicoDAO;
    
    public MedicoController(Scanner scanner) {
        this.scanner = scanner;
        this.medicoDAO = new MedicoDAO();
    }
    
    public void menuMedico() {
        int opcao;
        do {
            System.out.println("\n=== Medico (CRUD) ===");
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
                    criarMedico();
                    break;
                case 2:
                    listarMedicos();
                    break;
                case 3:
                    atualizarMedico();
                    break;
                case 4:
                    removerMedico();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarMedico() { 
        System.out.println("\n=== Criar Medico ===");
        System.out.print("nome: ");
        String nome = scanner.nextLine();
        System.out.print("Crm do medico: ");
        String crmMedico = scanner.nextLine();
        
        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setCrmMedico(crmMedico);
        medicoDAO.adicionar(medico);
        System.out.println("Medico criado com sucesso!");
    }
    
    private void listarMedicos() {
        System.out.println("\n=== Lista de Medicos ===");
        var medicos = medicoDAO.obterTodosMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Não há Medicos cadastrados.");
            return;
        }
        for (Medico medico : medicos) {
            System.out.println("idMedico: " + medico.getIdMedico() + 
                             " | nome: " + medico.getNome() + 
                             " | crmMedico: " + medico.getCrmMedico());
            System.out.println("-----------------------------");
        }
    }
    
    private void atualizarMedico() { 
        System.out.println("\n=== Atualizar Medico ===");
        System.out.print("ID do Medico: ");
        Long idMedico = scanner.nextLong();
        scanner.nextLine();
        if (!medicoDAO.idExiste(idMedico)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("nome: ");
        String nome = scanner.nextLine();
        System.out.print("Crm do medico: ");
        String crmMedico = scanner.nextLine();
        
        Medico medico = new Medico();
        medico.setIdMedico(idMedico);
        medico.setNome(nome);
        medico.setCrmMedico(crmMedico);
        boolean ok = medicoDAO.atualizar(medico);
        System.out.println(ok ? "Medico atualizado com sucesso!" : "Falha ao atualizar Medico.");
    }
    
    private void removerMedico() {
        System.out.println("\n=== Remover Medico ===");
        System.out.print("ID do Medico: ");
        Long idMedico = scanner.nextLong();
        scanner.nextLine();
        boolean ok = medicoDAO.removerPorId(idMedico);
        System.out.println(ok ? "Médico removido com sucesso!" : "Falha ao remover Médico.");
    }
}
