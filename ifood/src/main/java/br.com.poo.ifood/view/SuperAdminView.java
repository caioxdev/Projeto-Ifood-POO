package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.CategoriaController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.SuperAdminController;
import br.com.poo.ifood.model.Categoria;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.ItensPedido;
import br.com.poo.ifood.model.SuperAdmin;

import java.util.List;
import java.util.Scanner;

public class SuperAdminView {
    private Scanner sc;
    private SuperAdminController controller = new SuperAdminController();
    private CategoriaController categoriaController = new CategoriaController();
    private RestauranteController restauranteController = new RestauranteController();
    private ProdutoController produtoController = new ProdutoController();
    private PedidoController pedidoController = new PedidoController();

    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROXO = "\u001B[35m";
    private static final String CIANO = "\u001B[36m";

    public SuperAdminView(Scanner sc) { this.sc = sc; }

    // ================= MENU INICIAL =================
    public void menu() {
        int op;
        do {
            System.out.println(AZUL + "\n--- SUPERADMIN ---" + RESET);
            System.out.println("1. Cadastrar SuperAdmin");
            System.out.println("2. Login SuperAdmin");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);

            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1 -> cadastrarSuperAdmin();
                case 2 -> loginSuperAdmin();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while(op != 0);
    }

    private void cadastrarSuperAdmin() {
        SuperAdmin s = new SuperAdmin();
        System.out.print("Nome: "); s.setNome(sc.nextLine());
        System.out.print("Email: "); s.setEmail(sc.nextLine());
        System.out.print("Senha: "); s.setSenha(sc.nextLine());
        System.out.print("Telefone: "); s.setTelefone(sc.nextLine());

        if(controller.cadastrar(s))
            System.out.println(VERDE + "SuperAdmin cadastrado com sucesso!" + RESET);
        else
            System.out.println(VERMELHO + "Erro ao cadastrar." + RESET);
    }

    private void loginSuperAdmin() {
        List<SuperAdmin> lista = controller.listar(); // listar todos os SuperAdmins
        if(lista.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum SuperAdmin cadastrado." + RESET);
            return;
        }

        System.out.println("\n--- SUPERADMINS CADASTRADOS ---");
        for(SuperAdmin s : lista) {
            System.out.println(s.getId_admin() + " - " + s.getNome() + " | Email: " + s.getEmail());
        }

        System.out.print("ID do SuperAdmin: ");
        int id = Integer.parseInt(sc.nextLine());
        SuperAdmin s = controller.buscarPorId(id);

        if(s != null) {
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            if(s.getSenha().equals(senha)) {
                System.out.println(VERDE + "Login realizado com sucesso!" + RESET);
                painelAdmin(s);
            } else {
                System.out.println(VERMELHO + "Senha incorreta." + RESET);
            }
        } else {
            System.out.println(VERMELHO + "ID inválido." + RESET);
        }
    }

