package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.hc.model.Login;

public class LoginDAO {


	public void adicionar(Login l) {
		String sql = "INSERT INTO TB_HC_LOGIN (ID_PACIENTE, LOGIN, SENHA, DATA_CRIACAO, DATA_ATUALIZACAO) VALUES(?, ?, ?, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setLong(1, l.getIdPaciente());
			comandoDeInsercao.setString(2, l.getLogin());
			comandoDeInsercao.setString(3, l.getSenha());
			if (l.getDataCriacao() != null) comandoDeInsercao.setObject(4, l.getDataCriacao()); else comandoDeInsercao.setNull(4, Types.TIMESTAMP);
			if (l.getDataAtualizacao() != null) comandoDeInsercao.setObject(5, l.getDataAtualizacao()); else comandoDeInsercao.setNull(5, Types.TIMESTAMP);
			comandoDeInsercao.execute();
			comandoDeInsercao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
	}
	
	public ArrayList<Login> obterTodosLogins(){
		ArrayList<Login> logins = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_LOGIN";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)) {
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				Login login = new Login();
				login.setIdPaciente(rs.getLong("ID_PACIENTE"));
				login.setLogin(rs.getString("LOGIN"));
				login.setSenha(rs.getString("SENHA"));
				LocalDateTime dc = rs.getObject("DATA_CRIACAO", LocalDateTime.class);
				LocalDateTime da = rs.getObject("DATA_ATUALIZACAO", LocalDateTime.class);
				login.setDataCriacao(dc);
				login.setDataAtualizacao(da);
				logins.add(login);
			}
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return logins;
	}
	
	public boolean atualizar(Login l) {
		String sql = "UPDATE TB_HC_LOGIN SET LOGIN = ?, SENHA = ?, DATA_CRIACAO = ?, DATA_ATUALIZACAO = ? WHERE ID_PACIENTE = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(l.getIdPaciente())) {
				return false; 
			}
			
			comandoDeAtualizacao.setString(1, l.getLogin());
			comandoDeAtualizacao.setString(2, l.getSenha());
			if (l.getDataCriacao() != null) comandoDeAtualizacao.setObject(3, l.getDataCriacao()); else comandoDeAtualizacao.setNull(3, Types.TIMESTAMP);
			if (l.getDataAtualizacao() != null) comandoDeAtualizacao.setObject(4, l.getDataAtualizacao()); else comandoDeAtualizacao.setNull(4, Types.TIMESTAMP);
			comandoDeAtualizacao.setLong(5, l.getIdPaciente());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar login", e);
		}
	}
	   
	public boolean removerPorId(Long id) {
		String sql = "DELETE FROM TB_HC_LOGIN WHERE ID_PACIENTE = ?";
		
		if (!idExiste(id)) {
			return false; 
		}
		
		try(Connection conexao = new ConnectionFactory().getConnection(); 
		     PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setLong(1, id);
			int linhas = st.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover login do paciente", e);
		}
	}

	public boolean idExiste(Long id) {
		String sql = "SELECT 1 FROM TB_HC_LOGIN WHERE ID_PACIENTE = ?";

		try(Connection conexao = new ConnectionFactory().getConnection(); 
			     PreparedStatement st = conexao.prepareStatement(sql)){
			st.setLong(1, id);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_PACIENTE", e);
		}
	}


}


