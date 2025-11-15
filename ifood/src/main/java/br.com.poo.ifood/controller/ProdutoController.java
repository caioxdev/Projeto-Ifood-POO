package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao = new ProdutoDAO();

    public boolean cadastrar(Produto p) {
        try {
            dao.cadastrar(p);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos os produtos cadastrados
     */
    public List<Produto> listar() {
        try {
            return dao.listar(); // <-- método listar() precisa existir no ProdutoDAO
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Lista produtos de um restaurante específico
     */
    public List<Produto> listarPorRestaurante(int idRestaurante) {
        try {
            return dao.listarPorRestaurante(idRestaurante);
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos por restaurante: " + e.getMessage());
            return List.of();
        }
    }

    public Produto buscarPorId(int id) {
        try {
            return dao.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }

    public boolean atualizar(Produto p) {
        try {
            dao.atualizar(p);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int id) {
        try {
            dao.remover(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            return false;
        }
    }
}
