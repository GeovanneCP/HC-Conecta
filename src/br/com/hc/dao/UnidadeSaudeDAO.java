package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.hc.model.UnidadeSaude;

public class UnidadeSaudeDAO {
	

       public void adicionar(UnidadeSaude unidade) {
	       String sql = "INSERT INTO TB_HC_UNIDADE_SAUDE (ID_UNIDADE_SAUDE, NOME_UNIDADE_SAUDE, ENDERECO) VALUES(SEQ_UNIDADE_SAUDE.NEXTVAL, ?, ?)";
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
		       comandoDeInsercao.setString(1, unidade.getNomeUnidadeSaude());
		       comandoDeInsercao.setString(2, unidade.getEndereco());
		       comandoDeInsercao.execute();
	       } catch(SQLException e) {
		       System.out.println(e.getMessage());
		       throw new RuntimeException(e);
	       }
       }
	
       public ArrayList<UnidadeSaude> obterTodasUnidades(){
	       ArrayList<UnidadeSaude> unidades = new ArrayList<>();
	       String sql = "SELECT * FROM TB_HC_UNIDADE_SAUDE";
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
		    ResultSet rs = comandoDeSelecao.executeQuery()) {
		       while(rs.next()) {
			       UnidadeSaude unidade = new UnidadeSaude();
			       unidade.setIdUnidadeSaude(rs.getLong("ID_UNIDADE_SAUDE"));
			       unidade.setNomeUnidadeSaude(rs.getString("NOME_UNIDADE_SAUDE"));
			       unidade.setEndereco(rs.getString("ENDERECO"));
			       unidades.add(unidade);
		       }
	       } catch(SQLException e) {
		       System.out.println(e.getMessage());
		       throw new RuntimeException(e);
	       }
	       return unidades;
       }
	
       public boolean atualizar(UnidadeSaude unidade) {
	       String sql = "UPDATE TB_HC_UNIDADE_SAUDE SET NOME_UNIDADE_SAUDE = ?, ENDERECO = ? WHERE ID_UNIDADE_SAUDE = ?";
	       if (!idExiste(unidade.getIdUnidadeSaude())) {
		       return false; 
	       }
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)) {
		       comandoDeAtualizacao.setString(1, unidade.getNomeUnidadeSaude());
		       comandoDeAtualizacao.setString(2, unidade.getEndereco());
		       comandoDeAtualizacao.setLong(3, unidade.getIdUnidadeSaude());
		       int linhas = comandoDeAtualizacao.executeUpdate();
		       return linhas > 0;
	       } catch (SQLException e) {
		       throw new RuntimeException("Erro ao atualizar unidade de saúde", e);
	       }
       }
	   
       public boolean removerPorId(Long id) {
	       String sql = "DELETE FROM TB_HC_UNIDADE_SAUDE WHERE ID_UNIDADE_SAUDE = ?";
	       if (!idExiste(id)) {
		       return false; 
	       }
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement st = conexao.prepareStatement(sql)) {
		       st.setLong(1, id);
		       int linhas = st.executeUpdate();
		       return linhas > 0;
	       } catch (SQLException e) {
		       throw new RuntimeException("Erro ao remover unidade de saúde", e);
	       }
       }

       public boolean idExiste(Long id) {
	       String sql = "SELECT 1 FROM TB_HC_UNIDADE_SAUDE WHERE ID_UNIDADE_SAUDE = ?";
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement st = conexao.prepareStatement(sql)) {
		       st.setLong(1, id);
		       try (ResultSet rs = st.executeQuery()) {
			       return rs.next(); 
		       }
	       } catch (SQLException e) {
		       throw new RuntimeException("Erro ao verificar ID_UNIDADE_SAUDE", e);
	       }
       }


}


