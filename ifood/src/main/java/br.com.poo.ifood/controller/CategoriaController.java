package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.CategoriaDAO;
import br.com.poo.ifood.model.Categoria;

import java.util.List;

public class CategoriaController {
    private CategoriaDAO dao = new CategoriaDAO();

    public void cadastrar(Categoria c) {
        dao.create(c);
    }

    public List<Categoria> listar() {
        return dao.findAll();
    }

    public Categoria buscarPorId(int id) {
        return dao.findById(id);
    }

    public void atualizar(Categoria c) {
        dao.update(c);
    }

    public void deletar(int id) {
        dao.delete(id);
    }
}
