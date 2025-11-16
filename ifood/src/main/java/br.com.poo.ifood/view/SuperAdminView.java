package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.Scanner;

public class SuperAdminView {

    private Scanner sc;
    private SuperAdminController controller;

    public SuperAdminView(Scanner sc) {
        this.sc = sc;
        this.controller = new SuperAdminController();
    }

    public void loginMenu() {
        int op;

        do {
            System.out.println("\n====== SUPERADMIN ======");
            System.out.println("1. Login");
            System.out.println("2. Criar conta");
            System.out.println("3. Excluir conta");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> login();
                case 2 -> criarConta();
                case 3 -> deletarConta();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);
    }

    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        SuperAdmin admin = controller.login(email, senha);

        if (admin != null) {
            System.out.println("Login realizado com sucesso!");
            painelAdmin(admin);
        } else {
            System.out.println("Email ou senha incorretos!");
        }
    }

    private void criarConta() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        SuperAdmin admin = new SuperAdmin(nome, email, senha, telefone);

        boolean criado = controller.criar(admin);

        if (criado)
            System.out.println("Conta criada com sucesso!");
        else
            System.out.println("Erro: email já existe.");
    }

    private void deletarConta() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        boolean deletado = controller.deletar(email, senha);

        if (deletado)
            System.out.println("Conta deletada!");
        else
            System.out.println("Email/senha inválidos.");
    }

    private void painelAdmin(SuperAdmin admin) {
        int op;

        // Instanciando as views conectadas ao banco
        RestauranteView restauranteView = new RestauranteView();
        ProdutoView produtoView = new ProdutoView();
        PedidoView pedidoView = new PedidoView(); // admin vê todos os pedidos

        do {
            System.out.println("\n====== PAINEL SUPERADMIN ======");
            System.out.println("Logado como: " + admin.getNome());
            System.out.println("1. Gerenciar Restaurantes");
            System.out.println("2. Gerenciar Produtos");
            System.out.println("3. Gerenciar Pedidos");
            System.out.println("0. Logout");
            System.out.print("Opção: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> restauranteView.menu(sc); // chama menu de restaurantes
                case 2 -> produtoView.menu(sc);     // chama menu de produtos
                case 3 -> pedidoView.menu(sc);      // chama menu de pedidos (todos)
                case 0 -> System.out.println("Logout realizado!");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);
    }
}
