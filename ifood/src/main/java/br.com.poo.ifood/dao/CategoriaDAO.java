package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void create(Categoria c) {
        String sql = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("CategoriaDAO.create: " + e.getMessage());
        }
    }

    public Categoria findById(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria c = new Categoria();
                    c.setId(rs.getInt("id_categoria"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("CategoriaDAO.findById: " + e.getMessage());
        }
        return null;
    }

    public List<Categoria> findAll() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("CategoriaDAO.findAll: " + e.getMessage());
        }
        return lista;
    }

    public void update(Categoria c) {
        String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("CategoriaDAO.update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("CategoriaDAO.delete: " + e.getMessage());
        }
    }
}