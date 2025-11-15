package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.CategoriaController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.Categoria;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.List;
import java.util.Scanner;

public class SuperAdminView {

    private Scanner sc;
    private SuperAdminController superAdminController = new SuperAdminController();
    private RestauranteController restauranteController = new RestauranteController();
    private ProdutoController produtoController = new ProdutoController();
    private CategoriaController categoriaController = new CategoriaController();

    public SuperAdminView(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- SUPER ADMIN ---");
            System.out.println("1. Cadastrar SuperAdmin");
            System.out.println("2. Cadastrar Restaurante");
            System.out.println("3. Cadastrar Produto");
            System.out.println("4. Cadastrar Categoria");
            System.out.println("5. Listar Restaurantes");
            System.out.println("6. Listar Produtos");
            System.out.println("7. Listar Categorias");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> cadastrarSuperAdmin();
                case 2 -> cadastrarRestaurante();
                case 3 -> cadastrarProduto();
                case 4 -> cadastrarCategoria();
                case 5 -> listarRestaurantes();
                case 6 -> listarProdutos();
                case 7 -> listarCategorias();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void cadastrarSuperAdmin() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        SuperAdmin sa = new SuperAdmin(nome, email, senha, telefone);

        if (superAdminController.cadastrar(sa)) {
            System.out.println("SuperAdmin cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar SuperAdmin.");
        }
    }

    private void cadastrarRestaurante() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("ID Categoria: ");
        int categoriaId = Integer.parseInt(sc.nextLine());
        System.out.print("Avaliação (0-5): ");
        double avaliacao = Double.parseDouble(sc.nextLine());

        Restaurante r = new Restaurante(nome, telefone, endereco, categoriaId, avaliacao);

        if (restauranteController.cadastrar(r)) {
            System.out.println("Restaurante cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar restaurante.");
        }
    }

    private void cadastrarProduto() {
        listarRestaurantes();
        System.out.print("ID do Restaurante: ");
        int idRest = Integer.parseInt(sc.nextLine());

        System.out.print("Nome do Produto: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());

        Produto p = new Produto(idRest, nome, descricao, qtd, preco);

        if (produtoController.cadastrar(p)) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto.");
        }
    }

    private void cadastrarCategoria() {
        System.out.print("Nome da Categoria: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        Categoria c = new Categoria();
        c.setNome(nome);
        c.setDescricao(descricao);

        if (categoriaController.cadastrar(c)) {
            System.out.println("Categoria cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar categoria.");
        }
    }

    private void listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteController.listar();

        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
        } else {
            System.out.println("\n--- RESTAURANTES ---");
            for (Restaurante r : restaurantes) {
                System.out.println(
                        "ID: " + r.getId() +
                                " | Nome: " + r.getNome() +
                                " | Telefone: " + r.getTelefone() +
                                " | Endereço: " + r.getEndereco() +
                                " | Categoria: " + r.getCategoria_id() +
                                " | Avaliação: " + r.getAvaliacao() +
                                " | Ativo: " + r.isAtivo()
                );
            }
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoController.listar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\n--- PRODUTOS ---");

            for (Produto p : produtos) {
                Restaurante r = restauranteController.buscarPorId(p.getRestaurante_id());
                String nomeRestaurante = (r != null) ? r.getNome() : "Restaurante não encontrado";

                System.out.println(
                        "ID: " + p.getId_produto() +
                                " | Nome: " + p.getNome() +
                                " | Restaurante: " + nomeRestaurante +
                                " | Quantidade: " + p.getQuantidade() +
                                " | Preço: " + p.getPreco() +
                                " | Descrição: " + p.getDescricao()
                );
            }
        }
    }

    private void listarCategorias() {
        List<Categoria> categorias = categoriaController.listar();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            System.out.println("\n--- CATEGORIAS ---");

            for (Categoria c : categorias) {
                System.out.println(
                        "ID: " + c.getId() +
                                " | Nome: " + c.getNome() +
                                " | Descrição: " + c.getDescricao()
                );
            }
        }
    }
}
