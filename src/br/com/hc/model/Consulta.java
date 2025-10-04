package br.com.hc.model;

import java.time.LocalDateTime;

public class Consulta {
	  private Long idConsulta;
	  private Long idAgendamento;
	  private Long idMedico;
	  private Long idUnidade;
	  private String tipoAtendimento;
	  private LocalDateTime dataHora;
	    
	    public Consulta() {
	    }  
	    
		public Consulta(Long idConsulta, Long idAgendamento, Long idMedico, Long idUnidade, String tipoAtendimento,
				LocalDateTime dataHora) {
			this.idConsulta = idConsulta;
			this.idAgendamento = idAgendamento;
			this.idMedico = idMedico;
			this.idUnidade = idUnidade;
			this.tipoAtendimento = tipoAtendimento;
			this.dataHora = dataHora;
		}

		public Long getIdConsulta() {
			return idConsulta;
		}
		
		public void setIdConsulta(Long idConsulta) {
			this.idConsulta = idConsulta;
		}
		
		public Long getIdAgendamento() {
			return idAgendamento;
		}
		
		public void setIdAgendamento(Long idAgendamento) {
			this.idAgendamento = idAgendamento;
		}
		
		public Long getIdMedico() {
			return idMedico;
		}
		
		public void setIdMedico(Long idMedico) {
			this.idMedico = idMedico;
		}
		
		public Long getIdUnidade() {
			return idUnidade;
		}
		
		public void setIdUnidade(Long idUnidade) {
			this.idUnidade = idUnidade;
		}
		
		public String getTipoAtendimento() {
			return tipoAtendimento;
		}
		
		public void setTipoAtendimento(String tipoAtendimento) {
			this.tipoAtendimento = tipoAtendimento;
		}
		
		public LocalDateTime getDataHora() {
			return dataHora;
		}
		
		public void setDataHora(LocalDateTime dataHora) {
			this.dataHora = dataHora;
		}

		@Override
		public String toString() {
		    return "Consulta {" +
		           "\n   idConsulta = " + idConsulta +
		           ",\n   idAgendamento = " + idAgendamento +
		           ",\n   idMedico = " + idMedico +
		           ",\n   idUnidade = " + idUnidade +
		           ",\n   tipoAtendimento = "  + tipoAtendimento +
		           ",\n   dataHora = " + dataHora +
		           "\n}";
		}

	
   

}
