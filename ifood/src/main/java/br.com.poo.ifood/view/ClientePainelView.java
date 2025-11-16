package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ClienteController;
import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.ItemPedido;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;
import java.util.Scanner;

public class ClientePainelView {

    private ClienteController clienteController = new ClienteController();
    private RestauranteController restauranteController = new RestauranteController();
    private ProdutoController produtoController = new ProdutoController();
    private PedidoController pedidoController = new PedidoController();

    private int idCliente;

    public ClientePainelView(int idCliente) {
        this.idCliente = idCliente;
    }

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n====== CLIENTE ======");
            System.out.println("1. Fazer pedido");
            System.out.println("2. Acompanhar meus pedidos");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> fazerPedido(sc);
                case 2 -> acompanharPedidos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteController.listar();
        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
        } else {
            System.out.println("\n--- RESTAURANTES ---");
            restaurantes.forEach(r -> System.out.println(
                    "ID: " + r.getIdRestaurante() +
                            " | Nome: " + r.getNome() +
                            " | Endereço: " + r.getEndereco() +
                            " | Telefone: " + r.getTelefone() +
                            " | Categoria: " + r.getCategoriaId() +
                            " | Avaliação: " + r.getAvaliacao()
            ));
        }
    }

    private void fazerPedido(Scanner sc) {
        listarRestaurantes();
        System.out.print("Digite o ID do restaurante: ");
        int idRestaurante = Integer.parseInt(sc.nextLine());

        List<Produto> produtos = produtoController.listarPorRestaurante(idRestaurante);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível nesse restaurante.");
            return;
        }

        System.out.println("\n--- PRODUTOS ---");
        produtos.forEach(p -> System.out.println(
                "ID: " + p.getId_produto() +
                        " | Nome: " + p.getNome() +
                        " | Preço: R$ " + p.getPreco()
        ));

        Pedido pedido = new Pedido();
        pedido.setCliente_id(idCliente);
        pedido.setRestaurante_id(idRestaurante);

        double precoTotal = 0;

        while (true) {
            System.out.print("Digite o ID do produto (ou 0 para finalizar): ");
            int idProduto = Integer.parseInt(sc.nextLine());
            if (idProduto == 0) break;

            Produto produto = produtoController.buscarPorId(idProduto);
            if (produto == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(sc.nextLine());

            ItemPedido item = new ItemPedido();
            item.setProduto_id(produto.getId_produto());
            item.setQuantidade(quantidade);
            item.setPreco_unitario(produto.getPreco());

            pedido.adicionarItem(item);

            precoTotal += produto.getPreco() * quantidade;
            System.out.println("Item adicionado: " + produto.getNome() + " x" + quantidade +
                    " | Subtotal: R$ " + String.format("%.2f", precoTotal));
        }

        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum item adicionado. Pedido cancelado.");
            return;
        }

        pedido.setPreco_total(precoTotal);

        // Cadastra pedido no banco
        if (pedidoController.cadastrar(pedido)) {
            // Cadastra itens do pedido
            if (pedidoController.cadastrarItens(pedido.getItens(), pedido.getId_pedido())) {
                System.out.println("Pedido realizado com sucesso! Total: R$ " + String.format("%.2f", precoTotal));
            } else {
                System.out.println("Erro ao cadastrar itens do pedido.");
            }
        } else {
            System.out.println("Erro ao cadastrar pedido.");
        }
    }

    private void acompanharPedidos() {
        List<Pedido> pedidos = pedidoController.listarPorCliente(idCliente);
        if (pedidos.isEmpty()) {
            System.out.println("Você não possui pedidos.");
        } else {
            System.out.println("\n--- SEUS PEDIDOS ---");
            pedidos.forEach(p -> System.out.println(
                    "ID: " + p.getId_pedido() +
                            " | Restaurante ID: " + p.getRestaurante_id() +
                            " | Total: R$ " + String.format("%.2f", p.getPreco_total())
            ));
        }
    }
}
