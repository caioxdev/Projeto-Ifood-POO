package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.CategoriaDAO;
import br.com.poo.ifood.model.Categoria;

import java.util.List;

public class CategoriaController {

    private CategoriaDAO dao = new CategoriaDAO();

    public boolean cadastrar(Categoria c) {
        return dao.cadastrar(c);
    }

    public List<Categoria> listar() {
        return dao.listar();
    }

    public boolean atualizar(Categoria c) {
        return dao.atualizar(c);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }

    // <-- ADICIONE ESTE MÉTODO
    public Categoria buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
