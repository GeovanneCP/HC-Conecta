package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.hc.model.Agendamento;

public class AgendamentoDAO {

       public void adicionar(Agendamento ag) {
	       String sql = "INSERT INTO TB_HC_AGENDAMENTO (ID_AGENDAMENTO, ID_PACIENTE, DATA_HORA, STATUS, NOTIFICADO,DATA_HORA_NOTIFICACAO) VALUES(SEQ_AGENDAMENTO.NEXTVAL, ?, ?, ?, ?,?)";
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
		       comandoDeInsercao.setLong(1, ag.getIdPaciente());
		       if (ag.getDataHora() != null) {
			       comandoDeInsercao.setObject(2, ag.getDataHora());
		       } else {
			       comandoDeInsercao.setNull(2, Types.TIMESTAMP);
		       }
		       comandoDeInsercao.setString(3, ag.getStatus());
		       comandoDeInsercao.setString(4, ag.getNotificado());
		       
		       if (ag.getDataHoraNotificacao() != null) {
					comandoDeInsercao.setObject(5, ag.getDataHoraNotificacao());
				} else {
					comandoDeInsercao.setNull(5, Types.TIMESTAMP);
				}
		       comandoDeInsercao.execute();
	       } catch(SQLException e) {
		       System.out.println(e.getMessage());
		       throw new RuntimeException(e);
	       }
       }
	
       public ArrayList<Agendamento> obterTodosAgendamentos(){
	       ArrayList<Agendamento> agendamentos = new ArrayList<>();
	       String sql = "SELECT * FROM TB_HC_AGENDAMENTO";
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
		    ResultSet rs = comandoDeSelecao.executeQuery()) {
		       while(rs.next()) {
			       Long idAgendamento = rs.getLong("ID_AGENDAMENTO");
			       Long idPaciente = rs.getLong("ID_PACIENTE");
			       LocalDateTime dataHora = rs.getObject("DATA_HORA", LocalDateTime.class);
			       String status = rs.getString("STATUS");
			       String notificado = rs.getString("NOTIFICADO");
			       LocalDateTime dataHoraNotificacao = rs.getObject("DATA_HORA_NOTIFICACAO", LocalDateTime.class);

			       Agendamento agendamento = new Agendamento();
			       agendamento.setIdAgendamento(idAgendamento);
			       agendamento.setIdPaciente(idPaciente);
			       agendamento.setDataHora(dataHora);
			       agendamento.setStatus(status);
			       agendamento.setNotificado(notificado);
			       agendamento.setDataHoraNotificacao(dataHoraNotificacao);
			       agendamentos.add(agendamento);
		       }
	       } catch(SQLException e) {
		       System.out.println(e.getMessage());
		       throw new RuntimeException(e);
	       }
	       return agendamentos;
       }
	
       public boolean atualizar(Agendamento ag) {
	       String sql = "UPDATE TB_HC_AGENDAMENTO SET ID_PACIENTE = ?, DATA_HORA = ?, STATUS = ?, NOTIFICADO = ?,DATA_HORA_NOTIFICACAO = ? "
	       		+ "WHERE ID_AGENDAMENTO = ?";
	       if (!idExiste(ag.getIdAgendamento())) {
		       return false; 
	       }
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)) {
		       comandoDeAtualizacao.setLong(1, ag.getIdPaciente());
		       if (ag.getDataHora() != null) {
			       comandoDeAtualizacao.setObject(2, ag.getDataHora());
		       } else {
			       comandoDeAtualizacao.setNull(2, Types.TIMESTAMP);
		       }
		       comandoDeAtualizacao.setString(3, ag.getStatus());
		       comandoDeAtualizacao.setString(4, ag.getNotificado());
		       if (ag.getDataHoraNotificacao() != null) {
			       comandoDeAtualizacao.setObject(5, ag.getDataHoraNotificacao());
		       } else {
			       comandoDeAtualizacao.setNull(5, Types.TIMESTAMP);
		       }
		       comandoDeAtualizacao.setLong(6, ag.getIdAgendamento());
		       int linhas = comandoDeAtualizacao.executeUpdate();
		       return linhas > 0;
	       } catch (SQLException e) {
		       throw new RuntimeException("Erro ao atualizar agendamento", e);
	       }
       }
	   
       public boolean removerPorId(Long idAgendamento) {
	       String sql = "DELETE FROM TB_HC_AGENDAMENTO WHERE ID_AGENDAMENTO = ?";
	       if (!idExiste(idAgendamento)) {
		       return false; 
	       }
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement st = conexao.prepareStatement(sql)) {
		       st.setLong(1, idAgendamento);
		       int linhas = st.executeUpdate();
		       return linhas > 0;
	       } catch (SQLException e) {
		       throw new RuntimeException("Erro ao remover agendamento", e);
	       }
       }

       public boolean idExiste(Long idAgendamento) {
	       String sql = "SELECT 1 FROM TB_HC_AGENDAMENTO WHERE ID_AGENDAMENTO = ?";
	       try (Connection conexao = new ConnectionFactory().getConnection();
		    PreparedStatement st = conexao.prepareStatement(sql)) {
		       st.setLong(1, idAgendamento);
		       try (ResultSet rs = st.executeQuery()) {
			       return rs.next(); 
		       }
	       } catch (SQLException e) {
		       throw new RuntimeException("Erro ao verificar ID_AGENDAMENTO", e);
	       }
       }
}


