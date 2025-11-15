package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean create(Cliente c) {
        String sql = "INSERT INTO cliente (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getTelefone());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) return false;

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                c.setId_cliente(generatedKeys.getInt(1));
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return lista;
    }

    public Cliente findById(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                return c;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Cliente c) {
        String sql = "UPDATE cliente SET nome=?, email=?, senha=?, telefone=? WHERE id_cliente=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getTelefone());
            stmt.setInt(5, c.getId_cliente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }
}
