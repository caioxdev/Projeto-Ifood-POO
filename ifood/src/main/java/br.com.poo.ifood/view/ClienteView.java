package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ClienteController;
import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.controller.FavoritoController;
import br.com.poo.ifood.model.Cliente;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.ItensPedido;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;

public class ClienteView {
    private java.util.Scanner sc;
    private ClienteController controller = new ClienteController();
    private PedidoController pedidoController = new PedidoController();
    private ProdutoController produtoController = new ProdutoController();
    private RestauranteController restauranteController = new RestauranteController();
    private FavoritoController favoritoController = new FavoritoController();

    // ================== Cores ANSI ==================
    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROXO = "\u001B[35m";
    private static final String CIANO = "\u001B[36m";

    public ClienteView(java.util.Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        int op;
        do {
            System.out.println(AZUL + "\n--- CLIENTE ---" + RESET);
            System.out.println("1. Cadastrar");
            System.out.println("2. Login");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrar();
                case 2 -> login();
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while (op != 0);
    }

    private void cadastrar() {
        Cliente c = new Cliente();
        System.out.print("Nome: ");
        c.setNome(sc.nextLine());
        System.out.print("Email: ");
        c.setEmail(sc.nextLine());
        System.out.print("Senha: ");
        c.setSenha(sc.nextLine());

        if (controller.cadastrar(c))
            System.out.println(VERDE + "Cliente cadastrado com sucesso!" + RESET);
        else
            System.out.println(VERMELHO + "Erro ao cadastrar cliente." + RESET);
    }

    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Cliente c = controller.buscarPorEmailESenha(email, senha);
        if (c != null) {
            System.out.println("\u001B[32mSUCESSO: Conectado com sucesso ao banco!\u001B[0m");
            painelCliente(c);
        } else {
            System.out.println("\u001B[31mErro: cliente não encontrado ou senha incorreta.\u001B[0m");
            System.out.println("\n--- CLIENTES CADASTRADOS PARA TESTE ---");
            for (Cliente cli : controller.listar()) {
                System.out.println(cli.getId_cliente() + " - " + cli.getNome() + " | Email: " + cli.getEmail());
            }
        }
    }


    private void painelCliente(Cliente c) {
        int op;
        do {
            System.out.println(AZUL + "\n--- PAINEL CLIENTE ---" + RESET);
            System.out.println("Logado como: " + CIANO + c.getNome() + RESET);
            System.out.println("1. Fazer pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("3. Favoritos");
            System.out.println("0. Logout");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> fazerPedido(c);
                case 2 -> listarPedidos(c);
                case 3 -> menuFavoritos(c);
                case 0 -> System.out.println(CIANO + "Logout realizado!" + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while (op != 0);
    }

    private void fazerPedido(Cliente cliente) {
        List<Restaurante> restaurantes = restauranteController.listar();
        if (restaurantes.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum restaurante disponível." + RESET);
            return;
        }

        System.out.println(AZUL + "\n--- RESTAURANTES ---" + RESET);
        for (Restaurante r : restaurantes) {
            System.out.println(r.getId_restaurante() + " - " + r.getNome());
        }

        System.out.print(AMARELO + "Escolha o ID do restaurante: " + RESET);
        int idRest = Integer.parseInt(sc.nextLine());
        Restaurante r = restauranteController.buscarPorId(idRest);
        if (r == null) {
            System.out.println(VERMELHO + "Restaurante inválido." + RESET);
            return;
        }

        List<Produto> produtos = produtoController.listarPorRestaurante(r.getId_restaurante());
        if (produtos.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum produto nesse restaurante." + RESET);
            return;
        }

        pedidoController.fazerPedido(cliente, idRest, produtos, sc);
    }

    private void listarPedidos(Cliente c) {
        List<Pedido> pedidos = pedidoController.listarPedidosPorCliente(c.getId_cliente());

        if (pedidos.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum pedido encontrado." + RESET);
            return;
        }

        for (Pedido p : pedidos) {
            Restaurante r = restauranteController.buscarPorId(p.getRestaurante_id());
            System.out.println(AZUL + "\nPedido ID: " + p.getId_pedido() +
                    " | Restaurante: " + (r != null ? r.getNome() : "Desconhecido") +
                    " | Total: R$ " + p.getPreco_total() + RESET);

            System.out.println(ROXO + "--- Itens ---" + RESET);
            for (ItensPedido item : p.getItens()) {
                System.out.println("Produto ID: " + item.getProduto_id() +
                        " | Quantidade: " + item.getQuantidade() +
                        " | Preço unitário: R$ " + item.getPreco_unitario());
            }
        }
    }

    private void menuFavoritos(Cliente c) {
        int op;
        do {
            System.out.println(AZUL + "\n--- FAVORITOS ---" + RESET);
            System.out.println("1. Listar favoritos");
            System.out.println("2. Adicionar favorito");
            System.out.println("3. Remover favorito");
            System.out.println("0. Voltar");
            System.out.print(AMARELO + "Opção: " + RESET);
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> listarFavoritos(c);
                case 2 -> adicionarFavorito(c);
                case 3 -> removerFavorito(c);
                case 0 -> System.out.println(CIANO + "Voltando..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        } while (op != 0);
    }

    private void listarFavoritos(Cliente c) {
        List<Restaurante> favoritos = favoritoController.listarFavoritos(c.getId_cliente());
        if (favoritos.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum restaurante favoritado." + RESET);
            return;
        }

        System.out.println(AZUL + "\n--- RESTAURANTES FAVORITOS ---" + RESET);
        for (Restaurante r : favoritos) {
            System.out.println(r.getId_restaurante() + " - " + r.getNome());
        }
    }

    private void adicionarFavorito(Cliente c) {
        List<Restaurante> restaurantes = restauranteController.listar();
        if (restaurantes.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum restaurante disponível." + RESET);
            return;
        }

        System.out.println(AZUL + "\n--- RESTAURANTES ---" + RESET);
        for (Restaurante r : restaurantes) {
            System.out.println(r.getId_restaurante() + " - " + r.getNome());
        }

        System.out.print(AMARELO + "Escolha o ID do restaurante para favoritar: " + RESET);
        int idRest = Integer.parseInt(sc.nextLine());

        if (favoritoController.adicionarFavorito(c.getId_cliente(), idRest)) {
            System.out.println(VERDE + "Restaurante adicionado aos favoritos!" + RESET);
        } else {
            System.out.println(VERMELHO + "Erro ao adicionar favorito." + RESET);
        }
    }

    private void removerFavorito(Cliente c) {
        List<Restaurante> favoritos = favoritoController.listarFavoritos(c.getId_cliente());
        if (favoritos.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum restaurante favoritado." + RESET);
            return;
        }

        System.out.println(AZUL + "\n--- RESTAURANTES FAVORITOS ---" + RESET);
        for (Restaurante r : favoritos) {
            System.out.println(r.getId_restaurante() + " - " + r.getNome());
        }

        System.out.print(AMARELO + "Escolha o ID do restaurante para remover dos favoritos: " + RESET);
        int idRest = Integer.parseInt(sc.nextLine());

        if (favoritoController.removerFavorito(c.getId_cliente(), idRest)) {
            System.out.println(VERDE + "Restaurante removido dos favoritos!" + RESET);
        } else {
            System.out.println(VERMELHO + "Erro ao remover favorito." + RESET);
        }
    }
}
