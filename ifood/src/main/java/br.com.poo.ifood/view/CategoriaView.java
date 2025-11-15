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
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> cadastrar(sc);
                case 2 -> listar();
                case 3 -> atualizar(sc);
                case 4 -> remover(sc);
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        Categoria c = new Categoria();
        c.setNome(nome);
        c.setDescricao(desc);

        if (controller.cadastrar(c)) {
            System.out.println("Categoria cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar categoria.");
        }
    }

    private void listar() {
        List<Categoria> categorias = controller.listar();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID da categoria a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Categoria c = controller.buscarPorId(id);
        if (c == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Novo nome (" + c.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Nova descrição (" + c.getDescricao() + "): ");
        String desc = sc.nextLine();

        c.setNome(nome.isEmpty() ? c.getNome() : nome);
        c.setDescricao(desc.isEmpty() ? c.getDescricao() : desc);

        if (controller.atualizar(c)) {
            System.out.println("Categoria atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar categoria.");
        }
    }

    private void remover(Scanner sc) {
        listar();
        System.out.print("ID da categoria a remover: ");
        int id = Integer.parseInt(sc.nextLine());

        if (controller.remover(id)) {
            System.out.println("Categoria removida com sucesso!");
        } else {
            System.out.println("Erro ao remover categoria.");
        }
    }
}
