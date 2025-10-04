package br.com.hc.model;

import java.time.LocalDate;

public class Paciente {
    private Long idPaciente;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Paciente() {}

	public Paciente(String nome, String cpf, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Paciente(Long idPaciente, String nome, String cpf, LocalDate dataNascimento) {
		this.idPaciente = idPaciente;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
public String getInfo() {
	return "ID Paciente: " + idPaciente + "\nNome: " + nome + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento;
}

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "ID Paciente: " + idPaciente + "\nNome: " + nome + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento;
    }
	}

