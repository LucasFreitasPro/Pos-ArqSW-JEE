package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/iec-jee";
    private final String USER = "postgres";
    private final String PASSWORD = "p123";
    private Connection connection = null;

    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public void fecharConexao() throws SQLException {
        if (connection != null)
            connection.close();
    }
}
