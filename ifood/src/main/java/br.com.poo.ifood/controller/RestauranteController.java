package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.RestauranteDAO;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;

public class RestauranteController {

    private RestauranteDAO dao = new RestauranteDAO();

    // Cadastrar restaurante
    public boolean cadastrar(Restaurante r) {
        return dao.cadastrar(r);
    }

    // Listar todos os restaurantes
    public List<Restaurante> listar() {
        return dao.listar();
    }

    // Buscar restaurante por ID
    public Restaurante buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Atualizar restaurante
    public boolean atualizar(Restaurante r) {
        return dao.atualizar(r);
    }

    // Remover restaurante por ID
    public boolean remover(int id) {
        return dao.remover(id);
    }
}
