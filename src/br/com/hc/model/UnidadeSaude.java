package br.com.hc.model;

public class UnidadeSaude {
	private Long idUnidadeSaude;
	private String nomeUnidadeSaude;
	private String endereco;

	public Long getIdUnidadeSaude() { 
		return idUnidadeSaude; 
		}
	
	public void setIdUnidadeSaude(Long idUnidadeSaude) { 
		this.idUnidadeSaude = idUnidadeSaude; 
		}

	public String getNomeUnidadeSaude() { 
		return nomeUnidadeSaude; 
		}
	
	public void setNomeUnidadeSaude(String nomeUnidadeSaude) { 
		this.nomeUnidadeSaude = nomeUnidadeSaude; 
		}

	public String getEndereco() { 
		return endereco; 
		}
	
	public void setEndereco(String endereco) { 
		this.endereco = endereco; 
		}
}


