package br.com.hc.model;

import java.time.LocalDateTime;

public class Agendamento {
	private Long idAgendamento;
	private Long idPaciente;
	private LocalDateTime dataHora;
	private String status;
	private String notificado; 
	private LocalDateTime dataHoraNotificacao;

	public Long getIdAgendamento() { 
		return idAgendamento; 
		}
	
	public void setIdAgendamento(Long idAgendamento) { 
		this.idAgendamento = idAgendamento; 
		}

	public Long getIdPaciente() { 
		return idPaciente; 
		}
	
	public void setIdPaciente(Long idPaciente) { 
		this.idPaciente = idPaciente; 
		}

	public LocalDateTime getDataHora() { 
		return dataHora; 
		}
	
	public void setDataHora(LocalDateTime dataHora) { 
		this.dataHora = dataHora; 
		}

	public String getStatus() { 
		return status; 
		}
	
	public void setStatus(String status) { 
		this.status = status; 
		}

	public String getNotificado() { 
		return notificado; 
		}
	
	public void setNotificado(String notificado) { 
		this.notificado = notificado; 
		}

	public LocalDateTime getDataHoraNotificacao() {
		return dataHoraNotificacao;
	}

	public void setDataHoraNotificacao(LocalDateTime dataHoraNotificacao) {
		this.dataHoraNotificacao = dataHoraNotificacao;
	}
	
	
}


