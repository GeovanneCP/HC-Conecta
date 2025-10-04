package br.com.hc.model;

import java.time.LocalDateTime;

public class Diagnostico {
	private Long idDiagnostico;
	private Long idConsulta;
	private String descricao;
	private LocalDateTime dataRegistro;

	public Long getIdDiagnostico() { 
		return idDiagnostico; 
		}
	
	public void setIdDiagnostico(Long idDiagnostico) { 
		this.idDiagnostico = idDiagnostico; 
		}

	public Long getIdConsulta() { 
		return idConsulta; 
		}
	
	public void setIdConsulta(Long idConsulta) { 
		this.idConsulta = idConsulta; 
		}

	public String getDescricao() { 
		return descricao; 
		}
	
	public void setDescricao(String descricao) { 
		this.descricao = descricao; 
		}

	public LocalDateTime getDataRegistro() { 
		return dataRegistro; 
		}
	
	public void setDataRegistro(LocalDateTime dataRegistro) { 
		this.dataRegistro = dataRegistro; 
		}
}


