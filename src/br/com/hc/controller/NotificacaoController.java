package br.com.hc.controller;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.hc.model.Notificacao;

public class NotificacaoController {
    private Scanner scanner;
    private ArrayList<Notificacao> notificacoes;
    
    public NotificacaoController(Scanner scanner) {
        this.scanner = scanner;
        this.notificacoes = new ArrayList<>();
    }
    
    public void gerenciarNotificacoes() {
        System.out.println("\n=== Notificações ===");
        if (notificacoes.isEmpty()) {
            System.out.println("Não há notificações.");
            return;
        }
        
        for (Notificacao notificacao : notificacoes) {
            System.out.println(notificacao);
        }
    }
    
    public void adicionarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
    }
    
    public ArrayList<Notificacao> getNotificacoes() {
        return notificacoes;
    }
}
