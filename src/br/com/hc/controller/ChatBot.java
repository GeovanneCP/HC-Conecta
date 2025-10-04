package br.com.hc.controller;

import java.util.ArrayList;

import br.com.hc.utils.AudioTTS;
import br.com.hc.utils.MensagemFAQ;

public class ChatBot {
	private ArrayList<MensagemFAQ> mensagens;
	private AudioTTS audio;
	
	public ChatBot() {
		this.mensagens = new ArrayList<>();
		this.audio = new AudioTTS();
		inicializarMensagens();
	}
	
	private void inicializarMensagens() {
		// Mensagens relacionadas a consultas (somente para testes)
		mensagens.add(new MensagemFAQ(
			"Como criar uma consulta?",
			"Para criar uma consulta, acesse o menu principal e selecione a opção 2 - Menu de Consulta. " +
			"Após isso você acessara o menu de consultas e pode criar a sua consulta"
		));
		
		mensagens.add(new MensagemFAQ(
			"Como ver minhas consultas?",
			"Para ver suas consultas, acesse o menu principal e selecione a opção 4 - Listar Consultas. " +
			"Digite seu CPF quando solicitado para ver todas as suas consultas agendadas."
		));
		
		// Mensagens relacionadas a cadastro
		mensagens.add(new MensagemFAQ(
			"Como me cadastrar?",
			"Para se cadastrar, acesse o menu principal e escolha a opção 1 - Cadastrar Paciente. " +
			"Você precisará fornecer seus dados pessoais como nome, CPF, email e informações específicas."
		));
	}
	
	public String responder(String pergunta) {
		
		// Procura por uma resposta que corresponda à pergunta
		for (MensagemFAQ msg : mensagens) {
			pergunta = pergunta.toLowerCase();
			
			if (msg.getPergunta().toLowerCase().contains(pergunta) || 
				pergunta.contains(msg.getPergunta().toLowerCase())) {
				audio.falarTexto(msg.getResposta());
				return msg.getResposta();
			}
		}
		
		return "Desculpe, não encontrei uma resposta para sua pergunta. " +
			   "Por favor, tente reformular ou entre em contato com nossa equipe de suporte pelo telefone do HC.";
	}
	
	public void adicionarMensagem(String pergunta, String resposta) {
		mensagens.add(new MensagemFAQ(pergunta, resposta));
	}
}
