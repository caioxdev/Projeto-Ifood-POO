package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.model.Produto;

import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    // Cadastrar produto
    public boolean cadastrar(Produto p) {
        return produtoDAO.cadastrar(p);
    }

    // Listar todos os produtos
    public List<Produto> listar() {
        return produtoDAO.listar();
    }

    // Listar produtos de um restaurante específico
    public List<Produto> listarPorRestaurante(int restauranteId) {
        return produtoDAO.listarPorRestaurante(restauranteId);
    }

    // Buscar produto pelo ID
    public Produto buscarPorId(int idProduto) {
        return produtoDAO.buscarPorId(idProduto);
    }

    // Atualizar produto
    public boolean atualizar(Produto p) {
        return produtoDAO.atualizar(p);
    }

    // Remover produto
    public boolean remover(int idProduto) {
        return produtoDAO.remover(idProduto);
    }

    // Verificar se o produto está em algum pedido
    public boolean produtoEmPedido(int idProduto) {
        return produtoDAO.produtoEmPedido(idProduto);
    }
}
