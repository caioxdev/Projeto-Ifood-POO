package br.com.poo.ifood;

import br.com.poo.ifood.view.ClientePainelView;
import br.com.poo.ifood.view.RestaurantePainelView;
import br.com.poo.ifood.view.SuperAdminView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClientePainelView clientePainel = new ClientePainelView();
        RestaurantePainelView restaurantePainel = new RestaurantePainelView();
        SuperAdminView superAdminView = new SuperAdminView();

        int opcao;
        do {
            System.out.println("\n====== IFOOD TERMINAL ======");
            System.out.println("1. Sou Cliente");
            System.out.println("2. Sou Restaurante");
            System.out.println("3. Sou SuperAdmin");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            opcao = Integer.parseInt(line);
            switch (opcao) {
                case 1 -> clientePainel.menu(sc);
                case 2 -> restaurantePainel.menu(sc);
                case 3 -> superAdminView.loginMenu(sc);
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}
