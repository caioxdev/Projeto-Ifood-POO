package br.com.poo.ifood;

import br.com.poo.ifood.view.ClientePainelView;
import br.com.poo.ifood.view.RestaurantePainelView;
import br.com.poo.ifood.view.PedidoView;
import br.com.poo.ifood.view.ClienteCadastroView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Iniciando sistema com dados default...");
        Scanner sc = new Scanner(System.in);

        int op;
        do {
            System.out.println("\n====== IFOOD ======");
            System.out.println("1. Cliente");
            System.out.println("2. Restaurante");
            System.out.println("3. SuperAdmin (Pedidos)");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> {
                    System.out.println("1. Entrar");
                    System.out.println("2. Cadastrar Cliente");
                    int clienteOp = Integer.parseInt(sc.nextLine());
                    if (clienteOp == 1) {
                        System.out.print("Digite seu ID de Cliente: ");
                        int idCliente = Integer.parseInt(sc.nextLine());
                        new ClientePainelView(idCliente).menu(sc);
                    } else if (clienteOp == 2) {
                        new ClienteCadastroView().cadastrarCliente(sc);
                    }
                }
                case 2 -> {
                    System.out.print("Digite o ID do Restaurante: ");
                    int idRestaurante = Integer.parseInt(sc.nextLine());
                    new RestaurantePainelView(idRestaurante).menu(sc);
                }
                case 3 -> new PedidoView().menu(sc); // admin
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);

        sc.close();
    }
}
