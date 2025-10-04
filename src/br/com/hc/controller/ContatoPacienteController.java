package br.com.hc.controller;

import java.util.Scanner;

import br.com.hc.dao.ContatoPacienteDAO;
import br.com.hc.model.ContatoPaciente;

public class ContatoPacienteController {
    private Scanner scanner;
    private ContatoPacienteDAO contatoPacienteDAO;
    
    public ContatoPacienteController(Scanner scanner) {
        this.scanner = scanner;
        this.contatoPacienteDAO = new ContatoPacienteDAO();
    }
    
    public void menuContatoPaciente() {
        int opcao;
        do {
            System.out.println("\n=== ContatoPaciente (CRUD) ===");
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
                    criarContatoPaciente();
                    break;
                case 2:
                    listarContatosPaciente();
                    break;
                case 3:
                    atualizarContatoPaciente();
                    break;
                case 4:
                    removerContatoPaciente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarContatoPaciente() {
        System.out.println("\n=== Criar ContatoPaciente ===");
        System.out.print("ID Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        ContatoPaciente contato = new ContatoPaciente();
        contato.setIdPaciente(idPaciente);
        contato.setTelefone(telefone);
        contato.setEmail(email);
        
        contatoPacienteDAO.adicionar(contato);
        System.out.println("ContatoPaciente criado com sucesso!");
    }

    private void listarContatosPaciente() {
        System.out.println("\n=== Lista de ContatosPaciente ===");
        var contatos = contatoPacienteDAO.obterTodosContatos();
        if (contatos.isEmpty()) {
            System.out.println("Não há contatos cadastrados.");
            return;
        }
        for (ContatoPaciente contato : contatos) {
            System.out.println("ID: " + contato.getIdContato() + 
                             " | idPaciente: " + contato.getIdPaciente() + 
                             " | Telefone: " + contato.getTelefone() + 
                             " | Email: " + contato.getEmail());
            System.out.println("-----------------------------");
        }
    }

    private void atualizarContatoPaciente() {
        System.out.println("\n=== Atualizar ContatoPaciente ===");
        System.out.print("ID do Contato: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (!contatoPacienteDAO.idExiste(id)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("ID Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        ContatoPaciente contato = new ContatoPaciente();
        contato.setIdContato(id);
        contato.setIdPaciente(idPaciente);
        contato.setTelefone(telefone);
        contato.setEmail(email);
        
        boolean ok = contatoPacienteDAO.atualizar(contato);
        System.out.println(ok ? "ContatoPaciente atualizado com sucesso!" : "Falha ao atualizar contato.");
    }

    private void removerContatoPaciente() {
        System.out.println("\n=== Remover ContatoPaciente ===");
        System.out.print("ID do Contato: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean ok = contatoPacienteDAO.removerPorId(id);
        System.out.println(ok ? "ContatoPaciente removido com sucesso!" : "Falha ao remover contato.");
    }
}
