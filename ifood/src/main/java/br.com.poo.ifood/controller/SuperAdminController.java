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

    // ================= BUSCAR POR ID =================
    public SuperAdmin buscarPorId(int id) {
        List<SuperAdmin> lista = dao.listarTodos();
        for(SuperAdmin s : lista) {
            if(s.getId_admin() == id) {
                return s;
            }
        }
        return null;
    }

    // ================= BUSCAR POR EMAIL E SENHA =================
    public SuperAdmin buscarPorEmailESenha(String email, String senha) {
        return dao.buscarPorEmailESenha(email, senha);
    }

    // ================= LISTAR =================
    public List<SuperAdmin> listar() {
        return dao.listarTodos();
    }
}
