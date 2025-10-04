package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.hc.model.ContatoPaciente;

public class ContatoPacienteDAO {

	public void adicionar(ContatoPaciente c) {
		String sql = "INSERT INTO TB_HC_CONTATO_PACIENTE (ID_CONTATO, ID_PACIENTE, TELEFONE, EMAIL) VALUES(SEQ_CONTATO_PACIENTE.NEXTVAL, ?, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setLong(1, c.getIdPaciente());
			comandoDeInsercao.setString(2, c.getTelefone());
			comandoDeInsercao.setString(3, c.getEmail());
			comandoDeInsercao.execute();
			comandoDeInsercao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
	}
	
	public ArrayList<ContatoPaciente> obterTodosContatos(){
		ArrayList<ContatoPaciente> contatos = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_CONTATO_PACIENTE";
		try (Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)){
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				ContatoPaciente contato = new ContatoPaciente();
				contato.setIdContato(rs.getLong("ID_CONTATO"));
				contato.setIdPaciente(rs.getLong("ID_PACIENTE"));
				contato.setTelefone(rs.getString("TELEFONE"));
				contato.setEmail(rs.getString("EMAIL"));
				contatos.add(contato);
			}
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return contatos;
	}
	
	public boolean atualizar(ContatoPaciente c) {
		String sql = "UPDATE TB_HC_CONTATO_PACIENTE SET ID_PACIENTE = ?, TELEFONE = ?, EMAIL = ? WHERE ID_CONTATO = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){
			
			if (!idExiste(c.getIdContato())) {
				return false; 
			}
			
			comandoDeAtualizacao.setLong(1, c.getIdPaciente());
			comandoDeAtualizacao.setString(2, c.getTelefone());
			comandoDeAtualizacao.setString(3, c.getEmail());
			comandoDeAtualizacao.setLong(4, c.getIdContato());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar contato do paciente", e);
		}
	}
	   
	public boolean removerPorId(Long idContato) {
		String sql = "DELETE FROM TB_HC_CONTATO_PACIENTE WHERE ID_CONTATO = ?";
		
		if (!idExiste(idContato)) {
			return false; 
		}
		
		try (Connection conexao = new ConnectionFactory().getConnection();
				  PreparedStatement comandoDeRemocao = conexao.prepareStatement(sql)) {
			comandoDeRemocao.setLong(1, idContato);
			int linhas = comandoDeRemocao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover contato do paciente", e);
		}
	}

	public boolean idExiste(Long idContato) {
		String sql = "SELECT 1 FROM TB_HC_CONTATO_PACIENTE WHERE ID_CONTATO = ?";

		try (Connection conexao = new ConnectionFactory().getConnection();
				PreparedStatement st = conexao.prepareStatement(sql)){
		     
			st.setLong(1, idContato);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_CONTATO", e);
		}
	}
	
}


