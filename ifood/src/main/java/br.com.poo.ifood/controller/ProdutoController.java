package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao = new ProdutoDAO();

    public boolean cadastrar(Produto p) {
        return dao.cadastrar(p);
    }

    public List<Produto> listar() {
        return dao.listar();
    }

    public Produto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Produto p) {
        // Aqui precisa implementar um método atualizar no DAO
        return dao.atualizar(p);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }

    public List<Produto> listarPorRestaurante(int restauranteId) {
        return dao.listarPorRestaurante(restauranteId);
    }
}
