package br.com.hc.banco;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.hc.dao.ConnectionFactory;

public class ConnectionFactoryTest {

    @Test
    void conectarNoBanco(){
        Connection conexao = new ConnectionFactory().getConnection();

        try {
            assertFalse(conexao.isClosed());
            conexao.close();
            assertTrue(conexao.isClosed());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(!conexao.isClosed()){ conexao.close();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}