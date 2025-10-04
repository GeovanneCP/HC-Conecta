package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.hc.model.Diagnostico;

public class DiagnosticoDAO {

	public void adicionar(Diagnostico d) {
		String sql = "INSERT INTO TB_HC_DIAGNOSTICO (ID_DIAGNOSTICO, ID_CONSULTA, DESCRICAO, DATA_REGISTRO) VALUES(SEQ_DIAGNOSTICO.NEXTVAL, ?, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setLong(1, d.getIdConsulta());
			comandoDeInsercao.setString(2, d.getDescricao());
			if (d.getDataRegistro() != null) {
				comandoDeInsercao.setObject(3, d.getDataRegistro());
			} else {
				comandoDeInsercao.setNull(3, Types.TIMESTAMP);
			}
			comandoDeInsercao.execute();
			comandoDeInsercao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
	}
	
	public ArrayList<Diagnostico> obterTodosDiagnosticos(){
		ArrayList<Diagnostico> diagnosticos = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_DIAGNOSTICO";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)) {
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				Diagnostico diagnostico = new Diagnostico();
				diagnostico.setIdDiagnostico(rs.getLong("ID_DIAGNOSTICO"));
				diagnostico.setIdConsulta(rs.getLong("ID_CONSULTA"));
				diagnostico.setDescricao(rs.getString("DESCRICAO"));
				LocalDateTime dt = rs.getObject("DATA_REGISTRO", LocalDateTime.class);
				diagnostico.setDataRegistro(dt);
				diagnosticos.add(diagnostico);
			}
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return diagnosticos;
	}
	
	public boolean atualizar(Diagnostico d) {
		String sql = "UPDATE TB_HC_DIAGNOSTICO SET ID_CONSULTA = ?, DESCRICAO = ?, DATA_REGISTRO = ? WHERE ID_DIAGNOSTICO = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(d.getIdDiagnostico())) {
				return false; 
			}
			
			comandoDeAtualizacao.setLong(1, d.getIdConsulta());
			comandoDeAtualizacao.setString(2, d.getDescricao());
			if (d.getDataRegistro() != null) {
				comandoDeAtualizacao.setObject(3, d.getDataRegistro());
			} else {
				comandoDeAtualizacao.setNull(3, Types.TIMESTAMP);
			}
			comandoDeAtualizacao.setLong(4, d.getIdDiagnostico());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar diagnostico", e);
		}
	}
	   
	public boolean removerPorId(Long id) {
		String sql = "DELETE FROM TB_HC_DIAGNOSTICO WHERE ID_DIAGNOSTICO = ?";
		
		if (!idExiste(id)) {
			return false; 
		}
		
		try(Connection conexao = new ConnectionFactory().getConnection();
				  PreparedStatement comandoDeRemocao = conexao.prepareStatement(sql))  {
			comandoDeRemocao.setLong(1, id);
			int linhas = comandoDeRemocao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover diagnostico", e);
		}
	}

	public boolean idExiste(Long id) {
		String sql = "SELECT 1 FROM TB_HC_DIAGNOSTICO WHERE ID_DIAGNOSTICO = ?";

		try (Connection conexao = new ConnectionFactory().getConnection();
				PreparedStatement st = conexao.prepareStatement(sql)){
			st.setLong(1, id);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_DIAGNOSTICO", e);
		}
	}
	
}


