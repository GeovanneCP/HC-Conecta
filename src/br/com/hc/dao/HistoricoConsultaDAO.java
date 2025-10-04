package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.hc.model.HistoricoConsulta;

public class HistoricoConsultaDAO {


	public void adicionar(HistoricoConsulta h) {
		String sql = "INSERT INTO TB_HC_HISTORICO_CONSULTA (ID_CONSULTA, STATUS_FINAL, OBSERVACOES, DATA_REGISTRO) VALUES(?, ?, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setLong(1, h.getIdConsulta());
			comandoDeInsercao.setString(2, h.getStatusFinal());
			comandoDeInsercao.setString(3, h.getObservacoes());
			if (h.getDataRegistro() != null) {
				comandoDeInsercao.setObject(4, h.getDataRegistro());
			} else {
				comandoDeInsercao.setNull(4, Types.TIMESTAMP);
			}
			comandoDeInsercao.execute();
			comandoDeInsercao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
	}
	
	public ArrayList<HistoricoConsulta> obterTodosHistoricos(){
		ArrayList<HistoricoConsulta> historicos = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_HISTORICO_CONSULTA";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)) {
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				HistoricoConsulta historico = new HistoricoConsulta();
				historico.setIdConsulta(rs.getLong("ID_CONSULTA"));
				historico.setStatusFinal(rs.getString("STATUS_FINAL"));
				historico.setObservacoes(rs.getString("OBSERVACOES"));
				LocalDateTime dt = rs.getObject("DATA_REGISTRO", LocalDateTime.class);
				historico.setDataRegistro(dt);
				historicos.add(historico);
			}
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return historicos;
	}
	
	public boolean atualizar(HistoricoConsulta h) {
		String sql = "UPDATE TB_HC_HISTORICO_CONSULTA SET STATUS_FINAL = ?, OBSERVACOES = ?, DATA_REGISTRO = ? WHERE ID_CONSULTA = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(h.getIdConsulta())) {
				return false; 
			}
			
			comandoDeAtualizacao.setString(1, h.getStatusFinal());
			comandoDeAtualizacao.setString(2, h.getObservacoes());
			if (h.getDataRegistro() != null) {
				comandoDeAtualizacao.setObject(3, h.getDataRegistro());
			} else {
				comandoDeAtualizacao.setNull(3, Types.TIMESTAMP);
			}
			comandoDeAtualizacao.setLong(4, h.getIdConsulta());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar histórico", e);
		}
	}
	   
	public boolean removerPorId(Long idConsulta) {
		String sql = "DELETE FROM TB_HC_HISTORICO_CONSULTA WHERE ID_CONSULTA = ?";
		
		if (!idExiste(idConsulta)) {
			return false; 
		}
		
		try(Connection conexao = new ConnectionFactory().getConnection();
				  PreparedStatement comandoDeRemocao = conexao.prepareStatement(sql))  {
			comandoDeRemocao.setLong(1, idConsulta);
			int linhas = comandoDeRemocao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover histórico", e);
		}
	}

	public boolean idExiste(Long idConsulta) {
		String sql = "SELECT 1 FROM TB_HC_HISTORICO_CONSULTA WHERE ID_CONSULTA = ?";

		try(Connection conexao = new ConnectionFactory().getConnection();
		     PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setLong(1, idConsulta);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_CONSULTA (histórico)", e);
		}
	}

}


