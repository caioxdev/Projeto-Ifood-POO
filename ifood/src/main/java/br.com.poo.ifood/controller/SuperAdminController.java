package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.SuperAdminDAO;
import br.com.poo.ifood.model.SuperAdmin;

public class SuperAdminController {
    private SuperAdminDAO dao = new SuperAdminDAO();

    public boolean cadastrar(SuperAdmin s) {
        return dao.cadastrar(s);
    }

    public SuperAdmin buscarPorEmailESenha(String email, String senha) {
        return dao.buscarPorEmailESenha(email, senha);
    }
}
