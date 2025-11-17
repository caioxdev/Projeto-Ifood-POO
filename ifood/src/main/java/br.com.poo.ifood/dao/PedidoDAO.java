package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.ItensPedido;
import br.com.poo.ifood.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean cadastrar(Pedido pedido) {
        String sql = "INSERT INTO pedido (cliente_id, restaurante_id, preco_total) VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pedido.getCliente_id());
            ps.setInt(2, pedido.getRestaurante_id());
            ps.setDouble(3, pedido.getPreco_total());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    pedido.setId_pedido(rs.getInt(1));
                    for (ItensPedido item : pedido.getItens()) {
                        adicionarItem(pedido, item);
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
        return false;
    }

    private boolean adicionarItem(Pedido pedido, ItensPedido item) {
        String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getId_pedido());
            ps.setInt(2, item.getProduto_id());
            ps.setInt(3, item.getQuantidade());
            ps.setDouble(4, item.getPreco_unitario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
        }
        return false;
    }

    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE cliente_id = ?";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));
                p.setItens(listarItensDoPedido(p.getId_pedido()));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    private List<ItensPedido> listarItensDoPedido(int pedidoId) {
        List<ItensPedido> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_pedido WHERE pedido_id = ?";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedidoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItensPedido item = new ItensPedido();
                item.setProduto_id(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco_unitario(rs.getDouble("preco_unitario"));
                itens.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens do pedido: " + e.getMessage());
        }
        return itens;
    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setPreco_total(rs.getDouble("preco_total"));
                p.setItens(listarItensDoPedido(p.getId_pedido()));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os pedidos: " + e.getMessage());
        }

        return pedidos;
    }
}
