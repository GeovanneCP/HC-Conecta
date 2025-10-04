package br.com.hc.model;

import java.time.LocalDateTime;

public class Login {
	private Long idPaciente;
	private String login;
	private String senha;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;


	public Long getIdPaciente() { 
		return idPaciente; 
		}
	
	public void setIdPaciente(Long idPaciente) { 
		this.idPaciente = idPaciente; 
		}

	public String getLogin() { 
		return login; 
		}
	
	public void setLogin(String login) { 
		this.login = login; 
		}

	public String getSenha() {
		return senha; 
		}
	
	public void setSenha(String senha) {
		this.senha = senha; 
		}

	public LocalDateTime getDataCriacao() { 
		return dataCriacao; 
		}
	
	public void setDataCriacao(LocalDateTime dataCriacao) { 
		this.dataCriacao = dataCriacao; 
		}

	public LocalDateTime getDataAtualizacao() { 
		return dataAtualizacao; 
		}
	
	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao; 
		}
}


