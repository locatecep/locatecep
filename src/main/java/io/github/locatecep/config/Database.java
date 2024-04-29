package io.github.locatecep.config;

import io.github.locatecep.service.exception.DatabaseConnectionError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/SEU_BANCO_DE_DADOS";
    private static String username = "postgres";
    private static String password = "SUA_SENHA";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl,username,password);
            return conn;
        } catch (SQLException e) {
            throw new DatabaseConnectionError("Erro ao conectar com o banco de dados -> " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionError("Driver JDBC não encontrado: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseConnectionError("Erro ao conectar com o banco de dados -> " + e.getMessage());
            }
        }
    }

}
