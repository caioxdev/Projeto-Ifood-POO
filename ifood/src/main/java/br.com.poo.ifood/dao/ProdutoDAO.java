package br.com.poo.ifood.dao;

import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public boolean cadastrar(Produto p) {
        String sql = """
            INSERT INTO produto (restaurante_id, nome, descricao, quantidade, preco)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getRestaurante_id());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDescricao());
            stmt.setInt(4, p.getQuantidade());
            stmt.setDouble(5, p.getPreco());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("ERRO ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

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

        } catch (Exception e) {
            System.out.println("ERRO ao buscar produto: " + e.getMessage());
        }

        return null;
    }

    public List<Produto> listarPorRestaurante(int idRestaurante) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE restaurante_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);
            ResultSet rs = stmt.executeQuery();

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

        } catch (Exception e) {
            System.out.println("ERRO ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

        } catch (Exception e) {
            System.out.println("ERRO ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

    public boolean atualizar(Produto p) {

        String sql = """
            UPDATE produto 
            SET nome = ?, descricao = ?, quantidade = ?, preco = ?
            WHERE id_produto = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getId_produto());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("ERRO ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int idProduto) {

        String sql = "DELETE FROM produto WHERE id_produto = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("ERRO ao remover produto: " + e.getMessage());
            return false;
        }
    }
}
