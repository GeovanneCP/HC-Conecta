package br.com.hc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.hc.dao.LoginDAO;
import br.com.hc.model.Login;

public class LoginController {
    private Scanner scanner;
    private LoginDAO loginDAO;
    
    public LoginController(Scanner scanner) {
        this.scanner = scanner;
        this.loginDAO = new LoginDAO();
    }
    
    public void menuLogin() {
        int opcao;
        do {
            System.out.println("\n=== Login (CRUD) ===");
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
                    criarLogin();
                    break;
                case 2:
                    listarLogins();
                    break;
                case 3:
                    atualizarLogin();
                    break;
                case 4:
                    removerLogin();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void criarLogin() { 
        System.out.println("\n=== Criar Login ===");
        System.out.print("ID Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Nome de Usuario: ");
        String loginn = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Data Criação do login (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime dataRegistro = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        System.out.print("Data ultima atualização do login (dd/MM/yyyy HH:mm): ");
        String dataUltAt = scanner.nextLine();
        LocalDateTime dataAtualizacao = LocalDateTime.parse(dataUltAt, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        Login login = new Login();
        login.setIdPaciente(idPaciente);
        login.setLogin(loginn);
        login.setSenha(senha);
        login.setDataCriacao(dataRegistro);
        login.setDataAtualizacao(dataAtualizacao);
        
        loginDAO.adicionar(login);
        System.out.println("Login criado com sucesso!");
    }
    
    private void listarLogins() {
        System.out.println("\n=== Lista de Logins ===");
        var logins = loginDAO.obterTodosLogins();
        if (logins.isEmpty()) {
            System.out.println("Não há Logins cadastrados.");
            return;
        }
        for (Login login : logins) {
            System.out.println(" | idPaciente: " + login.getIdPaciente() + 
                             " | Email: " + login.getLogin() + 
                             " | Senha: " + login.getSenha() +
                             " | Data Criação: " + login.getDataCriacao() +
                             " | Data Atualização: " + login.getDataAtualizacao());
            System.out.println("-----------------------------");
        }
    }
    
    private void atualizarLogin() { 
        System.out.println("\n=== Atualizar Login ===");
        System.out.print("ID do Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        if (!loginDAO.idExiste(idPaciente)) {
            System.out.println("ID não encontrado.");
            return;
        }
        System.out.print("Nome de Usuario: ");
        String login = scanner.nextLine();
        System.out.print("senha: ");
        String senha = scanner.nextLine();
        System.out.print("Data Criação do login (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime dataCriacao = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        System.out.print("Data ultima atualização do login (dd/MM/yyyy HH:mm): ");
        String dataUltAt = scanner.nextLine();
        LocalDateTime dataAtualizacao = LocalDateTime.parse(dataUltAt, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        Login loginn = new Login();
        loginn.setIdPaciente(idPaciente);
        loginn.setLogin(login);
        loginn.setSenha(senha);
        loginn.setDataCriacao(dataCriacao);
        loginn.setDataAtualizacao(dataAtualizacao);
        
        boolean ok = loginDAO.atualizar(loginn);
        System.out.println(ok ? "Login atualizado com sucesso!" : "Falha ao atualizar login.");
    }
    
    private void removerLogin() {  
        System.out.println("\n=== Remover Login ===");
        System.out.print("ID do Paciente: ");
        Long idPaciente = scanner.nextLong();
        scanner.nextLine();
        boolean ok = loginDAO.removerPorId(idPaciente);
        System.out.println(ok ? "Login removido com sucesso!" : "Falha ao remover login.");
    }
}
