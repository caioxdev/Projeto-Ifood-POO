package br.com.poo.ifood.controller;

import java.util.Scanner;

public class PedidoController {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Pedido ===");
            System.out.println("1 - Fazer Pedido");
            System.out.println("2 - Listar Pedidos (Cliente)");
            System.out.println("3 - Listar Pedidos (Restaurante)");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> fazerPedido();
                case 2 -> listarPedidosCliente();
                case 3 -> listarPedidosRestaurante();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void fazerPedido() {
        System.out.println("Realizando novo pedido...");
    }

    public void listarPedidosCliente() {
        System.out.println("Listando pedidos do cliente...");
    }

    public void listarPedidosRestaurante() {
        System.out.println("Listando pedidos do restaurante...");
    }
}
