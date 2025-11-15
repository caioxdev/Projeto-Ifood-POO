package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.controller.ClienteController;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.model.ItemPedido;
import br.com.poo.ifood.model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClientePainelView {

    private RestauranteController restauranteController = new RestauranteController();
    private ProdutoController produtoController = new ProdutoController();
    private PedidoController pedidoController = new PedidoController();
    private ClienteController clienteController = new ClienteController();

    private int idCliente;

    public ClientePainelView(int idCliente) {
        this.idCliente = idCliente;
    }

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- CLIENTE ---");
            System.out.println("1. Ver Restaurantes");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Acompanhar Pedidos");
            System.out.println("0. Sair");

            op = lerInteiro(sc, "Opção: ");

            switch (op) {
                case 1 -> listarRestaurantes();
                case 2 -> fazerPedido(sc);
                case 3 -> acompanharPedidos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private int lerInteiro(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private void listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteController.listar();
        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
            return;
        }
        System.out.println("\n--- RESTAURANTES ---");
        restaurantes.forEach(r -> System.out.println(r.getId() + " - " + r.getNome()));
    }

    private void fazerPedido(Scanner sc) {
        listarRestaurantes();
        int idRestaurante = lerInteiro(sc, "Digite o ID do restaurante: ");
        Restaurante restaurante = restauranteController.buscarPorId(idRestaurante);
        if (restaurante == null) {
            System.out.println("Restaurante não encontrado.");
            return;
        }

        List<Produto> produtos = produtoController.listarPorRestaurante(idRestaurante);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível nesse restaurante.");
            return;
        }

        System.out.println("\n--- PRODUTOS ---");
        produtos.forEach(p -> System.out.println(p.getId_produto() + " - " + p.getNome() + " (R$ " + p.getPreco() + ")"));

        Pedido pedido = new Pedido();
        pedido.setCliente_id(idCliente);
        pedido.setRestaurante_id(idRestaurante);

        double precoTotal = 0;

        while (true) {
            int idProduto = lerInteiro(sc, "Digite o ID do produto (ou 0 para finalizar): ");
            if (idProduto == 0) break;

            Produto p = produtoController.buscarPorId(idProduto);
            if (p == null || p.getRestaurante_id() != idRestaurante) {
                System.out.println("Produto inválido para este restaurante.");
                continue;
            }

            int quantidade = lerInteiro(sc, "Quantidade: ");
            if (quantidade <= 0) {
                System.out.println("Quantidade inválida.");
                continue;
            }

            // Criar ItemPedido e adicionar no pedido
            ItemPedido item = new ItemPedido(
                    0,                  // id_item será gerado pelo banco
                    p.getId_produto(),  // produto_id
                    quantidade,         // quantidade
                    p.getPreco()        // preço unitário
            );
            pedido.adicionarItem(item);
            precoTotal += p.getPreco() * quantidade;

            System.out.println(
                    "Item adicionado: " + p.getNome() +
                            " x" + quantidade +
                            " | Subtotal: R$ " + String.format("%.2f", precoTotal)
            );
        }

        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum item adicionado. Pedido cancelado.");
            return;
        }

        pedido.setPreco_total(precoTotal);

        if (pedidoController.cadastrar(pedido)) {
            System.out.println("Pedido realizado com sucesso! Total: R$ " + String.format("%.2f", precoTotal));
        } else {
            System.out.println("Erro ao realizar pedido.");
        }
    }

    private void acompanharPedidos() {
        List<Pedido> pedidos = pedidoController.listarPorCliente(idCliente);
        if (pedidos.isEmpty()) {
            System.out.println("Você não possui pedidos.");
            return;
        }

        System.out.println("\n--- SEUS PEDIDOS ---");
        pedidos.forEach(System.out::println);
    }
}
