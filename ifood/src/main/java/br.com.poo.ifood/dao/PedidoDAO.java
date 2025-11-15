package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.ItemPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // cadastra o pedido e seus itens em transação
    public boolean cadastrar(Pedido p) {
        String sqlPedido = "INSERT INTO pedido (cliente_id, restaurante_id, preco_total) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);

            // Inserir pedido
            try (PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {

                stmtPedido.setInt(1, p.getCliente_id());
                stmtPedido.setInt(2, p.getRestaurante_id());
                stmtPedido.setDouble(3, p.getPreco_total());

                int affected = stmtPedido.executeUpdate();
                if (affected == 0) throw new SQLException("Falha ao inserir pedido.");

                ResultSet rsKeys = stmtPedido.getGeneratedKeys();
                if (rsKeys.next()) {
                    p.setId_pedido(rsKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID do pedido.");
                }
            }

            // Inserir itens do pedido
            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                for (ItemPedido item : p.getItens()) {

                    stmtItem.setInt(1, p.getId_pedido());
                    stmtItem.setInt(2, item.getProduto_id());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getPreco_unitario());

                    stmtItem.addBatch();
                }
                stmtItem.executeBatch();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // listar pedidos de um cliente
    public List<Pedido> listarPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE cliente_id = ? ORDER BY id_pedido DESC";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();

                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));

                p.setItens(buscarItensDoPedido(p.getId_pedido()));

                pedidos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    // listar todos os pedidos (admin)
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido ORDER BY id_pedido DESC";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();

                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));

                p.setItens(buscarItensDoPedido(p.getId_pedido()));

                pedidos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    // buscar itens de um pedido
    private List<ItemPedido> buscarItensDoPedido(int idPedido) {
        List<ItemPedido> itens = new ArrayList<>();

        String sql =
                "SELECT pi.*, pr.nome AS produto_nome " +
                        "FROM itens_pedido pi " +
                        "LEFT JOIN produto pr ON pr.id_produto = pi.produto_id " +
                        "WHERE pi.pedido_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();

                item.setPedido_id(rs.getInt("pedido_id"));
                item.setProduto_id(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco_unitario(rs.getDouble("preco_unitario"));

                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }
}
