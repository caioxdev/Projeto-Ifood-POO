package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.CategoriaController;
import br.com.poo.ifood.model.Categoria;

import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    private Scanner sc;
    private CategoriaController controller = new CategoriaController();

    public CategoriaView(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- CATEGORIAS ---");
            System.out.println("1. Cadastrar categoria");
            System.out.println("2. Listar categorias");
            System.out.println("3. Atualizar categoria");
            System.out.println("4. Remover categoria");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void cadastrar() {
        Categoria c = new Categoria();
        System.out.print("Nome: ");
        c.setNome(sc.nextLine());
        System.out.print("Descrição: ");
        c.setDescricao(sc.nextLine());

        if(controller.cadastrar(c))
            System.out.println("Categoria cadastrada com sucesso!");
        else
            System.out.println("Erro ao cadastrar categoria.");
    }

    private void listar() {
        List<Categoria> categorias = controller.listar();
        if(categorias.isEmpty()) System.out.println("Nenhuma categoria encontrada.");
        else {
            System.out.println("\n--- LISTA DE CATEGORIAS ---");
            for(Categoria c : categorias) {
                System.out.println("ID: " + c.getId_categoria() + " | Nome: " + c.getNome() + " | Descrição: " + c.getDescricao());
            }
        }
    }

    private void atualizar() {
        System.out.print("ID da categoria: ");
        int id = Integer.parseInt(sc.nextLine());
        Categoria c = controller.buscarPorId(id);
        if(c == null) {
            System.out.println("Categoria não encontrada!");
            return;
        }

        System.out.print("Novo nome (" + c.getNome() + "): ");
        String nome = sc.nextLine();
        if(!nome.isEmpty()) c.setNome(nome);

        System.out.print("Nova descrição (" + c.getDescricao() + "): ");
        String desc = sc.nextLine();
        if(!desc.isEmpty())
            c.setDescricao(desc);
        if(controller.atualizar(c))
            System.out.println("Categoria atualizada!");
        else
            System.out.println("Erro ao atualizar categoria.");
    }

    private void remover() {
        System.out.print("ID da categoria: ");
        int id = Integer.parseInt(sc.nextLine());
        if(controller.remover(id))
            System.out.println("Categoria removida!");
        else
            System.out.println("Erro ao remover categoria.");
    }
}
