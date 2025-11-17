package br.com.poo.ifood;

import br.com.poo.ifood.view.ClienteView;
import br.com.poo.ifood.view.RestauranteView;
import br.com.poo.ifood.view.SuperAdminView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        final String RESET = "\u001B[0m";
        final String AZUL = "\u001B[34m";
        final String AMARELO = "\u001B[33m";
        final String VERMELHO = "\u001B[31m";

        int op;
        do {
            System.out.println(AZUL + "\n=== iFOOD POO ===" + RESET);
            System.out.println("1. Cliente");
            System.out.println("2. Restaurante");
            System.out.println("3. SuperAdmin");
            System.out.println("0. Sair");
            System.out.print(AMARELO + "Opção: " + RESET);

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> {
                    ClienteView clienteView = new ClienteView(sc);
                    clienteView.menu();
                }
                case 2 -> {
                    RestauranteView restauranteView = new RestauranteView(sc);
                    restauranteView.menu();
                }
                case 3 -> {
                    SuperAdminView superAdminView = new SuperAdminView(sc);
                    superAdminView.menu();
                }
                case 0 -> System.out.println(VERMELHO + "Saindo do sistema..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }

        } while (op != 0);

        sc.close();
    }
}
