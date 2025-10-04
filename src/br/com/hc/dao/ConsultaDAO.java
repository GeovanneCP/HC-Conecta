package br.com.hc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.hc.model.Consulta;

public class ConsultaDAO {


	public void adicionar(Consulta consulta) {
		String sql = "INSERT INTO TB_HC_CONSULTA (ID_CONSULTA, ID_AGENDAMENTO, ID_MEDICO,"
				+ "ID_UNIDADE, TIPO_ATENDIMENTO, DATA_HORA) "
				+ "VALUES(SEQ_CONSULTA.NEXTVAL, ?, ?, ?, ?, ?)";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql)) {
			comandoDeInsercao.setLong(1, consulta.getIdAgendamento());
			comandoDeInsercao.setLong(2, consulta.getIdMedico());
			comandoDeInsercao.setLong(3, consulta.getIdUnidade());
			comandoDeInsercao.setString(4, consulta.getTipoAtendimento());
			if (consulta.getDataHora() != null) {
				comandoDeInsercao.setObject(5, consulta.getDataHora());
			} else {
				comandoDeInsercao.setNull(5, Types.TIMESTAMP);
			}
			comandoDeInsercao.execute();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
	}
	
	public ArrayList<Consulta> obterTodasConsultas(){
		ArrayList<Consulta> consultas = new ArrayList<>();
		String sql = "SELECT * FROM TB_HC_CONSULTA";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql)) {
			ResultSet rs = comandoDeSelecao.executeQuery();
			while(rs.next()) {
				Consulta consulta = new Consulta();
				consulta.setIdConsulta(rs.getLong("ID_CONSULTA"));
				consulta.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
				consulta.setIdMedico(rs.getLong("ID_MEDICO"));
				consulta.setIdUnidade(rs.getLong("ID_UNIDADE"));
				consulta.setTipoAtendimento(rs.getString("TIPO_ATENDIMENTO"));
				consulta.setDataHora(rs.getObject("DATA_HORA", LocalDateTime.class));
				consultas.add(consulta);
			}
			comandoDeSelecao.close();
			}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			}
		
		return consultas;
	}
	
	public boolean atualizar(Consulta consulta) {
		String sql = "UPDATE TB_HC_CONSULTA "
				+ "SET ID_AGENDAMENTO = ?, ID_MEDICO = ?, ID_UNIDADE = ?, TIPO_ATENDIMENTO = ?, DATA_HORA = ? "
				+ "WHERE ID_CONSULTA = ?";
		try(Connection conexao = new ConnectionFactory().getConnection();
			    PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sql)){			
			if (!idExiste(consulta.getIdConsulta())) {
				return false; 
			}
			comandoDeAtualizacao.setLong(1, consulta.getIdAgendamento());
			comandoDeAtualizacao.setLong(2, consulta.getIdMedico());
			comandoDeAtualizacao.setLong(3, consulta.getIdUnidade());
			comandoDeAtualizacao.setString(4, consulta.getTipoAtendimento());
			if (consulta.getDataHora() != null) {
				comandoDeAtualizacao.setObject(5, consulta.getDataHora()); // JDBC 4.2: LocalDateTime
			} else {
				comandoDeAtualizacao.setNull(5, Types.TIMESTAMP);
			}
			comandoDeAtualizacao.setLong(6, consulta.getIdConsulta());

			int linhas = comandoDeAtualizacao.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar consulta", e);
		}
	}
	   
	public boolean removerPorId(Long idConsulta) {
		String sql = "DELETE FROM TB_HC_CONSULTA WHERE ID_CONSULTA = ?";
		
		if (!idExiste(idConsulta)) {
			return false; 
		}
		
		try(Connection conexao = new ConnectionFactory().getConnection())  {
		     PreparedStatement st = conexao.prepareStatement(sql);
			st.setLong(1, idConsulta);
			int linhas = st.executeUpdate();
			return linhas > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover consulta", e);
		}
	}
	

	public boolean idExiste(Long idConsulta) {
		String sql = "SELECT 1 FROM TB_HC_CONSULTA WHERE ID_CONSULTA = ?";

		try (Connection conexao = new ConnectionFactory().getConnection()){
		     PreparedStatement st = conexao.prepareStatement(sql);
			st.setLong(1, idConsulta);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar ID_CONSULTA", e);
		}
	}

	public boolean existeConsultaAgendada(LocalDateTime dataHora) {
		String sql = "SELECT 1 FROM TB_HC_CONSULTA WHERE DATA_HORA = ?";

		try (Connection conexao = new ConnectionFactory().getConnection()){
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setObject(1, dataHora);

			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar consulta agendada", e);
		}
	}
}
