package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.SuperAdminDAO;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.List;

public class SuperAdminController {

    private SuperAdminDAO dao = new SuperAdminDAO();

    // ================= CADASTRAR =================
    public boolean cadastrar(SuperAdmin s) {
        return dao.cadastrar(s);
    }

    // ================= LOGIN =================
    public SuperAdmin buscarPorEmailESenha(String email, String senha) {
        return dao.buscarPorEmailESenha(email, senha);
    }

    // ================= LISTAR =================
    public List<SuperAdmin> listar() {
        return dao.listarTodos();
    }
}
