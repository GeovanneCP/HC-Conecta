package br.com.hc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	public Connection getConnection() {
		String urlDeConexao = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		String login = "rm563960";
		String senha = "020607";
		
		try {
			return DriverManager.getConnection(urlDeConexao, login, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