    // ================= PAINEL SUPERADMIN =================
    private void painelAdmin(SuperAdmin s) {
        int op;
        do {
            System.out.println(AZUL + "\n--- PAINEL SUPERADMIN ---" + RESET);
            System.out.println("Logado como: " + CIANO + s.getNome() + RESET);
            System.out.println("1. Gerenciar categorias");
            System.out.println("2. Gerenciar restaurantes");
            System.out.println("3. Gerenciar produtos");
            System.out.println("4. Acompanhar pedidos");
            System.out.println("0. Logout");
            System.out.print(AMARELO + "Opção: " + RESET);

            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1 -> menuCategorias();
                case 2 -> menuRestaurantes();
                case 3 -> menuProdutos();
                case 4 -> listarTodosPedidos();
                case 0 -> System.out.println(CIANO + "Logout realizado!" + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while(op != 0);
    }

    // ================= CATEGORIAS =================
    private void menuCategorias() {
        int op;
        do {
            System.out.println(AZUL + "\n--- GERENCIAR CATEGORIAS ---" + RESET);
            System.out.println("1. Cadastrar categoria");
            System.out.println("2. Listar categorias");
            System.out.println("3. Atualizar categoria");
            System.out.println("4. Remover categoria");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);

            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1 -> cadastrarCategoria();
                case 2 -> listarCategorias();
                case 3 -> atualizarCategoria();
                case 4 -> removerCategoria();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while(op != 0);
    }

    private void cadastrarCategoria() {
        Categoria c = new Categoria();
        System.out.print("Nome da categoria: "); c.setNome(sc.nextLine());
        System.out.print("Descrição da categoria: "); c.setDescricao(sc.nextLine());
        if(categoriaController.cadastrar(c)) System.out.println(VERDE + "Categoria cadastrada!" + RESET);
        else System.out.println(VERMELHO + "Erro ao cadastrar categoria." + RESET);
    }

    private void listarCategorias() {
        List<Categoria> lista = categoriaController.listar();
        if(lista.isEmpty()) System.out.println(VERMELHO + "Nenhuma categoria encontrada." + RESET);
        for(Categoria c : lista)
            System.out.println(c.getId_categoria() + " - " + c.getNome() + " | " + c.getDescricao());
    }

    private void atualizarCategoria() {
        listarCategorias();
        System.out.print("ID da categoria para atualizar: "); int id = Integer.parseInt(sc.nextLine());
        Categoria c = categoriaController.buscarPorId(id);
        if(c == null) { System.out.println(VERMELHO + "Categoria não encontrada." + RESET); return; }
        System.out.print("Novo nome: "); c.setNome(sc.nextLine());
        System.out.print("Nova descrição: "); c.setDescricao(sc.nextLine());
        if(categoriaController.atualizar(c)) System.out.println(VERDE + "Categoria atualizada!" + RESET);
        else System.out.println(VERMELHO + "Erro ao atualizar categoria." + RESET);
    }

    private void removerCategoria() {
        listarCategorias();
        System.out.print("ID da categoria para remover: "); int id = Integer.parseInt(sc.nextLine());
        if(categoriaController.remover(id)) System.out.println(VERDE + "Categoria removida!" + RESET);
        else System.out.println(VERMELHO + "Erro ao remover categoria." + RESET);
    }

    // ================= RESTAURANTES =================
    private void menuRestaurantes() {
        int op;
        do {
            System.out.println(AZUL + "\n--- GERENCIAR RESTAURANTES ---" + RESET);
            System.out.println("1. Cadastrar restaurante");
            System.out.println("2. Listar restaurantes");
            System.out.println("3. Atualizar restaurante");
            System.out.println("4. Remover restaurante");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);

            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1 -> cadastrarRestaurante();
                case 2 -> listarRestaurantes();
                case 3 -> atualizarRestaurante();
                case 4 -> removerRestaurante();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while(op != 0);
    }

    private void cadastrarRestaurante() {
        Restaurante r = new Restaurante();
        System.out.print("Nome: "); r.setNome(sc.nextLine());
        System.out.print("Telefone: "); r.setTelefone(sc.nextLine());
        if(restauranteController.cadastrar(r)) System.out.println(VERDE + "Restaurante cadastrado!" + RESET);
        else System.out.println(VERMELHO + "Erro ao cadastrar restaurante." + RESET);
    }

    private void listarRestaurantes() {
        List<Restaurante> lista = restauranteController.listar();
        if(lista.isEmpty()) System.out.println(VERMELHO + "Nenhum restaurante cadastrado." + RESET);
        for(Restaurante r : lista)
            System.out.println(r.getId_restaurante() + " - " + r.getNome());
    }

    private void atualizarRestaurante() {
        listarRestaurantes();
        System.out.print("ID do restaurante para atualizar: "); int id = Integer.parseInt(sc.nextLine());
        Restaurante r = restauranteController.buscarPorId(id);
        if(r == null) { System.out.println(VERMELHO + "Restaurante não encontrado." + RESET); return; }
        System.out.print("Novo nome: "); r.setNome(sc.nextLine());
        System.out.print("Novo telefone: "); r.setTelefone(sc.nextLine());
        if(restauranteController.atualizar(r)) System.out.println(VERDE + "Restaurante atualizado!" + RESET);
        else System.out.println(VERMELHO + "Erro ao atualizar restaurante." + RESET);
    }

    private void removerRestaurante() {
        listarRestaurantes();
        System.out.print("ID do restaurante para remover: "); int id = Integer.parseInt(sc.nextLine());
        if(restauranteController.remover(id)) System.out.println(VERDE + "Restaurante removido!" + RESET);
        else System.out.println(VERMELHO + "Erro ao remover restaurante." + RESET);
    }

    // ================= PRODUTOS =================
    private void menuProdutos() {
        int op;
        do {
            System.out.println(AZUL + "\n--- GERENCIAR PRODUTOS ---" + RESET);
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Atualizar produto");
            System.out.println("4. Remover produto");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);

            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 4 -> removerProduto();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while(op != 0);
    }

    private void cadastrarProduto() {
        Produto p = new Produto();
        System.out.print("Nome: "); p.setNome(sc.nextLine());
        System.out.print("Descrição: "); p.setDescricao(sc.nextLine());
        System.out.print("Preço: "); p.setPreco(Double.parseDouble(sc.nextLine()));
        System.out.print("Quantidade: "); p.setQuantidade(Integer.parseInt(sc.nextLine()));
        listarRestaurantes();
        System.out.print("ID do restaurante: "); p.setRestaurante_id(Integer.parseInt(sc.nextLine()));
        if(produtoController.cadastrar(p)) System.out.println(VERDE + "Produto cadastrado!" + RESET);
        else System.out.println(VERMELHO + "Erro ao cadastrar produto." + RESET);
    }

    private void listarProdutos() {
        List<Produto> lista = produtoController.listar();
        if(lista.isEmpty()) System.out.println(VERMELHO + "Nenhum produto cadastrado." + RESET);
        for(Produto p : lista)
            System.out.println(p.getId_produto() + " - " + p.getNome() + " | " + p.getDescricao() +
                    " | R$ " + p.getPreco() + " | Qt: " + p.getQuantidade() +
                    " | Restaurante ID: " + p.getRestaurante_id());
    }

    private void atualizarProduto() {
        listarProdutos();
        System.out.print("ID do produto para atualizar: "); int id = Integer.parseInt(sc.nextLine());
        Produto p = produtoController.buscarPorId(id);
        if(p == null) { System.out.println(VERMELHO + "Produto não encontrado." + RESET); return; }
        System.out.print("Novo nome: "); p.setNome(sc.nextLine());
        System.out.print("Nova descrição: "); p.setDescricao(sc.nextLine());
        System.out.print("Novo preço: "); p.setPreco(Double.parseDouble(sc.nextLine()));
        System.out.print("Nova quantidade: "); p.setQuantidade(Integer.parseInt(sc.nextLine()));
        listarRestaurantes();
        System.out.print("Novo ID do restaurante: "); p.setRestaurante_id(Integer.parseInt(sc.nextLine()));
        if(produtoController.atualizar(p)) System.out.println(VERDE + "Produto atualizado!" + RESET);
        else System.out.println(VERMELHO + "Erro ao atualizar produto." + RESET);
    }

    private void removerProduto() {
        listarProdutos();
        System.out.print("ID do produto para remover: "); int id = Integer.parseInt(sc.nextLine());
        if(produtoController.remover(id)) System.out.println(VERDE + "Produto removido!" + RESET);
        else System.out.println(VERMELHO + "Erro ao remover produto." + RESET);
    }

    // ================= PEDIDOS =================
    private void listarTodosPedidos() {
        List<Pedido> pedidos = pedidoController.listarTodos();
        if(pedidos.isEmpty()) { System.out.println(VERMELHO + "Nenhum pedido encontrado." + RESET); return; }

        for(Pedido p : pedidos) {
            System.out.println(AZUL + "\nPedido ID: " + p.getId_pedido() +
                    " | Cliente ID: " + p.getCliente_id() +
                    " | Restaurante ID: " + p.getRestaurante_id() +
                    " | Total: R$ " + p.getPreco_total() + RESET);

            System.out.println(ROXO + "--- Itens ---" + RESET);
            for(ItensPedido item : p.getItens()) {
                System.out.println("Produto ID: " + item.getProduto_id() +
                        " | Quantidade: " + item.getQuantidade() +
                        " | Preço unitário: R$ " + item.getPreco_unitario());
            }
        }
    }
}
