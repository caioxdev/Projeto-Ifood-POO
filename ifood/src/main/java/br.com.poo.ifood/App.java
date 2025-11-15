package br.com.poo.ifood;

import br.com.poo.ifood.view.ClientePainelView;
import br.com.poo.ifood.view.RestaurantePainelView;
import br.com.poo.ifood.view.SuperAdminView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Iniciando sistema com dados padrão...");
        Scanner sc = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n====== IFOOD ======");
            System.out.println("1. Cliente");
            System.out.println("2. Restaurante");
            System.out.println("3. SuperAdmin (Pedidos)");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                // --- MENU CLIENTE ---
                case 1 -> {
                    System.out.print("Digite o ID de Cliente: ");
                    int clienteId = sc.nextInt();
                    sc.nextLine();

                    // Construtor atual recebe apenas clienteId
                    ClientePainelView clienteView = new ClientePainelView(clienteId);
                    clienteView.mostrarMenu();
                }

                // --- MENU RESTAURANTE ---
                case 2 -> {
                    System.out.print("Digite o ID do Restaurante: ");
                    int restauranteId = sc.nextInt();
                    sc.nextLine();

                    // Construtor que recebe restauranteId e Scanner
                    RestaurantePainelView restauranteView = new RestaurantePainelView(restauranteId, sc);
                    restauranteView.mostrarMenu();
                }

                // --- MENU SUPERADMIN ---
                case 3 -> {
                    // Construtor que recebe Scanner
                    SuperAdminView superAdminView = new SuperAdminView(sc);
                    superAdminView.mostrarMenu();
                }

                // --- SAIR ---
                case 0 -> System.out.println("Saindo...");

                default -> System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
