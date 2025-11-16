package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.Scanner;

public class SuperAdminView {
    private Scanner sc;
    private SuperAdminController controller = new SuperAdminController();

    // ================== Cores ANSI ==================
    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROXO = "\u001B[35m";
    private static final String CIANO = "\u001B[36m";

    public SuperAdminView(Scanner sc) { this.sc = sc; }

    public void menu() {
        int op;
        do {
            System.out.println(AZUL + "\n--- SUPERADMIN ---" + RESET);
            System.out.println("1. Cadastrar SuperAdmin");
            System.out.println("2. Login SuperAdmin");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch(op) {
                case 1 -> cadastrar();
                case 2 -> login();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
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

        if(controller.cadastrar(s))
            System.out.println(VERDE + "SuperAdmin cadastrado com sucesso!" + RESET);
        else
            System.out.println(VERMELHO + "Erro ao cadastrar." + RESET);
    }

    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        SuperAdmin s = controller.buscarPorEmailESenha(email, senha);
        if(s != null) {
            System.out.println(VERDE + "SUCESSO: Conectado com sucesso ao banco!" + RESET);
            painelAdmin(s);
        } else {
            System.out.println(VERMELHO + "Email ou senha incorretos." + RESET);

            // ================= LISTAGEM DE TESTE =================
            System.out.println("\n--- SUPERADMINS CADASTRADOS PARA TESTE ---");
            for (SuperAdmin sa : controller.listar()) {
                System.out.println(sa.getId_admin() + " - " + sa.getNome() + " | Email: " + sa.getEmail());
            }
        }
    }

    private void painelAdmin(SuperAdmin s) {
        int op;
        do {
            System.out.println(AZUL + "\n--- PAINEL SUPERADMIN ---" + RESET);
            System.out.println("Logado como: " + CIANO + s.getNome() + RESET);
            System.out.println("1. Gerenciar categorias");
            System.out.println("2. Gerenciar restaurantes");
            System.out.println("3. Gerenciar produtos");
            System.out.println("4. Acompanhar pedidos");
            System.out.println("0. Logout");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch(op) {
                case 1 -> System.out.println("Funcionalidade de categorias");
                case 2 -> System.out.println("Funcionalidade de restaurantes");
                case 3 -> System.out.println("Funcionalidade de produtos");
                case 4 -> System.out.println("Funcionalidade de pedidos");
                case 0 -> System.out.println(CIANO + "Logout realizado!" + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while(op != 0);
    }
}
