package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao = new ProdutoDAO();

    public void cadastrar(Produto p) {
        dao.create(p);
    }

    public List<Produto> listar() {
        return dao.findAll();
    }

    public Produto buscarPorId(int id) {
        return dao.findById(id);
    }

    public void atualizar(Produto p) {
        dao.update(p);
    }

    public void deletar(int id) {
        dao.delete(id);
    }
}
