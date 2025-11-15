package br.com.poo.ifood.controller;

import br.com.poo.ifood.model.ItemPedido;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    // Cadastrar pedido
    public boolean cadastrar(Pedido p) {
        String sqlPedido = "INSERT INTO pedido (cliente_id, restaurante_id, preco_total) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false); // transação

            try (PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                psPedido.setInt(1, p.getCliente_id());
                psPedido.setInt(2, p.getRestaurante_id());
                psPedido.setDouble(3, p.getPreco_total());

                int affectedRows = psPedido.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }

                ResultSet rs = psPedido.getGeneratedKeys();
                if (rs.next()) {
                    int pedidoId = rs.getInt(1);

                    try (PreparedStatement psItem = conn.prepareStatement(sqlItem)) {
                        for (ItemPedido item : p.getItens()) {
                            psItem.setInt(1, pedidoId);
                            psItem.setInt(2, item.getProduto_id());
                            psItem.setInt(3, item.getQuantidade());
                            psItem.setDouble(4, item.getPreco_unitario());
                            psItem.addBatch();
                        }
                        psItem.executeBatch();
                    }

                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos os pedidos
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection conn = Conexao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));
                p.setItens(listarItens(p.getId_pedido()));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    // Listar pedidos de um cliente
    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE cliente_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));
                p.setItens(listarItens(p.getId_pedido()));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    // Listar pedidos de um restaurante
    public List<Pedido> listarPorRestaurante(int restauranteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE restaurante_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restauranteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));
                p.setItens(listarItens(p.getId_pedido()));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    // Listar itens de um pedido
    private List<ItemPedido> listarItens(int pedidoId) {
        List<ItemPedido> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_pedido WHERE pedido_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pedidoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
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
