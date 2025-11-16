package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao = new ProdutoDAO();

    // Listar todos os produtos
    public List<Produto> listar() {
        return dao.listar();
    }

    // Buscar produto por ID
    public Produto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    // **Novo método**: listar produtos por restaurante
    public List<Produto> listarPorRestaurante(int restauranteId) {
        return dao.listarPorRestaurante(restauranteId);
    }

    // Cadastrar produto
    public boolean cadastrar(Produto p) {
        return dao.cadastrar(p);
    }
}
