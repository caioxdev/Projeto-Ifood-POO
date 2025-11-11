package br.com.poo.ifood.controller;

import java.util.Scanner;

public class ClienteController {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1 - Ver Restaurantes");
            System.out.println("2 - Ver Categorias");
            System.out.println("3 - Fazer Pedido");
            System.out.println("4 - Consultar Pedidos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> new RestauranteController().listar();
                case 2 -> new CategoriaController().listar();
                case 3 -> new PedidoController().fazerPedido();
                case 4 -> new PedidoController().listarPedidosCliente();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
