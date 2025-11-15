package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Cadastrar produto
    public boolean cadastrar(Produto p) {
        String sql = "INSERT INTO produto (restaurante_id, nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, p.getRestaurante_id());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDescricao());
            stmt.setInt(4, p.getQuantidade());
            stmt.setDouble(5, p.getPreco());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    p.setId_produto(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao cadastrar produto: " + e.getMessage());
        }
        return false;
    }

    // Listar todos os produtos
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("restaurante_id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                p.setId_produto(rs.getInt("id_produto"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    // Listar produtos de um restaurante específico
    public List<Produto> listarPorRestaurante(int restauranteId) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE restaurante_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, restauranteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("restaurante_id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                p.setId_produto(rs.getInt("id_produto"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao listar produtos por restaurante: " + e.getMessage());
        }
        return produtos;
    }

    // Buscar produto pelo ID
    public Produto buscarPorId(int idProduto) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("restaurante_id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                p.setId_produto(rs.getInt("id_produto"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    // Atualizar produto
    public boolean atualizar(Produto p) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, quantidade = ?, preco = ? WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getId_produto());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar produto: " + e.getMessage());
        }
        return false;
    }

    // Remover produto
    public boolean remover(int idProduto) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERRO ao remover produto: " + e.getMessage());
        }
        return false;
    }

    // Verificar se o produto está em algum pedido
    public boolean produtoEmPedido(int idProduto) {
        String sql = "SELECT COUNT(*) FROM itens_pedido WHERE produto_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao verificar produto em pedido: " + e.getMessage());
        }
        return false;
    }
}
