package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;
import java.util.Scanner;

public class RestauranteView {
    private RestauranteController controller = new RestauranteController();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- RESTAURANTES ---");
            System.out.println("1 Cadastrar");
            System.out.println("2 Listar");
            System.out.println("3 Atualizar");
            System.out.println("4 Deletar");
            System.out.println("0 Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrar(sc);
                case 2 -> listar();
                case 3 -> atualizar(sc);
                case 4 -> deletar(sc);
            }
        } while (op != 0);
    }

    public void listar() {
        List<Restaurante> lista = controller.findAll();
        System.out.println("\nID | Nome | Categoria");
        for (Restaurante r : lista) System.out.println(r);
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("Endereço: ");
        String end = sc.nextLine();
        System.out.print("Categoria ID: ");
        int cat = Integer.parseInt(sc.nextLine());
        System.out.print("Avaliação (0-5): ");
        double av = Double.parseDouble(sc.nextLine());

        Restaurante r = new Restaurante(nome, tel, end, cat, av);
        controller.create(r);
        System.out.println("OK");
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        controller.atualizarViaTerminal(sc, id);
    }

    private void deletar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        controller.delete(id);
        System.out.println("Deletado");
    }
}
