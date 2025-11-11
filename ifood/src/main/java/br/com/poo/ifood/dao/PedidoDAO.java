package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Pedido;
import java.sql.*;
import java.util.*;

public class PedidoDAO {

    public void create(Pedido pedido) {
        String sql = "INSERT INTO Pedido (cliente_id, restaurante_id, produto_id, quantidade, preco_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getCliente_id());
            stmt.setInt(2, pedido.getRestaurante_id());
            stmt.setInt(3, pedido.getProduto_id());
            stmt.setInt(4, pedido.getQuantidade());
            stmt.setDouble(5, pedido.getPreco_total());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pedido> readAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setProduto_id(rs.getInt("produto_id"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco_total(rs.getDouble("preco_total"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pedidos;
    }

    public void update(Pedido pedido) {
        String sql = "UPDATE Pedido SET cliente_id=?, restaurante_id=?, produto_id=?, quantidade=?, preco_total=? WHERE id_pedido=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getCliente_id());
            stmt.setInt(2, pedido.getRestaurante_id());
            stmt.setInt(3, pedido.getProduto_id());
            stmt.setInt(4, pedido.getQuantidade());
            stmt.setDouble(5, pedido.getPreco_total());
            stmt.setInt(6, pedido.getId_pedido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Pedido WHERE id_pedido=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
