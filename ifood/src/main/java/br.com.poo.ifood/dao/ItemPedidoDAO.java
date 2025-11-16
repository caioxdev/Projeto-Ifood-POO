package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemPedidoDAO {

    // Cadastrar itens de um pedido, passando o id do pedido
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
        }

        return false;
    }
}
