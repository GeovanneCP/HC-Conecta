package br.com.hc.model;

public class Especialidade {
	private Long idEspecialidade;
	private String nomeEspecialidade;
	private String descricao;

	public Long getIdEspecialidade() { 
		return idEspecialidade; 
		}
	
	public void setIdEspecialidade(Long idEspecialidade) { 
		this.idEspecialidade = idEspecialidade; 
		}

	public String getNomeEspecialidade() { 
		return nomeEspecialidade; 
		}
	
	public void setNomeEspecialidade(String nomeEspecialidade) { 
		this.nomeEspecialidade = nomeEspecialidade; 
		}

	public String getDescricao() { 
		return descricao; 
		}
	
	public void setDescricao(String descricao) { 
		this.descricao = descricao; 
		}
}


