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
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar por Restaurante");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> cadastrar(sc);
                case 2 -> listarPorRestaurante(sc);
                case 3 -> atualizar(sc);
                case 4 -> remover(sc);
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void cadastrar(Scanner sc) {
        System.out.print("ID Restaurante: ");
        int idRest = Integer.parseInt(sc.nextLine());
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Quantidade: ");
        int q = Integer.parseInt(sc.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());

        Produto p = new Produto(idRest, nome, desc, q, preco);
        if (controller.cadastrar(p)) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto.");
        }
    }

    private void listarPorRestaurante(Scanner sc) {
        System.out.print("ID Restaurante: ");
        int idRest = Integer.parseInt(sc.nextLine());
        List<Produto> produtos = controller.listarPorRestaurante(idRest);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void atualizar(Scanner sc) {
        listarPorRestaurante(sc);
        System.out.print("ID do produto a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Produto p = controller.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + p.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Nova descrição (" + p.getDescricao() + "): ");
        String desc = sc.nextLine();
        System.out.print("Nova quantidade (" + p.getQuantidade() + "): ");
        String qStr = sc.nextLine();
        System.out.print("Novo preço (" + p.getPreco() + "): ");
        String precoStr = sc.nextLine();

        p.setNome(nome.isEmpty() ? p.getNome() : nome);
        p.setDescricao(desc.isEmpty() ? p.getDescricao() : desc);
        p.setQuantidade(qStr.isEmpty() ? p.getQuantidade() : Integer.parseInt(qStr));
        p.setPreco(precoStr.isEmpty() ? p.getPreco() : Double.parseDouble(precoStr));

        if (controller.atualizar(p)) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar produto.");
        }
    }

    private void remover(Scanner sc) {
        listarPorRestaurante(sc);
        System.out.print("ID do produto a remover: ");
        int id = Integer.parseInt(sc.nextLine());

        if (controller.remover(id)) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Erro ao remover produto.");
        }
    }
}
