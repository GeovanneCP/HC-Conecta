
package br.com.hc.view;

import br.com.hc.controller.ChatBot;
import br.com.hc.controller.Sistema;

public class Main {
    public static void main(String[] args) {
        try {
            Sistema sistema = new Sistema();
            ChatBot chatBot = new ChatBot();
            
            System.out.println("=== Bem-vindo ao HC Conecta ===\n");
            
            System.out.println("Testando ChatBot:");
            System.out.println("Pergunta: Como criar uma consulta?");
            String resposta = chatBot.responder("Como criar uma consulta?");
            System.out.println("Resposta: " + resposta + "\n");
            
            System.out.println("Iniciando sistema principal...\n");
            sistema.menu();
            
            
        } catch (Exception e) {
            System.out.println("Erro ao executar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 