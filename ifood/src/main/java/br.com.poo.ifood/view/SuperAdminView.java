package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.Scanner;

public class SuperAdminView {
    private SuperAdminController controller = new SuperAdminController();
    private CategoriaView categoriaView = new CategoriaView();
    private ProdutoView produtoView = new ProdutoView();
    private ClienteView clienteView = new ClienteView();
    private RestauranteView restauranteView = new RestauranteView();
    private PedidoView pedidoView = new PedidoView();

    public void loginMenu(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        SuperAdmin admin = controller.autenticar(email, senha);

        if (admin == null) {
            System.out.println("Credenciais inválidas");
            return;
        }

        adminMenu(sc, admin);
    }

    private void adminMenu(Scanner sc, SuperAdmin admin) {
        int op;
        do {
            System.out.println("\n--- SUPERADMIN (" + admin.getNome() + ") ---");
            System.out.println("1. Categorias");
            System.out.println("2. Produtos");
            System.out.println("3. Clientes");
            System.out.println("4. Restaurantes");
            System.out.println("5. Pedidos");
            System.out.println("0. Logout");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> categoriaView.menu(sc);
                case 2 -> produtoView.menu(sc);
                case 3 -> clienteView.menu(sc);
                case 4 -> restauranteView.menu(sc);
                case 5 -> pedidoView.menu(sc);
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }
}
