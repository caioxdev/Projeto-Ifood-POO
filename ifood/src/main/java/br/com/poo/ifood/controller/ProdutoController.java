package br.com.poo.ifood.controller;

import java.util.Scanner;

public class ProdutoController {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Produto ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Excluir Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> excluir();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void cadastrar() {
        System.out.println("Cadastrando novo produto...");
    }

    public void listar() {
        System.out.println("Listando produtos cadastrados...");
    }

    public void excluir() {
        System.out.println("Excluindo produto...");
    }
}
