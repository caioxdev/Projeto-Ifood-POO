package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ClienteController;
import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.model.Cliente;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.ItensPedido;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private Scanner sc;
    private ClienteController controller = new ClienteController();
    private PedidoController pedidoController = new PedidoController();
    private ProdutoController produtoController = new ProdutoController();
    private RestauranteController restauranteController = new RestauranteController();

    public ClienteView(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- CLIENTE ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Login");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrar();
                case 2 -> login();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
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

        if (controller.cadastrar(c)) System.out.println("Cliente cadastrado!");
        else System.out.println("Erro ao cadastrar cliente.");
    }

    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Cliente c = controller.buscarPorEmailESenha(email, senha);
        if (c != null) {
            System.out.println("SUCESSO: Conectado com sucesso ao banco!");
            painelCliente(c);
        } else {
            System.out.println("Erro: cliente não encontrado ou senha incorreta.");
        }
    }

    private void painelCliente(Cliente c) {
        int op;
        do {
            System.out.println("\n--- PAINEL CLIENTE ---");
            System.out.println("Logado como: " + c.getNome());
            System.out.println("1. Fazer pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("0. Logout");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> fazerPedido(c);
                case 2 -> listarPedidos(c);
                case 0 -> System.out.println("Logout realizado!");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void fazerPedido(Cliente cliente) {
        List<Restaurante> restaurantes = restauranteController.listar();
        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante disponível.");
            return;
        }

        System.out.println("\n--- RESTAURANTES ---");
        for (Restaurante r : restaurantes) {
            System.out.println(r.getId_restaurante() + " - " + r.getNome());
        }

        System.out.print("Escolha o ID do restaurante: ");
        int idRest = Integer.parseInt(sc.nextLine());
        Restaurante r = restauranteController.buscarPorId(idRest);
        if (r == null) {
            System.out.println("Restaurante inválido.");
            return;
        }

        List<Produto> produtos = produtoController.listarPorRestaurante(r.getId_restaurante());
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto nesse restaurante.");
            return;
        }

        pedidoController.fazerPedido(cliente, produtos, sc);
    }

    private void listarPedidos(Cliente c) {
        List<Pedido> pedidos = pedidoController.listarPedidosPorCliente(c.getId_cliente());

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        for (Pedido p : pedidos) {
            Restaurante r = restauranteController.buscarPorId(p.getRestaurante_id());
            System.out.println("\nPedido ID: " + p.getId_pedido() +
                    " | Restaurante: " + (r != null ? r.getNome() : "Desconhecido") +
                    " | Total: R$ " + p.getPreco_total());

            System.out.println("--- Itens ---");
            for (ItensPedido item : p.getItens()) {
                System.out.println("Produto ID: " + item.getProduto_id() +
                        " | Quantidade: " + item.getQuantidade() +
                        " | Preço unitário: R$ " + item.getPreco_unitario());
            }
        }
    }
}
