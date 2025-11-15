package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ClienteDAO;
import br.com.poo.ifood.model.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO dao = new ClienteDAO();

    public boolean cadastrar(Cliente c) {
        return dao.create(c);
    }

    public List<Cliente> listar() {
        return dao.findAll();
    }

    public Cliente buscarPorId(int id) {
        return dao.findById(id);
    }

    public boolean atualizar(Cliente c) {
        return dao.update(c);
    }

    public boolean remover(int id) {
        return dao.delete(id);
    }
}
