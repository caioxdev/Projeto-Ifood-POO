package br.com.poo.ifood.view;

import java.util.Scanner;

public class ClientePainelView {

    private RestauranteView restauranteView = new RestauranteView();
    private ProdutoView produtoView = new ProdutoView();
    private PedidoView pedidoView = new PedidoView();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n====== PAINEL DO CLIENTE ======");
            System.out.println("1. Listar Restaurantes");
            System.out.println("2. Ver Produtos de um Restaurante (pelo ID do restaurante)");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Ver Meus Pedidos (lista geral)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> restauranteView.listar();
                case 2 -> produtoView.listarPorRestaurante(sc);
                case 3 -> pedidoView.cadastrar(sc);
                case 4 -> pedidoView.listar();
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }
}
