package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void create(Produto p) {
        String sql = "INSERT INTO produto (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getPreco());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("ProdutoDAO.create: " + e.getMessage());
        }
    }

    public Produto findById(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id_produto"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    p.setPreco(rs.getDouble("preco"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("ProdutoDAO.findById: " + e.getMessage());
        }
        return null;
    }

    public List<Produto> findAll() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("ProdutoDAO.findAll: " + e.getMessage());
        }
        return lista;
    }

    public void update(Produto p) {
        String sql = "UPDATE produto SET nome=?, descricao=?, quantidade=?, preco=? WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getPreco());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ProdutoDAO.update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ProdutoDAO.delete: " + e.getMessage());
        }
    }
}