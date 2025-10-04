package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.hc.model.Especialidade;

public class EspecialidadeDAO {


	public void adicionar(Especialidade e) {
		String sql = "INSERT INTO TB_HC_ESPECIALIDADE (ID_ESPECIALIDADE, NOME_ESPECIALIDADE, DESCRICAO) VALUES(SEQ_ESPECIALIDADE.NEXTVAL, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setString(1, e.getNomeEspecialidade());
			comandoDeInsercao.setString(2, e.getDescricao());
			comandoDeInsercao.execute();
			comandoDeInsercao.close();
			}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw new RuntimeException(ex);
			}
	}
	
	public ArrayList<Especialidade> obterTodasEspecialidades(){
		ArrayList<Especialidade> especialidades = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_ESPECIALIDADE";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)) {
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				Especialidade especialidade = new Especialidade();
				especialidade.setIdEspecialidade(rs.getLong("ID_ESPECIALIDADE"));
				especialidade.setNomeEspecialidade(rs.getString("NOME_ESPECIALIDADE"));
				especialidade.setDescricao(rs.getString("DESCRICAO"));
				especialidades.add(especialidade);
			}
			comandoDeSelecao.close();
			}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw new RuntimeException(ex);
			}
		
		return especialidades;
	}
	
	public boolean atualizar(Especialidade e) {
		String sql = "UPDATE TB_HC_ESPECIALIDADE SET NOME_ESPECIALIDADE = ?, DESCRICAO = ? WHERE ID_ESPECIALIDADE = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(e.getIdEspecialidade())) {
				return false; 
			}
			
			comandoDeAtualizacao.setString(1, e.getNomeEspecialidade());
			comandoDeAtualizacao.setString(2, e.getDescricao());
			comandoDeAtualizacao.setLong(3, e.getIdEspecialidade());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao atualizar especialidade", ex);
		}
	}
	   
	public boolean removerPorId(Long id) {
		String sql = "DELETE FROM TB_HC_ESPECIALIDADE WHERE ID_ESPECIALIDADE = ?";
		
		if (!idExiste(id)) {
			return false; 
		}
		
		try (Connection conexao = new ConnectionFactory().getConnection();
				  PreparedStatement comandoDeRemocao = conexao.prepareStatement(sql)) {
			comandoDeRemocao.setLong(1, id);
			int linhas = comandoDeRemocao.executeUpdate();
			return linhas > 0;
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao remover especialidade", ex);
		}
	}

	public boolean idExiste(Long id) {
		String sql = "SELECT 1 FROM TB_HC_ESPECIALIDADE WHERE ID_ESPECIALIDADE = ?";

		try(Connection conexao = new ConnectionFactory().getConnection(); 
		     PreparedStatement st = conexao.prepareStatement(sql)){
			st.setLong(1, id);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao verificar ID_ESPECIALIDADE", ex);
		}
	}

}


