package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ClienteDAO;
import br.com.poo.ifood.model.Cliente;

public class ClienteController {
    private ClienteDAO dao = new ClienteDAO();

    // Cadastrar cliente
    public boolean cadastrar(Cliente cliente) {
        return dao.cadastrar(cliente);
    }

    // Buscar cliente por email e senha (login)
    public Cliente buscarPorEmailESenha(String email, String senha) {
        return dao.buscarPorEmailESenha(email, senha);
    }
}
