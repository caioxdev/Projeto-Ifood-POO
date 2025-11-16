package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.model.ItemPedido;

import java.util.List;

public class PedidoView {
    private RestauranteController restauranteController = new RestauranteController();
    private ProdutoController produtoController = new ProdutoController();
    private PedidoController pedidoController = new PedidoController();

    private int idCliente = -1; // -1 indica admin

    public PedidoView(int idCliente) { this.idCliente = idCliente; }
    public PedidoView() { this.idCliente = -1; }

    public void menu(java.util.Scanner sc) {
        int op;
        do {
            System.out.println("\n--- PEDIDOS ---");
            if (idCliente == -1) System.out.println("1. Ver todos os pedidos");
            else {
                System.out.println("1. Fazer pedido");
                System.out.println("2. Acompanhar meus pedidos");
            }
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> { if (idCliente == -1) acompanharTodosPedidos(); else fazerPedido(sc); }
                case 2 -> { if (idCliente != -1) acompanharPedidos(); else System.out.println("Opção inválida."); }
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void fazerPedido(java.util.Scanner sc) {
        listarRestaurantes();
        System.out.print("Digite o ID do restaurante: ");
        int idRestaurante = Integer.parseInt(sc.nextLine());

        List<Produto> produtos = produtoController.listarPorRestaurante(idRestaurante);
        if (produtos.isEmpty()) { System.out.println("Nenhum produto disponível nesse restaurante."); return; }

        System.out.println("\n--- PRODUTOS ---");
        produtos.forEach(System.out::println);

        double precoTotal = 0;
        Pedido pedido = new Pedido();
        pedido.setCliente_id(idCliente);
        pedido.setRestaurante_id(idRestaurante);

        while (true) {
            System.out.print("Digite o ID do produto (ou 0 para finalizar): ");
            int idProduto = Integer.parseInt(sc.nextLine());
            if (idProduto == 0) break;

            Produto p = produtoController.buscarPorId(idProduto);
            if (p == null) { System.out.println("Produto não encontrado."); continue; }

            System.out.print("Quantidade: ");
            int q = Integer.parseInt(sc.nextLine());

            ItemPedido item = new ItemPedido(
                    0, p.getId_produto(), q, p.getPreco()
            );
            pedido.adicionarItem(item);
            precoTotal += p.getPreco() * q;

            System.out.println("Item adicionado: " + p.getNome() + " x" + q +
                    " | Subtotal: R$ " + String.format("%.2f", precoTotal));
        }

        if (pedido.getItens().isEmpty()) { System.out.println("Nenhum item adicionado. Pedido cancelado."); return; }

        pedido.setPreco_total(precoTotal);
        if (pedidoController.cadastrar(pedido) && pedidoController.cadastrarItens(pedido.getItens(), pedido.getId_pedido())) {
            System.out.println("Pedido realizado com sucesso! Total: R$ " + String.format("%.2f", precoTotal));
        } else System.out.println("Erro ao realizar pedido.");
    }

    private void acompanharPedidos() {
        List<Pedido> pedidos = pedidoController.listarPorCliente(idCliente);
        if (pedidos.isEmpty()) System.out.println("Você não possui pedidos.");
        else { System.out.println("\n--- SEUS PEDIDOS ---"); pedidos.forEach(System.out::println); }
    }

    private void acompanharTodosPedidos() {
        List<Pedido> pedidos = pedidoController.listarTodos();
        if (pedidos.isEmpty()) System.out.println("Nenhum pedido registrado.");
        else { System.out.println("\n--- TODOS OS PEDIDOS ---"); pedidos.forEach(System.out::println); }
    }

    private void listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteController.listar();
        if (restaurantes.isEmpty()) System.out.println("Nenhum restaurante cadastrado.");
        else { System.out.println("\n--- RESTAURANTES ---"); restaurantes.forEach(System.out::println); }
    }
}
