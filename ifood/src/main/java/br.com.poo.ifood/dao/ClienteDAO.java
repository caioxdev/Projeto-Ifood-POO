package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Cliente;

import java.sql.*;

public class ClienteDAO {

    // Cadastrar cliente
    public boolean cadastrar(Cliente c) {
        String sql = "INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getSenha());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) c.setId_cliente(rs.getInt(1));
                return true;
            }

        } catch(SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
        return false;
    }

    // Buscar cliente por email e senha
    public Cliente buscarPorEmailESenha(String email, String senha) {
        String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                return c;
            }

        } catch(SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }
}
