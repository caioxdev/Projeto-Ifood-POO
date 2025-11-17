package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.FavoritoDAO;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.controller.RestauranteController;

import java.util.List;
import java.util.stream.Collectors;

public class FavoritoController {

    private FavoritoDAO dao = new FavoritoDAO();
    private RestauranteController restauranteController = new RestauranteController();

    public boolean adicionarFavorito(int clienteId, int restauranteId) {
        if (restauranteController.buscarPorId(restauranteId) == null) {
            System.out.println("Restaurante não existe.");
            return false;
        }
        return dao.adicionar(clienteId, restauranteId);
    }

    public boolean removerFavorito(int clienteId, int restauranteId) {
        return dao.remover(clienteId, restauranteId);
    }

    public List<Restaurante> listarFavoritos(int clienteId) {
        return dao.listarPorCliente(clienteId).stream()
                .map(f -> restauranteController.buscarPorId(f.getRestaurante_id()))
                .filter(r -> r != null)
                .collect(Collectors.toList());
    }
}
