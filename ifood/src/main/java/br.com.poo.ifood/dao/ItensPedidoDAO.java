package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.ItensPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItensPedidoDAO {
    public boolean cadastrar(ItensPedido item) {
        String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getPedido_id());
            ps.setInt(2, item.getProduto_id());
            ps.setInt(3, item.getQuantidade());
            ps.setDouble(4, item.getPreco_unitario());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar item: " + e.getMessage());
        }

        return false;
    }
}
