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

    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROXO = "\u001B[35m";
    private static final String CIANO = "\u001B[36m";

    public RestauranteView(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        int op;
        do {
            System.out.println(AZUL + "\n--- RESTAURANTE ---" + RESET);
            System.out.println("1. Cadastrar restaurante");
            System.out.println("2. Login restaurante");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarRestaurante();
                case 2 -> loginRestaurante();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
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
            System.out.println(VERDE + "Restaurante cadastrado com sucesso! ID: " + r.getId_restaurante() + RESET);
        else
            System.out.println(VERMELHO + "Erro ao cadastrar restaurante." + RESET);
    }

    private void loginRestaurante() {
        List<Restaurante> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum restaurante cadastrado." + RESET);
            return;
        }

        System.out.println(AZUL + "\n--- RESTAURANTES DISPONÍVEIS ---" + RESET);
        for (Restaurante r : lista) {
            System.out.println(r.getId_restaurante() + " - " + r.getNome() + " | Telefone: " + r.getTelefone());
        }

        System.out.print("Digite o ID do restaurante para login: ");
        int id = Integer.parseInt(sc.nextLine());

        Restaurante r = controller.buscarPorId(id);
        if (r != null) {
            System.out.println(VERDE + "Login realizado com sucesso! Restaurante: " + r.getNome() + RESET);
            painelRestaurante(r);
        } else {
            System.out.println(VERMELHO + "ID inválido." + RESET);
        }
    }

    private void painelRestaurante(Restaurante r) {
        int op;
        do {
            System.out.println(AZUL + "\n--- PAINEL RESTAURANTE ---" + RESET);
            System.out.println("Logado como: " + CIANO + r.getNome() + RESET);
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Atualizar restaurante");
            System.out.println("4. Remover restaurante");
            System.out.println("0. Logout");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarProduto(r);
                case 2 -> listarProdutos(r);
                case 3 -> atualizarRestaurante(r);
                case 4 -> removerRestaurante(r);
                case 0 -> System.out.println(CIANO + "Logout realizado!" + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
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
            System.out.println(VERDE + "Produto cadastrado com sucesso!" + RESET);
        else
            System.out.println(VERMELHO + "Erro ao cadastrar produto." + RESET);
    }

    private void listarProdutos(Restaurante r) {
        List<Produto> produtos = produtoController.listarPorRestaurante(r.getId_restaurante());
        if (produtos.isEmpty()) System.out.println(VERMELHO + "Nenhum produto cadastrado." + RESET);
        else {
            System.out.println(AZUL + "\n--- PRODUTOS ---" + RESET);
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
            System.out.println(VERDE + "Restaurante atualizado com sucesso!" + RESET);
        else
            System.out.println(VERMELHO + "Erro ao atualizar restaurante." + RESET);
    }

    private void removerRestaurante(Restaurante r) {
        System.out.print("Confirma remoção do restaurante " + r.getNome() + "? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            if (controller.remover(r.getId_restaurante()))
                System.out.println(VERDE + "Restaurante removido com sucesso!" + RESET);
            else
                System.out.println(VERMELHO + "Erro ao remover restaurante." + RESET);
        }
    }
}
