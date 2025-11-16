package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.Scanner;

public class SuperAdminView {
    private Scanner sc;
    private SuperAdminController controller = new SuperAdminController();

    public SuperAdminView(Scanner sc) { this.sc = sc; }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- SUPERADMIN ---");
            System.out.println("1. Cadastrar SuperAdmin");
            System.out.println("2. Login SuperAdmin");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch(op) {
                case 1 -> cadastrar();
                case 2 -> login();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while(op != 0);
    }

    private void cadastrar() {
        SuperAdmin s = new SuperAdmin();
        System.out.print("Nome: ");
        s.setNome(sc.nextLine());
        System.out.print("Email: ");
        s.setEmail(sc.nextLine());
        System.out.print("Senha: ");
        s.setSenha(sc.nextLine());
        System.out.print("Telefone: ");
        s.setTelefone(sc.nextLine());

        if(controller.cadastrar(s)) System.out.println("SuperAdmin cadastrado!");
        else System.out.println("Erro ao cadastrar.");
    }

    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        SuperAdmin s = controller.buscarPorEmailESenha(email, senha);
        if(s != null) painelAdmin(s);
        else System.out.println("Email ou senha incorretos.");
    }

    private void painelAdmin(SuperAdmin s) {
        int op;
        do {
            System.out.println("\n--- PAINEL SUPERADMIN ---");
            System.out.println("1. Gerenciar categorias");
            System.out.println("2. Gerenciar restaurantes");
            System.out.println("3. Gerenciar produtos");
            System.out.println("4. Acompanhar pedidos");
            System.out.println("0. Logout");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch(op) {
                case 1 -> System.out.println("Funcionalidade de categorias");
                case 2 -> System.out.println("Funcionalidade de restaurantes");
                case 3 -> System.out.println("Funcionalidade de produtos");
                case 4 -> System.out.println("Funcionalidade de pedidos");
                case 0 -> System.out.println("Logout realizado!");
                default -> System.out.println("Opção inválida!");
            }
        } while(op != 0);
    }
}
