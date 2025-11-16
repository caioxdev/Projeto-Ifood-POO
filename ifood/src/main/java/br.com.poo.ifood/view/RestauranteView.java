package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;
import java.util.Scanner;

public class RestauranteView {
    private Scanner sc;
    private RestauranteController controller = new RestauranteController();
    private ProdutoController produtoController = new ProdutoController();

    public RestauranteView(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- RESTAURANTE ---");
            System.out.println("1. Cadastrar restaurante");
            System.out.println("2. Login restaurante");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarRestaurante();
                case 2 -> loginRestaurante();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void cadastrarRestaurante() {
        Restaurante r = new Restaurante();
        System.out.print("Nome: ");
        r.setNome(sc.nextLine());
        System.out.print("Telefone: ");
        r.setTelefone(sc.nextLine());
        System.out.print("Endereço: ");
        r.setEndereco(sc.nextLine());
        System.out.print("ID da categoria: ");
        r.setCategoria_id(Integer.parseInt(sc.nextLine()));

        if (controller.cadastrar(r))
            System.out.println("Restaurante cadastrado com sucesso! ID: " + r.getId_restaurante());
        else
            System.out.println("Erro ao cadastrar restaurante.");
    }

    private void loginRestaurante() {
        System.out.print("ID do restaurante: ");
        int id = Integer.parseInt(sc.nextLine());

        Restaurante r = controller.buscarPorId(id);
        if (r != null) painelRestaurante(r);
        else System.out.println("Restaurante não encontrado!");
    }

    private void painelRestaurante(Restaurante r) {
        int op;
        do {
            System.out.println("\n--- PAINEL RESTAURANTE ---");
            System.out.println("Logado como: " + r.getNome());
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Atualizar restaurante");
            System.out.println("4. Remover restaurante");
            System.out.println("0. Logout");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarProduto(r);
                case 2 -> listarProdutos(r);
                case 3 -> atualizarRestaurante(r);
                case 4 -> removerRestaurante(r);
                case 0 -> System.out.println("Logout realizado!");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void cadastrarProduto(Restaurante r) {
        Produto p = new Produto();
        p.setRestaurante_id(r.getId_restaurante());

        System.out.print("Nome do produto: ");
        p.setNome(sc.nextLine());
        System.out.print("Descrição: ");
        p.setDescricao(sc.nextLine());
        System.out.print("Quantidade: ");
        p.setQuantidade(Integer.parseInt(sc.nextLine()));
        System.out.print("Preço: ");
        p.setPreco(Double.parseDouble(sc.nextLine()));

        if (produtoController.cadastrar(p))
            System.out.println("Produto cadastrado com sucesso!");
        else
            System.out.println("Erro ao cadastrar produto.");
    }

    private void listarProdutos(Restaurante r) {
        List<Produto> produtos = produtoController.listarPorRestaurante(r.getId_restaurante());
        if (produtos.isEmpty()) System.out.println("Nenhum produto cadastrado.");
        else {
            System.out.println("\n--- PRODUTOS ---");
            for (Produto p : produtos) {
                System.out.println(p.getId_produto() + " - " + p.getNome() + " | Qtd: " + p.getQuantidade() + " | R$ " + p.getPreco());
            }
        }
    }

    private void atualizarRestaurante(Restaurante r) {
        System.out.print("Novo nome (" + r.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) r.setNome(nome);

        System.out.print("Novo telefone (" + r.getTelefone() + "): ");
        String telefone = sc.nextLine();
        if (!telefone.isEmpty()) r.setTelefone(telefone);

        System.out.print("Novo endereço (" + r.getEndereco() + "): ");
        String endereco = sc.nextLine();
        if (!endereco.isEmpty()) r.setEndereco(endereco);

        System.out.print("Nova categoria (" + r.getCategoria_id() + "): ");
        String cat = sc.nextLine();
        if (!cat.isEmpty()) r.setCategoria_id(Integer.parseInt(cat));

        if (controller.atualizar(r))
            System.out.println("Restaurante atualizado com sucesso!");
        else
            System.out.println("Erro ao atualizar restaurante.");
    }

    private void removerRestaurante(Restaurante r) {
        System.out.print("Confirma remoção do restaurante " + r.getNome() + "? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            if (controller.remover(r.getId_restaurante()))
                System.out.println("Restaurante removido com sucesso!");
            else
                System.out.println("Erro ao remover restaurante.");
        }
    }
}
