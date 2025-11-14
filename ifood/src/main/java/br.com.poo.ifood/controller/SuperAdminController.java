package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.SuperAdminDAO;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.List;

public class SuperAdminController {
    private SuperAdminDAO dao = new SuperAdminDAO();

    public void cadastrar(SuperAdmin s) {
        dao.create(s);
    }

    public List<SuperAdmin> listar() {
        return dao.findAll();
    }

    public SuperAdmin buscarPorId(int id) {
        return dao.findById(id);
    }

    public void atualizar(SuperAdmin s) {
        dao.update(s);
    }

    public void deletar(int id) {
        dao.delete(id);
    }

    public SuperAdmin autenticar(String email, String senha) {
        return dao.findByEmailAndSenha(email, senha);
    }
}
