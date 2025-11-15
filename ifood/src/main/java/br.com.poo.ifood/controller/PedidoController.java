package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.PedidoDAO;
import br.com.poo.ifood.model.Pedido;

import java.util.List;

public class PedidoController {
    private PedidoDAO dao = new PedidoDAO();

    public boolean cadastrar(Pedido p) {
        return dao.cadastrar(p);
    }

    public List<Pedido> listarPorCliente(int idCliente) {
        return dao.listarPorCliente(idCliente);
    }

    // MÉTODO NOVO: listar todos pedidos (para admin)
    public List<Pedido> listarTodos() {
        return dao.listarTodos();
    }
}
