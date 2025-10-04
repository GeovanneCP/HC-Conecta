package br.com.hc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.hc.dao.PacienteDAO;
import br.com.hc.model.Paciente;

public class PacienteController {
    private Scanner scanner;
    private PacienteDAO pacienteDAO;
    
    public PacienteController(Scanner scanner) {
        this.scanner = scanner;
        this.pacienteDAO = new PacienteDAO();
    }
    
    public void menuPaciente() {
        int opcao;
        do {
            System.out.println("\n=== Paciente (CRUD) ===");
            System.out.println("1. Criar (POST)");
            System.out.println("2. Listar (GET)");
            System.out.println("3. Atualizar (PUT)");
            System.out.println("4. Remover (DELETE)");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1: criarPaciente(); break;
                case 2: listarPacientes(); break;
                case 3: atualizarPaciente(); break;
                case 4: removerPaciente(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarPaciente() {
        System.out.println("\n=== Cadastro de Paciente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (dd-MM-yyyy): ");
        String input = scanner.nextLine();
        LocalDate dtNasc = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        Paciente paciente = new Paciente(nome, cpf, dtNasc);
        pacienteDAO.adicionar(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }
    
    private void listarPacientes() {
        System.out.println("\n=== Lista de Pacientes ===");
        var lista = pacienteDAO.obterTodosPacientes();
        if (lista.isEmpty()) {
            System.out.println("Não há pacientes cadastrados.");
            return;
        }
        for (Paciente p : lista) {
            System.out.println(p.getInfo());
            System.out.println("-----------------------------");
        }
    }

    private void atualizarPaciente() {
        System.out.println("\n=== Atualizar Paciente ===");
        System.out.print("ID do Paciente: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (!pacienteDAO.idExiste(id)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (dd-MM-yyyy): ");
        String input = scanner.nextLine();
        LocalDate dtNasc = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Paciente paciente = new Paciente(id,nome, cpf, dtNasc);
        boolean ok = pacienteDAO.atualizar(paciente);
        System.out.println(ok ? "Paciente atualizado com sucesso!" : "Falha ao atualizar paciente.");
    }

    private void removerPaciente() {
        System.out.println("\n=== Remover Paciente ===");
        System.out.print("ID do Paciente: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean ok = pacienteDAO.removerPorId(id);
        System.out.println(ok ? "Paciente removido com sucesso!" : "Falha ao remover paciente.");
    }
}
