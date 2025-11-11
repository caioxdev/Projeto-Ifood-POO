package br.com.poo.ifood;

import java.util.Scanner;
import br.com.poo.ifood.controller.*;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===============================");
            System.out.println("             iFood               ");
            System.out.println("=================================");
            System.out.println("1 - Cliente");
            System.out.println("2 - Restaurante");
            System.out.println("3 - Categoria");
            System.out.println("4 - Produto");
            System.out.println("5 - Pedido");
            System.out.println("6 - SuperAdmin");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> new ClienteController().menu();
                case 2 -> new RestauranteController().menu();
                case 3 -> new CategoriaController().menu();
                case 4 -> new ProdutoController().menu();
                case 5 -> new PedidoController().menu();
                case 6 -> new SuperAdminController().menu();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}
