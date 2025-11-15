package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.ItemPedido;
import br.com.poo.ifood.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // Cadastrar pedido principal e preencher o ID gerado
    public boolean cadastrar(Pedido pedido) {
        String sql = "INSERT INTO pedido (cliente_id, restaurante_id, preco_total) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getCliente_id());
            stmt.setInt(2, pedido.getRestaurante_id());
            stmt.setDouble(3, pedido.getPreco_total());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    pedido.setId_pedido(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao cadastrar pedido: " + e.getMessage());
        }

        return false;
    }

    // Cadastrar itens do pedido
    public boolean cadastrarItens(List<ItemPedido> itens) {
        String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (ItemPedido item : itens) {
                stmt.setInt(1, item.getPedido_id());
                stmt.setInt(2, item.getProduto_id());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getPreco_unitario());
                stmt.addBatch();
            }

            stmt.executeBatch();
            return true;

        } catch (SQLException e) {
            System.out.println("ERRO ao cadastrar itens do pedido: " + e.getMessage());
        }

        return false;
    }

    // Listar pedidos de um cliente
    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE cliente_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("cliente_id"),
                        rs.getInt("restaurante_id"),
                        rs.getDouble("preco_total")
                );
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setItens(listarItens(pedido.getId_pedido()));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar itens de um pedido
    public List<ItemPedido> listarItens(int pedidoId) {
        List<ItemPedido> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_pedido WHERE pedido_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido(
                        rs.getInt("pedido_id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco_unitario")
                );
                item.setId_item(rs.getInt("id_item"));
                itens.add(item);
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao listar itens do pedido: " + e.getMessage());
        }

        return itens;
    }

    // Listar todos os pedidos (para SuperAdmin)
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("cliente_id"),
                        rs.getInt("restaurante_id"),
                        rs.getDouble("preco_total")
                );
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setItens(listarItens(pedido.getId_pedido())); // adiciona os itens do pedido
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao listar todos os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

}
