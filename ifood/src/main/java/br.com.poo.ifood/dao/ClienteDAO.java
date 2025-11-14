package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void create(Cliente c) {
        String sql = "INSERT INTO cliente (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getSenha());
            ps.setString(4, c.getTelefone());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("ClienteDAO.create: " + e.getMessage());
        }
    }

    public Cliente findById(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setEmail(rs.getString("email"));
                    c.setSenha(rs.getString("senha"));
                    c.setTelefone(rs.getString("telefone"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("ClienteDAO.findById: " + e.getMessage());
        }
        return null;
    }

    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("ClienteDAO.findAll: " + e.getMessage());
        }
        return lista;
    }

    public void update(Cliente c) {
        String sql = "UPDATE cliente SET nome=?, email=?, senha=?, telefone=? WHERE id_cliente = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getSenha());
            ps.setString(4, c.getTelefone());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ClienteDAO.update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ClienteDAO.delete: " + e.getMessage());
        }
    }
}