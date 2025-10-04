package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.hc.model.Medico;

public class MedicoDAO {


	public void adicionar(Medico medico) {
		String sql = "INSERT INTO TB_HC_MEDICO (ID_MEDICO, NOME, CRM_MEDICO) VALUES(SEQ_MEDICO.NEXTVAL, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setString(1, medico.getNome());
			comandoDeInsercao.setString(2, medico.getCrmMedico());
			comandoDeInsercao.execute();
			comandoDeInsercao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
	}
	
	public ArrayList<Medico> obterTodosMedicos(){
		ArrayList<Medico> medicos = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_MEDICO";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)) {
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				Medico medico = new Medico();
				medico.setIdMedico(rs.getLong("ID_MEDICO"));
				medico.setNome(rs.getString("NOME"));
				medico.setCrmMedico(rs.getString("CRM_MEDICO"));
				medicos.add(medico);
			}
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return medicos;
	}
	
	public boolean atualizar(Medico medico) {
		String sql = "UPDATE TB_HC_MEDICO SET NOME = ?, CRM_MEDICO = ? WHERE ID_MEDICO = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(medico.getIdMedico())) {
				return false; 
			}
			
			comandoDeAtualizacao.setString(1, medico.getNome());
			comandoDeAtualizacao.setString(2, medico.getCrmMedico());
			comandoDeAtualizacao.setLong(3, medico.getIdMedico());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar medico", e);
		}
	}
	   
	public boolean removerPorId(Long idMedico) {
		String sql = "DELETE FROM TB_HC_MEDICO WHERE ID_MEDICO = ?";
		
		if (!idExiste(idMedico)) {
			return false; 
		}
		
		try(Connection conexao = new ConnectionFactory().getConnection(); 
			     PreparedStatement st = conexao.prepareStatement(sql))  {
			st.setLong(1, idMedico);
			int linhas = st.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover medico", e);
		}
	}

	public boolean idExiste(Long idMedico) {
		String sql = "SELECT 1 FROM TB_HC_MEDICO WHERE ID_MEDICO = ?";

		try(Connection conexao = new ConnectionFactory().getConnection();
		     PreparedStatement st = conexao.prepareStatement(sql)){
			st.setLong(1, idMedico);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_MEDICO", e);
		}
	}

	    
}


