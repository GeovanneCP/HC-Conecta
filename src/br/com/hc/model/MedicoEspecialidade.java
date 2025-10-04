package br.com.hc.model;

import java.time.LocalDate;

public class MedicoEspecialidade {
	private Long idMedico;
	private Long idEspecialidade;
	private LocalDate dataInicio; 

	public Long getIdMedico() { 
		return idMedico; 
		}
	
	public void setIdMedico(Long idMedico) { 
		this.idMedico = idMedico; 
		}

	public Long getIdEspecialidade() { 
		return idEspecialidade; 
		}
	
	public void setIdEspecialidade(Long idEspecialidade) { 
		this.idEspecialidade = idEspecialidade;
		}

	public LocalDate getDataInicio() { 
		return dataInicio; 
		}
	
	public void setDataInicio(LocalDate dataInicio) { 
		this.dataInicio = dataInicio; 
		}

}


