package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void create(Pedido p) {
        String sql = "INSERT INTO pedido (cliente_id, restaurante_id, produto_id, quantidade, preco_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getClienteId());
            ps.setInt(2, p.getRestauranteId());
            ps.setInt(3, p.getProdutoId());
            ps.setInt(4, p.getQuantidade());
            ps.setDouble(5, p.getPrecoTotal());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("PedidoDAO.create: " + e.getMessage());
        }
    }

    public Pedido findById(int id) {
        String sql = "SELECT * FROM pedido WHERE id_pedido = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pedido p = new Pedido();
                    p.setId(rs.getInt("id_pedido"));
                    p.setClienteId(rs.getInt("cliente_id"));
                    p.setRestauranteId(rs.getInt("restaurante_id"));
                    p.setProdutoId(rs.getInt("produto_id"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    p.setPrecoTotal(rs.getDouble("preco_total"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("PedidoDAO.findById: " + e.getMessage());
        }
        return null;
    }

    public List<Pedido> findAll() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id_pedido"));
                p.setClienteId(rs.getInt("cliente_id"));
                p.setRestauranteId(rs.getInt("restaurante_id"));
                p.setProdutoId(rs.getInt("produto_id"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPrecoTotal(rs.getDouble("preco_total"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("PedidoDAO.findAll: " + e.getMessage());
        }
        return lista;
    }

    public void update(Pedido p) {
        String sql = "UPDATE pedido SET cliente_id=?, restaurante_id=?, produto_id=?, quantidade=?, preco_total=? WHERE id_pedido = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getClienteId());
            ps.setInt(2, p.getRestauranteId());
            ps.setInt(3, p.getProdutoId());
            ps.setInt(4, p.getQuantidade());
            ps.setDouble(5, p.getPrecoTotal());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("PedidoDAO.update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("PedidoDAO.delete: " + e.getMessage());
        }
    }
}
