package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.ItemPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // Cadastrar pedido (retorna ID gerado)
    public int cadastrar(Pedido pedido) {
        String sql = "INSERT INTO pedido (cliente_id, restaurante_id, preco_total) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pedido.getCliente_id());
            ps.setInt(2, pedido.getRestaurante_id());
            ps.setDouble(3, pedido.getPreco_total());

            int linhas = ps.executeUpdate();
            if (linhas == 0) return -1;

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                pedido.setId_pedido(id);
                return id;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
        return -1;
    }

    // Cadastrar itens de um pedido
    public boolean cadastrarItens(List<ItemPedido> itens, int pedidoId) {
        String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (ItemPedido item : itens) {
                ps.setInt(1, pedidoId);
                ps.setInt(2, item.getProduto_id());
                ps.setInt(3, item.getQuantidade());
                ps.setDouble(4, item.getPreco_unitario());
                ps.addBatch();
            }

            ps.executeBatch();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar itens do pedido: " + e.getMessage());
            return false;
        }
    }

    // Listar pedidos por cliente
    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE cliente_id=?";
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
                pedidos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos do cliente: " + e.getMessage());
        }
        return pedidos;
    }

    // Listar todos pedidos (admin)
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
                pedidos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos pedidos: " + e.getMessage());
        }
        return pedidos;
    }
}
