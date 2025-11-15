package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.CategoriaController;
import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.Categoria;
import br.com.poo.ifood.model.ItemPedido;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.List;
import java.util.Scanner;

public class SuperAdminView {

    private final Scanner sc;
    private final SuperAdminController superAdminController = new SuperAdminController();
    private final RestauranteController restauranteController = new RestauranteController();
    private final ProdutoController produtoController = new ProdutoController();
    private final CategoriaController categoriaController = new CategoriaController();
    private final PedidoController pedidoController = new PedidoController();

    public SuperAdminView(Scanner sc) {
        this.sc = sc;
    }

    public void mostrarMenu() {
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
            System.out.println("8. Menu Restaurante");
            System.out.println("9. Listar Todos os Pedidos");
            System.out.println("0. Sair");
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
                case 8 -> menuRestaurante();
                case 9 -> listarTodosPedidos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    // --------------------- MÉTODOS DE CADASTRO ---------------------
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

    // --------------------- MÉTODOS DE LISTAGEM ---------------------
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

    // --------------------- MENU RESTAURANTE ---------------------
    private void menuRestaurante() {
        System.out.print("Digite o ID do Restaurante: ");
        int restauranteId = Integer.parseInt(sc.nextLine());

        int op;
        do {
            System.out.println("\n--- RESTAURANTE ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarProdutoRestaurante(restauranteId);
                case 2 -> listarProdutosRestaurante(restauranteId);
                case 3 -> atualizarProdutoRestaurante(restauranteId);
                case 4 -> removerProdutoRestaurante(restauranteId);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);
    }

    private void cadastrarProdutoRestaurante(int restauranteId) {
        System.out.print("Nome do Produto: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());

        Produto p = new Produto(restauranteId, nome, descricao, qtd, preco);
        if (produtoController.cadastrar(p)) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto.");
        }
    }

    private void listarProdutosRestaurante(int restauranteId) {
        List<Produto> produtos = produtoController.listarPorRestaurante(restauranteId);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado neste restaurante.");
        } else {
            System.out.println("\n--- PRODUTOS ---");
            for (Produto p : produtos) {
                System.out.println(
                        "ID: " + p.getId_produto() +
                                " | Nome: " + p.getNome() +
                                " | Quantidade: " + p.getQuantidade() +
                                " | Preço: " + p.getPreco() +
                                " | Descrição: " + p.getDescricao()
                );
            }
        }
    }

    private void atualizarProdutoRestaurante(int restauranteId) {
        listarProdutosRestaurante(restauranteId);
        System.out.print("Digite o ID do Produto a atualizar: ");
        int idProduto = Integer.parseInt(sc.nextLine());

        Produto p = produtoController.buscarPorId(idProduto);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo Nome (" + p.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isBlank()) p.setNome(nome);

        System.out.print("Nova Descrição (" + p.getDescricao() + "): ");
        String descricao = sc.nextLine();
        if (!descricao.isBlank()) p.setDescricao(descricao);

        System.out.print("Nova Quantidade (" + p.getQuantidade() + "): ");
        String qtdStr = sc.nextLine();
        if (!qtdStr.isBlank()) p.setQuantidade(Integer.parseInt(qtdStr));

        System.out.print("Novo Preço (" + p.getPreco() + "): ");
        String precoStr = sc.nextLine();
        if (!precoStr.isBlank()) p.setPreco(Double.parseDouble(precoStr));

        if (produtoController.atualizar(p)) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar produto.");
        }
    }

    private void removerProdutoRestaurante(int restauranteId) {
        listarProdutosRestaurante(restauranteId);
        System.out.print("Digite o ID do Produto a remover: ");
        int idProduto = Integer.parseInt(sc.nextLine());

        Produto p = produtoController.buscarPorId(idProduto);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // Checa se o produto está em algum pedido
        if (produtoController.produtoEmPedido(idProduto)) {
            System.out.println("Não é possível remover este produto, pois ele já foi pedido.");
            return;
        }

        if (produtoController.remover(idProduto)) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Erro ao remover produto.");
        }
    }

    // --------------------- LISTAR PEDIDOS ---------------------
    private void listarTodosPedidos() {
        List<Pedido> pedidos = pedidoController.listarTodos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        System.out.println("\n--- PEDIDOS ---");
        for (Pedido p : pedidos) {
            System.out.println("ID Pedido: " + p.getId_pedido() +
                    " | Cliente ID: " + p.getCliente_id() +
                    " | Restaurante ID: " + p.getRestaurante_id() +
                    " | Preço Total: " + p.getPreco_total());

            System.out.println("Itens:");
            for (ItemPedido item : p.getItens()) {
                System.out.println("   Produto ID: " + item.getProduto_id() +
                        " | Quantidade: " + item.getQuantidade() +
                        " | Preço Unitário: " + item.getPreco_unitario());
            }
        }
    }
}
