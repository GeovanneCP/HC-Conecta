package br.com.hc.model;

import java.time.LocalDateTime;

public class HistoricoConsulta {
	private Long idConsulta;
	private String statusFinal;
	private String observacoes;
	private LocalDateTime dataRegistro;

	public Long getIdConsulta() { 
		return idConsulta; 
		}
	
	public void setIdConsulta(Long idConsulta) { 
		this.idConsulta = idConsulta; 
		}

	public String getStatusFinal() { 
		return statusFinal; 
		}
	
	public void setStatusFinal(String statusFinal) { 
		this.statusFinal = statusFinal; 
		}

	public String getObservacoes() {
		return observacoes; 
		}
	
	public void setObservacoes(String observacoes) { 
		this.observacoes = observacoes; 
		}

	public LocalDateTime getDataRegistro() {
		return dataRegistro; 
		}
	
	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro; 
		}
}


