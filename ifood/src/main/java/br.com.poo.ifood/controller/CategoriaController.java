package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.CategoriaDAO;
import br.com.poo.ifood.model.Categoria;

import java.util.List;

public class CategoriaController {
    private CategoriaDAO dao = new CategoriaDAO();

    public boolean cadastrar(Categoria c) {
        return dao.cadastrar(c); // aqui o dao deve retornar boolean
    }

    public boolean atualizar(Categoria c) {
        return dao.atualizar(c); // dao deve retornar boolean
    }

    public boolean remover(int id) {
        return dao.remover(id); // dao deve retornar boolean
    }

    public List<Categoria> listar() {
        return dao.listar();
    }

    public Categoria buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
