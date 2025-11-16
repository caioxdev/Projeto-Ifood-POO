package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.PedidoDAO;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.ItemPedido;

import java.util.List;

public class PedidoController {

    private PedidoDAO pedidoDAO = new PedidoDAO();

    // Cadastrar pedido
    public boolean cadastrar(Pedido pedido) {
        int id = pedidoDAO.cadastrar(pedido);
        return id > 0;
    }

    // Cadastrar itens do pedido
    public boolean cadastrarItens(List<ItemPedido> itens, int pedidoId) {
        return pedidoDAO.cadastrarItens(itens, pedidoId);
    }

    // Listar pedidos de um cliente
    public List<Pedido> listarPorCliente(int clienteId) {
        return pedidoDAO.listarPorCliente(clienteId);
    }

    // Listar todos pedidos
    public List<Pedido> listarTodos() {
        return pedidoDAO.listarTodos();
    }
}
