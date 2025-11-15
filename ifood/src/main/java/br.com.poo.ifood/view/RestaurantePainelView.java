package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.model.Produto;

import java.util.List;
import java.util.Scanner;

public class RestaurantePainelView {
    private ProdutoController produtoController = new ProdutoController();
    private int idRestaurante;

    public RestaurantePainelView(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- RESTAURANTE ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine().trim());

            switch (op) {
                case 1 -> cadastrarProduto(sc);
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto(sc);
                case 4 -> removerProduto(sc);
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void cadastrarProduto(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Quantidade: ");
        int q = Integer.parseInt(sc.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());

        Produto p = new Produto(idRestaurante, nome, desc, q, preco);
        if (produtoController.cadastrar(p)) {
            System.out.println("Produto cadastrado!");
        } else {
            System.out.println("Erro ao cadastrar produto.");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoController.listarPorRestaurante(idRestaurante);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void atualizarProduto(Scanner sc) {
        listarProdutos();
        System.out.print("ID do produto: ");
        int id = Integer.parseInt(sc.nextLine());
        Produto p = produtoController.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + p.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Nova descrição (" + p.getDescricao() + "): ");
        String desc = sc.nextLine();
        System.out.print("Nova quantidade (" + p.getQuantidade() + "): ");
        String qStr = sc.nextLine();
        System.out.print("Novo preço (" + p.getPreco() + "): ");
        String precoStr = sc.nextLine();

        p.setNome(nome.isEmpty() ? p.getNome() : nome);
        p.setDescricao(desc.isEmpty() ? p.getDescricao() : desc);
        p.setQuantidade(qStr.isEmpty() ? p.getQuantidade() : Integer.parseInt(qStr));
        p.setPreco(precoStr.isEmpty() ? p.getPreco() : Double.parseDouble(precoStr));

        if (produtoController.atualizar(p)) {
            System.out.println("Produto atualizado!");
        } else {
            System.out.println("Erro ao atualizar produto.");
        }
    }

    private void removerProduto(Scanner sc) {
        listarProdutos();
        System.out.print("ID do produto: ");
        int id = Integer.parseInt(sc.nextLine());
        if (produtoController.remover(id)) {
            System.out.println("Produto removido!");
        } else {
            System.out.println("Erro ao remover produto.");
        }
    }
}
