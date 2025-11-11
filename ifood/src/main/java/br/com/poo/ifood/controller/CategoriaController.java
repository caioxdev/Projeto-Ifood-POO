package br.com.poo.ifood.controller;

import java.util.Scanner;

public class CategoriaController {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Categoria ===");
            System.out.println("1 - Cadastrar Categoria");
            System.out.println("2 - Listar Categorias");
            System.out.println("3 - Excluir Categoria");
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
        System.out.println("Cadastrando nova categoria...");
    }

    public void listar() {
        System.out.println("Listando categorias cadastradas...");
    }

    public void excluir() {
        System.out.println("Excluindo categoria...");
    }
}
