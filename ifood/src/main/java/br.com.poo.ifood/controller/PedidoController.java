package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.PedidoDAO;
import br.com.poo.ifood.model.Pedido;

import java.util.List;

public class PedidoController {
    private PedidoDAO dao = new PedidoDAO();

    public void cadastrar(Pedido p) {
        dao.create(p);
    }

    public List<Pedido> listar() {
        return dao.findAll();
    }

    public Pedido buscarPorId(int id) {
        return dao.findById(id);
    }

    public void atualizar(Pedido p) {
        dao.update(p);
    }

    public void deletar(int id) {
        dao.delete(id);
    }
}
