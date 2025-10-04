package br.com.hc.model;

import java.time.LocalDateTime;

import br.com.hc.utils.AudioTTS;

public class Notificacao {
	private String mensagem;
	private String tipoDeEnvio;
	private LocalDateTime enviadaEm;
	
	public Notificacao(String mensagem, String tipoDeEnvio, LocalDateTime enviadaEm) {
		this.mensagem = mensagem;
		this.tipoDeEnvio = tipoDeEnvio;
		this.enviadaEm = enviadaEm;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTipoDeEnvio() {
		return tipoDeEnvio;
	}

	public void setTipoDeEnvio(String tipoDeEnvio) {
		this.tipoDeEnvio = tipoDeEnvio;
	}

	public LocalDateTime getEnviadaEm() {
		return enviadaEm;
	}

	public void setEnviadaEm(LocalDateTime enviadaEm) {
		this.enviadaEm = enviadaEm;
	}
	
	 public void enviar(Paciente paciente, AudioTTS audioTTS) {
	        this.enviadaEm = LocalDateTime.now();
	        System.out.println("🔔 Notificação enviada para: " + paciente.getNome());
	        System.out.println("Tipo: " + tipoDeEnvio);
	        
	   if(tipoDeEnvio == "audio") {
		   audioTTS.falarTexto(mensagem);
	   }
	        	else {
	        		System.out.println("Mensagem: " + mensagem);
	        	}
	        
	        System.out.println("Data/Hora: " + enviadaEm);
	    }

	@Override
	public String toString() {
		return "Notificacao [mensagem = " + mensagem + ", tipoDeEnvio = " + tipoDeEnvio + " , enviadaEm = " + enviadaEm + "]";
	}
	
	
	 
	 
	
}
