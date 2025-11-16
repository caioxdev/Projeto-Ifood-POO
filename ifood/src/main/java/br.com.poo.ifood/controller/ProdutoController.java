package br.com.poo.ifood.controller;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    public boolean cadastrar(Produto p) {
        String sql = "INSERT INTO produto (restaurante_id, nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getRestaurante_id());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getDescricao());
            ps.setInt(4, p.getQuantidade());
            ps.setDouble(5, p.getPreco());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = Conexao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId_produto(rs.getInt("id_produto"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<Produto> listarPorRestaurante(int restauranteId) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE restaurante_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, restauranteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId_produto(rs.getInt("id_produto"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto p = new Produto();
                p.setId_produto(rs.getInt("id_produto"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                return p;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean atualizar(Produto p) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, quantidade = ?, preco = ? WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getPreco());
            ps.setInt(5, p.getId_produto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean produtoEmPedido(int produtoId) {
        String sql = "SELECT COUNT(*) as c FROM itens_pedido WHERE produto_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, produtoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("c") > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
