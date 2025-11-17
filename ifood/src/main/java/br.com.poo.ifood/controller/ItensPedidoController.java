package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ItensPedidoDAO;
import br.com.poo.ifood.model.ItensPedido;

public class ItensPedidoController {

    private ItensPedidoDAO dao = new ItensPedidoDAO();

    public boolean cadastrar(ItensPedido item) {
        return dao.cadastrar(item);
    }
}
