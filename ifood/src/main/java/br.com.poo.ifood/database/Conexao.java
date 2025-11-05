package br.com.poo.ifood.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/projeto_ifood";
    private static final String USER = "ifood_user";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("SUCESSO: Conectado com sucesso ao banco!");
            return conn;
        } catch (SQLException e) {
            System.out.println("ERRO: Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}