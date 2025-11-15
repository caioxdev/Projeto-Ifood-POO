package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.PedidoDAO;
import br.com.poo.ifood.model.Pedido;

import java.util.List;

public class PedidoController {

    private final PedidoDAO dao = new PedidoDAO();

    // Cadastrar pedido principal e seus itens
    public boolean cadastrar(Pedido pedido) {
        boolean pedidoCadastrado = dao.cadastrar(pedido);
        if (pedidoCadastrado) {
            // Atualiza o pedido_id em cada item
            pedido.getItens().forEach(item -> item.setPedido_id(pedido.getId_pedido()));
            return dao.cadastrarItens(pedido.getItens());
        }
        return false;
    }

    // Listar pedidos de um cliente
    public List<Pedido> listarPorCliente(int clienteId) {
        return dao.listarPorCliente(clienteId);
    }

    // Listar todos os pedidos (para SuperAdmin)
    public List<Pedido> listarTodos() {
        return dao.listarTodos();
    }
}
