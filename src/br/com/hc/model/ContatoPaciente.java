package br.com.hc.model;

public class ContatoPaciente {
	private Long idContato;
	private Long idPaciente;
	private String telefone;
	private String email;

	public Long getIdContato() { 
		return idContato; 
		}
	
	public void setIdContato(Long idContato) { 
		this.idContato = idContato; 
		}

	public Long getIdPaciente() { 
		return idPaciente; 
		}
	
	public void setIdPaciente(Long idPaciente) { 
		this.idPaciente = idPaciente; 
		}

	public String getTelefone() { 
		return telefone; 
		}
	
	public void setTelefone(String telefone) { 
		this.telefone = telefone; 
		}

	public String getEmail() { 
		return email; 
		}
	
	public void setEmail(String email) { 
		this.email = email; 
		}
}


