package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.RestauranteDAO;
import br.com.poo.ifood.model.Restaurante;

public class RestauranteController {
    private RestauranteDAO dao;

    public RestauranteController() {
        this.dao = new RestauranteDAO();
    }

    public boolean cadastrar(Restaurante restaurante) {
        return dao.cadastrar(restaurante); // <--- aqui era 'create', agora é 'cadastrar'
    }

    public boolean atualizar(Restaurante restaurante) {
        return dao.atualizar(restaurante);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }

    public Restaurante buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public java.util.List<Restaurante> listar() {
        return dao.listar();
    }
}
