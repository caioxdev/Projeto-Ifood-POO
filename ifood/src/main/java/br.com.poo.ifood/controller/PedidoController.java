package br.com.poo.ifood.controller;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    public void criar(Pedido p) {
        String sql = "INSERT INTO pedido (cliente_id, restaurante_id, produto_id, quantidade, preco_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getCliente_id());
            stmt.setInt(2, p.getRestaurante_id());
            stmt.setInt(3, p.getProduto_id());
            stmt.setInt(4, p.getQuantidade());
            stmt.setDouble(5, p.getPreco_total());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }

    public List<Pedido> listar() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setCliente_id(rs.getInt("cliente_id"));
                p.setRestaurante_id(rs.getInt("restaurante_id"));
                p.setProduto_id(rs.getInt("produto_id"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco_total(rs.getDouble("preco_total"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
        return lista;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM pedido WHERE id_pedido=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar pedido: " + e.getMessage());
        }
    }
}
