package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.CategoriaController;
import br.com.poo.ifood.model.Categoria;
import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    private CategoriaController controller = new CategoriaController();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- CATEGORIAS ---");
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

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        Categoria c = new Categoria(nome, desc);
        controller.cadastrar(c);
        System.out.println("OK");
    }

    private void listar() {
        List<Categoria> lista = controller.listar();
        System.out.println("\nID | Nome - Descrição");
        for (Categoria c : lista) System.out.println(c);
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Nova descrição: ");
        String desc = sc.nextLine();
        Categoria c = new Categoria(nome, desc);
        c.setId(id);
        controller.atualizar(c);
        System.out.println("Atualizado");
    }

    private void deletar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        controller.deletar(id);
        System.out.println("Deletado");
    }
}
