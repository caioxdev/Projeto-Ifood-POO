package br.com.poo.ifood.controller;

import java.util.Scanner;

public class RestauranteController {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Restaurante ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Consultar Pedidos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> new ProdutoController().cadastrar();
                case 2 -> new ProdutoController().listar();
                case 3 -> new PedidoController().listarPedidosRestaurante();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void listar() {
        System.out.println("Listando restaurantes cadastrados...");
    }
}
