package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.RestauranteDAO;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;

public class RestauranteController {
    private RestauranteDAO dao = new RestauranteDAO();

    public boolean cadastrar(Restaurante r) {
        return dao.cadastrar(r);
    }

    public List<Restaurante> listar() {
        return dao.listar();
    }

    public Restaurante buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Restaurante r) {
        return dao.atualizar(r);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }
}
