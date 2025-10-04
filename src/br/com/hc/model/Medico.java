package br.com.hc.model;

public class Medico{
	    private Long idMedico;
	    private String nome;
	    private String crmMedico;

	    public Medico() {}

		public Medico(Long idMedico, String nome, String crmMedico) {
			this.idMedico = idMedico;
			this.nome = nome;
			this.crmMedico = crmMedico;
		}

	    public Long getIdMedico() {
	        return idMedico;
	    }

	    public void setIdMedico(Long idMedico) {
	        this.idMedico = idMedico;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }


	    public String getCrmMedico() {
	        return crmMedico;
	    }

	    public void setCrmMedico(String crmMedico) {
	        this.crmMedico = crmMedico;
	    }

	    @Override
	    public String toString() {
	        return "ID Médico: " + idMedico + "\nNome: " + nome + "\nCRM: " + crmMedico;
	    }
	}


