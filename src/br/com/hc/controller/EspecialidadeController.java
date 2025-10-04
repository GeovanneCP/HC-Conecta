package br.com.hc.controller;

import java.util.Scanner;

import br.com.hc.dao.EspecialidadeDAO;
import br.com.hc.model.Especialidade;

public class EspecialidadeController {
    private Scanner scanner;
    private EspecialidadeDAO especialidadeDAO;
    
    public EspecialidadeController(Scanner scanner) {
        this.scanner = scanner;
        this.especialidadeDAO = new EspecialidadeDAO();
    }
    
    public void menuEspecialidade() {
        int opcao;
        do {
            System.out.println("\n=== Especialidade (CRUD) ===");
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
                    criarEspecialidade();
                    break;
                case 2:
                    listarEspecialidades();
                    break;
                case 3:
                    atualizarEspecialidade();
                    break;
                case 4:
                    removerEspecialidade();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarEspecialidade() {
        System.out.println("\n=== Criar Especialidade ===");
        System.out.print("Nome da Especialidade: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        Especialidade especialidade = new Especialidade();
        especialidade.setNomeEspecialidade(nome);
        especialidade.setDescricao(descricao);
        
        especialidadeDAO.adicionar(especialidade);
        System.out.println("Especialidade criada com sucesso!");
    }

    private void listarEspecialidades() {
        System.out.println("\n=== Lista de Especialidades ===");
        var especialidades = especialidadeDAO.obterTodasEspecialidades();
        if (especialidades.isEmpty()) {
            System.out.println("Não há especialidades cadastradas.");
            return;
        }
        for (Especialidade esp : especialidades) {
            System.out.println("ID: " + esp.getIdEspecialidade() + 
                             " | Nome: " + esp.getNomeEspecialidade() + 
                             " | Descrição: " + esp.getDescricao());
            System.out.println("-----------------------------");
        }
    }

    private void atualizarEspecialidade() {
        System.out.println("\n=== Atualizar Especialidade ===");
        System.out.print("ID da Especialidade: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (!especialidadeDAO.idExiste(id)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("Nome da Especialidade: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        Especialidade especialidade = new Especialidade();
        especialidade.setIdEspecialidade(id);
        especialidade.setNomeEspecialidade(nome);
        especialidade.setDescricao(descricao);
        
        boolean ok = especialidadeDAO.atualizar(especialidade);
        System.out.println(ok ? "Especialidade atualizada com sucesso!" : "Falha ao atualizar especialidade.");
    }

    private void removerEspecialidade() {
        System.out.println("\n=== Remover Especialidade ===");
        System.out.print("ID da Especialidade: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean ok = especialidadeDAO.removerPorId(id);
        System.out.println(ok ? "Especialidade removida com sucesso!" : "Falha ao remover especialidade.");
    }
}
