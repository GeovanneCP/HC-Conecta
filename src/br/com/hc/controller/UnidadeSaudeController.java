package br.com.hc.controller;

import java.util.Scanner;

import br.com.hc.dao.UnidadeSaudeDAO;
import br.com.hc.model.UnidadeSaude;

public class UnidadeSaudeController {
    private Scanner scanner;
    private UnidadeSaudeDAO unidadeSaudeDAO;
    
    public UnidadeSaudeController(Scanner scanner) {
        this.scanner = scanner;
        this.unidadeSaudeDAO = new UnidadeSaudeDAO();
    }
    
    public void menuUnidadeSaude() {
        int opcao;
        do {
            System.out.println("\n=== UnidadeSaude (CRUD) ===");
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
                    criarUnidadeSaude();
                    break;
                case 2:
                    listarUnidadeSaude();
                    break;
                case 3:
                    atualizarUnidadeSaude();
                    break;
                case 4:
                    removerUnidadeSaude();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarUnidadeSaude() { 
        System.out.println("\n=== Criar Unidade de Saude ===");
        System.out.print("nome da Unidade de saude: ");
        String nomeUnidadeSaude = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        
        UnidadeSaude unidadeSaude = new UnidadeSaude();
        unidadeSaude.setNomeUnidadeSaude(nomeUnidadeSaude);
        unidadeSaude.setEndereco(endereco);
        unidadeSaudeDAO.adicionar(unidadeSaude);
        System.out.println("Unidade de saude criada com sucesso!");
    }
    
    private void listarUnidadeSaude() { 
        System.out.println("\n=== Lista de Unidades ===");
        var unidades = unidadeSaudeDAO.obterTodasUnidades();
        if (unidades.isEmpty()) {
            System.out.println("Não há Unidades cadastradas.");
            return;
        }
        for (UnidadeSaude unidadeSaude : unidades) {
            System.out.println("idUnidadeSaude: " + unidadeSaude.getIdUnidadeSaude() + 
                             " | nome Unidade de saude: " + unidadeSaude.getNomeUnidadeSaude() + 
                             " | Endereço da unidade: " + unidadeSaude.getEndereco()); 
            System.out.println("-----------------------------");
        }
    }
    
    private void atualizarUnidadeSaude() { 
        System.out.println("\n=== Atualizar Unidade de Saude ===");
        System.out.print("ID da Unidade: ");
        Long idUnidade = scanner.nextLong();
        scanner.nextLine();
        if (!unidadeSaudeDAO.idExiste(idUnidade)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("nome da Unidade de saude: ");
        String nomeUnidadeSaude = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        
        UnidadeSaude unidadeSaude = new UnidadeSaude();
        unidadeSaude.setIdUnidadeSaude(idUnidade);
        unidadeSaude.setNomeUnidadeSaude(nomeUnidadeSaude);
        unidadeSaude.setEndereco(endereco);
        boolean ok = unidadeSaudeDAO.atualizar(unidadeSaude);
        System.out.println(ok ? "Unidade de saude atualizada com sucesso!" : "Falha ao atualizar Unidade de saude.");
    }
    
    private void removerUnidadeSaude() { 
        System.out.println("\n=== Remover Unidade de Saude ===");
        System.out.print("ID da Unidade: ");
        Long idUnidadeSaude = scanner.nextLong();
        scanner.nextLine();
        boolean ok = unidadeSaudeDAO.removerPorId(idUnidadeSaude);
        System.out.println(ok ? "Unidade de Saúde removida com sucesso!" : "Falha ao remover Unidade de Saúde .");
    }
}
