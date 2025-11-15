package br.com.poo.ifood.controller;

import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    // Cadastrar produto
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

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos produtos
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = Conexao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id_produto"),
                        rs.getInt("restaurante_id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar produto por ID
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("id_produto"),
                        rs.getInt("restaurante_id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Listar produtos por restaurante
    public List<Produto> listarPorRestaurante(int restauranteId) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE restaurante_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restauranteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id_produto"),
                        rs.getInt("restaurante_id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Atualizar produto
    public boolean atualizar(Produto p) {
        String sql = "UPDATE produto SET nome=?, descricao=?, quantidade=?, preco=? WHERE id_produto=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getPreco());
            ps.setInt(5, p.getId_produto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remover produto
    public boolean remover(int id) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Checa se produto está em algum pedido
    public boolean produtoEmPedido(int produtoId) {
        String sql = "SELECT * FROM itens_pedido WHERE produto_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, produtoId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
