package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.model.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private Scanner sc;
    private ProdutoController controller = new ProdutoController();

    public ProdutoView(Scanner sc) { this.sc = sc; }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- PRODUTOS ---");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Atualizar produto");
            System.out.println("4. Remover produto");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch(op) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 4 -> removerProduto();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while(op != 0);
    }

    private void cadastrarProduto() {
        Produto p = new Produto();
        System.out.print("ID do restaurante: ");
        p.setRestaurante_id(Integer.parseInt(sc.nextLine()));
        System.out.print("Nome: ");
        p.setNome(sc.nextLine());
        System.out.print("Descrição: ");
        p.setDescricao(sc.nextLine());
        System.out.print("Quantidade: ");
        p.setQuantidade(Integer.parseInt(sc.nextLine()));
        System.out.print("Preço: ");
        p.setPreco(Double.parseDouble(sc.nextLine()));

        if(controller.cadastrar(p)) System.out.println("Produto cadastrado!");
        else System.out.println("Erro ao cadastrar produto.");
    }

    private void listarProdutos() {
        List<Produto> lista = controller.listar();
        if(lista.isEmpty()) System.out.println("Nenhum produto encontrado.");
        else {
            System.out.println("\n--- LISTA DE PRODUTOS ---");
            for(Produto p : lista) {
                System.out.println("ID: " + p.getId_produto() +
                        " | Restaurante ID: " + p.getRestaurante_id() +
                        " | Nome: " + p.getNome() +
                        " | Descrição: " + p.getDescricao() +
                        " | Quantidade: " + p.getQuantidade() +
                        " | Preço: " + p.getPreco());
            }
        }
    }

    private void atualizarProduto() { /* similar ao cadastrar, mas busca por id */ }
    private void removerProduto() { /* verifica se está em pedidos antes de remover */ }
}
