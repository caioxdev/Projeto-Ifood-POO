package br.com.poo.ifood;

import br.com.poo.ifood.view.ClienteView;
import br.com.poo.ifood.view.RestauranteView;
import br.com.poo.ifood.view.SuperAdminView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClienteView clienteView = new ClienteView(sc);
        RestauranteView restauranteView = new RestauranteView(sc);
        SuperAdminView superAdminView = new SuperAdminView(sc);

        int op;
        do {
            System.out.println("\n====== IFOOD ======");
            System.out.println("1. Área do Cliente");
            System.out.println("2. Área do Restaurante");
            System.out.println("3. Área do SuperAdmin");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> clienteView.menu();
                case 2 -> restauranteView.menu();
                case 3 -> superAdminView.menu();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);

        sc.close();
    }
}
