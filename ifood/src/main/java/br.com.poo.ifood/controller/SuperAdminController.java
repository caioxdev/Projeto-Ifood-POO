package br.com.poo.ifood.controller;

import java.util.Scanner;

public class SuperAdminController {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Menu SuperAdmin ===");
            System.out.println("1 - Gerenciar Restaurantes");
            System.out.println("2 - Gerenciar Categorias");
            System.out.println("3 - Gerenciar Produtos");
            System.out.println("4 - Consultar Pedidos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> new RestauranteController().menu();
                case 2 -> new CategoriaController().menu();
                case 3 -> new ProdutoController().menu();
                case 4 -> new PedidoController().menu();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
