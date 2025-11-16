package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ClienteDAO;
import br.com.poo.ifood.model.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO dao = new ClienteDAO();

    public boolean cadastrar(Cliente c) {
        return dao.cadastrar(c);
    }

    public Cliente buscarPorEmailESenha(String email, String senha) {
        return dao.buscarPorEmailESenha(email, senha);
    }

    public List<Cliente> listar() {
        return dao.listarTodos();
    }
}
