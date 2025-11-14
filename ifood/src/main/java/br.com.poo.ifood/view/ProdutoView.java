package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.model.Produto;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController controller = new ProdutoController();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- PRODUTOS ---");
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

    public void listarPorRestaurante(Scanner sc) {
        System.out.print("ID do Restaurante: ");
        int idRest = Integer.parseInt(sc.nextLine());
        listar();
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Quantidade: ");
        int q = Integer.parseInt(sc.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());
        Produto p = new Produto(nome, desc, q, preco);
        controller.cadastrar(p);
        System.out.println("OK");
    }

    private void listar() {
        List<Produto> lista = controller.listar();
        System.out.println("\nID | Nome | Preço | Qtd");
        for (Produto p : lista) System.out.println(p);
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Quantidade: ");
        int q = Integer.parseInt(sc.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());
        Produto p = new Produto(nome, desc, q, preco);
        p.setId(id);
        controller.atualizar(p);
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
