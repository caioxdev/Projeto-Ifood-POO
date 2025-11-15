package br.com.poo.ifood.view;

import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.model.Produto;

import java.util.List;
import java.util.Scanner;

public class RestaurantePainelView {
    private final int restauranteId;
    private final Scanner sc;
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public RestaurantePainelView(int restauranteId, Scanner sc) {
        this.restauranteId = restauranteId;
        this.sc = sc;
    }

    public void mostrarMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- RESTAURANTE ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 4 -> removerProduto();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();
        sc.nextLine();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        Produto produto = new Produto(restauranteId, nome, descricao, quantidade, preco);
        if (produtoDAO.cadastrar(produto)) {
            System.out.println("Produto cadastrado com sucesso!");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarPorRestaurante(restauranteId);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : produtos) {
            System.out.println("----------------------------");
            System.out.println("ID: " + p.getId_produto());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Descrição: " + p.getDescricao());
            System.out.println("Quantidade: " + p.getQuantidade());
            System.out.println("Preço: R$ " + String.format("%.2f", p.getPreco()));
            System.out.println("----------------------------");
        }
    }

    private void atualizarProduto() {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();
        Produto p = produtoDAO.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Nome (" + p.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Descrição (" + p.getDescricao() + "): ");
        String descricao = sc.nextLine();
        System.out.print("Quantidade (" + p.getQuantidade() + "): ");
        int quantidade = sc.nextInt();
        sc.nextLine();
        System.out.print("Preço (" + p.getPreco() + "): ");
        double preco = sc.nextDouble();
        sc.nextLine();

        p.setNome(nome);
        p.setDescricao(descricao);
        p.setQuantidade(quantidade);
        p.setPreco(preco);

        if (produtoDAO.atualizar(p)) {
            System.out.println("Produto atualizado com sucesso!");
        }
    }

    private void removerProduto() {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (produtoDAO.remover(id)) {
            System.out.println("Produto removido com sucesso!");
        }
    }
}
