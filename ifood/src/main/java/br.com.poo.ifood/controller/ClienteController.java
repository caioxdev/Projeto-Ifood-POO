package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ClienteDAO;
import br.com.poo.ifood.model.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO dao = new ClienteDAO();

    public void cadastrar(Cliente c) {
        dao.create(c);
    }

    public List<Cliente> listar() {
        return dao.findAll();
    }

    public Cliente buscarPorId(int id) {
        return dao.findById(id);
    }

    public void atualizar(Cliente c) {
        dao.update(c);
    }

    public void deletar(int id) {
        dao.delete(id);
    }
}
