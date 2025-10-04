package br.com.hc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.hc.model.Paciente;

public class PacienteDAO {

	
	public void adicionar(Paciente paciente) {
		String sql = "INSERT INTO TB_HC_PACIENTE (id_paciente, nome, cpf, data_nascimento) VALUES(SEQ_PACIENTE.NEXTVAL, ?, ?, ?)";
	    try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)){
	        // Verifica se o CPF é válido antes de inserir
	        if (!CpfValido(paciente.getCpf())) {
	            throw new IllegalArgumentException("CPF inválido: " + paciente.getCpf());
	        }

	        // Verifica se já existe CPF no banco
	        String sqlCheck = "SELECT COUNT(*) FROM TB_HC_PACIENTE WHERE cpf = ?";
	        PreparedStatement checkStmt = conexao.prepareStatement(sqlCheck);
	        checkStmt.setString(1, paciente.getCpf());
	        ResultSet rs = checkStmt.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        rs.close();
	        checkStmt.close();

	        if (count > 0) {
	            throw new IllegalArgumentException("CPF já cadastrado: " + paciente.getCpf());
	        }
	        
	        comandoDeInsercao.setString(1, paciente.getNome());
	        comandoDeInsercao.setString(2, paciente.getCpf());
	        comandoDeInsercao.setDate(3, Date.valueOf(paciente.getDataNascimento()));
	        comandoDeInsercao.execute();
	        comandoDeInsercao.close();

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    }
	}
	
	public ArrayList<Paciente> obterTodosPacientes(){
		ArrayList<Paciente> pacientes = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_PACIENTE";
		try (Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)){
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getLong("ID_PACIENTE"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				Date dnSql = rs.getDate("data_nascimento");
				LocalDate dataNascimento = (dnSql != null) ? dnSql.toLocalDate() : null;
				paciente.setDataNascimento(dataNascimento);
				pacientes.add(paciente);
			}
			comandoDeSelecao.execute();
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return pacientes;
	}
	
	public boolean atualizar(Paciente paciente) {
		String sql = "UPDATE TB_HC_PACIENTE "
				+ "SET nome = ?, data_nascimento = ?, cpf = ? "
				+ "WHERE id_paciente = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(paciente.getIdPaciente())) {
				return false; 
			}
			comandoDeAtualizacao.setString(1, paciente.getNome());

			LocalDate dn = paciente.getDataNascimento();
			if (dn != null) {
				comandoDeAtualizacao.setDate(2, java.sql.Date.valueOf(dn));
			} else {
				comandoDeAtualizacao.setNull(2, java.sql.Types.DATE);
			}

			comandoDeAtualizacao.setString(3, paciente.getCpf());
			comandoDeAtualizacao.setLong(4, paciente.getIdPaciente());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar paciente", e);
		}
	}
	   
	public boolean removerPorId(Long idPaciente) {
		String sql = "DELETE FROM TB_HC_PACIENTE WHERE ID_PACIENTE = ?";
		
		if (!idExiste(idPaciente)) {
			return false; 
		}
		
		try (Connection conexao = new ConnectionFactory().getConnection();
			  PreparedStatement comandoDeRemocao = conexao.prepareStatement(sql)){
			comandoDeRemocao.setLong(1, idPaciente);
			int linhas = comandoDeRemocao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover paciente", e);
		}
	}
	
	
	public boolean idExiste(Long idPaciente) {
		String sql = "SELECT 1 FROM TB_HC_PACIENTE WHERE ID_PACIENTE = ?";

		try(Connection conexao = new ConnectionFactory().getConnection();
				  PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setLong(1, idPaciente);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_PACIENTE", e);
		}
	}

	
	public static boolean CpfValido(String cpf) {
	    if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
	        return false;
	    }

	    try {
	        int soma = 0, resto;
	        for (int i = 1; i <= 9; i++) {
	            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
	        }
	        resto = (soma * 10) % 11;
	        if ((resto == 10) || (resto == 11)) resto = 0;
	        if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

	        soma = 0;
	        for (int i = 1; i <= 10; i++) {
	            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
	        }
	        resto = (soma * 10) % 11;
	        if ((resto == 10) || (resto == 11)) resto = 0;
	        return resto == Integer.parseInt(cpf.substring(10));
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	    
}
