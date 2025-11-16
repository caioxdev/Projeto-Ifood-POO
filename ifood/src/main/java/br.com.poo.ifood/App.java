package br.com.poo.ifood;

import br.com.poo.ifood.view.SuperAdminView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

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
                case 1 -> System.out.println("Área do Cliente (em construção)...");
                case 2 -> System.out.println("Área do Restaurante (em construção)...");
                case 3 -> {
                    // Chama a view do SuperAdmin
                    SuperAdminView superAdminView = new SuperAdminView(sc);
                    superAdminView.loginMenu(); // login e menu completo
                }
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);

        sc.close();
    }
}
