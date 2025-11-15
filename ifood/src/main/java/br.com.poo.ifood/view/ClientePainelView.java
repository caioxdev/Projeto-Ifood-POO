package br.com.poo.ifood.view;

import br.com.poo.ifood.dao.PedidoDAO;
import br.com.poo.ifood.dao.ProdutoDAO;
import br.com.poo.ifood.dao.RestauranteDAO;
import br.com.poo.ifood.model.ItemPedido;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;
import br.com.poo.ifood.model.Restaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientePainelView {

    private final int clienteId;
    private final Scanner sc = new Scanner(System.in);
    private final RestauranteDAO restauranteDAO = new RestauranteDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public ClientePainelView(int clienteId) {
        this.clienteId = clienteId;
    }

    public void mostrarMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- CLIENTE ---");
            System.out.println("1. Ver Restaurantes");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Acompanhar Pedidos");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> listarRestaurantes();
                case 2 -> fazerPedido();
                case 3 -> acompanharPedidos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteDAO.listar();
        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
            return;
        }

        System.out.println("\n--- RESTAURANTES ---");
        for (Restaurante r : restaurantes) {
            System.out.println(
                    "ID: " + r.getId() +
                            " | Nome: " + r.getNome() +
                            " | Telefone: " + r.getTelefone() +
                            " | Endereço: " + r.getEndereco()
            );
        }
    }

    private void fazerPedido() {
        listarRestaurantes();
        System.out.print("Digite o ID do restaurante: ");
        int restauranteId = sc.nextInt();
        sc.nextLine();

        List<Produto> produtos = produtoDAO.listarPorRestaurante(restauranteId);
        if (produtos.isEmpty()) {
            System.out.println("Este restaurante não possui produtos cadastrados.");
            return;
        }

        List<ItemPedido> itensPedido = new ArrayList<>();
        double total = 0;

        while (true) {
            System.out.println("\n--- PRODUTOS ---");
            for (Produto p : produtos) {
                System.out.println(
                        "ID: " + p.getId_produto() +
                                " | Nome: " + p.getNome() +
                                " | Quantidade: " + p.getQuantidade() +
                                " | Preço: R$ " + String.format("%.2f", p.getPreco())
                );
            }

            System.out.print("Digite o ID do produto para adicionar ao pedido (0 para finalizar): ");
            int produtoId = sc.nextInt();
            sc.nextLine();

            if (produtoId == 0) break;

            Produto produtoEscolhido = produtoDAO.buscarPorId(produtoId);
            if (produtoEscolhido == null || produtoEscolhido.getRestaurante_id() != restauranteId) {
                System.out.println("Produto inválido.");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();
            sc.nextLine();

            if (qtd <= 0 || qtd > produtoEscolhido.getQuantidade()) {
                System.out.println("Quantidade inválida. Disponível: " + produtoEscolhido.getQuantidade());
                continue;
            }

            // ✅ Corrigido: cria ItemPedido com pedido_id = 0 (será gerado pelo banco)
            ItemPedido item = new ItemPedido(0, produtoId, qtd, produtoEscolhido.getPreco());
            itensPedido.add(item);
            total += produtoEscolhido.getPreco() * qtd;

            System.out.println("Produto adicionado ao pedido!");
        }

        if (itensPedido.isEmpty()) {
            System.out.println("Pedido cancelado.");
            return;
        }

        // Cria o pedido
        Pedido pedido = new Pedido(clienteId, restauranteId, total);

        // Associa os itens ao pedido, preenchendo pedido_id após salvar
        if (pedidoDAO.cadastrar(pedido)) {
            int pedidoIdGerado = pedido.getId_pedido();
            for (ItemPedido item : itensPedido) {
                item.setPedido_id(pedidoIdGerado);
            }
            pedido.setItens(itensPedido);

            if (pedidoDAO.cadastrarItens(itensPedido)) {
                System.out.println("Pedido realizado com sucesso! Total: R$ " + String.format("%.2f", total));
            } else {
                System.out.println("Erro ao salvar os itens do pedido.");
            }

        } else {
            System.out.println("Erro ao realizar pedido.");
        }
    }

    private void acompanharPedidos() {
        List<Pedido> pedidos = pedidoDAO.listarPorCliente(clienteId);
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        System.out.println("\n--- MEUS PEDIDOS ---");
        for (Pedido p : pedidos) {
            System.out.println(
                    "Pedido ID: " + p.getId_pedido() +
                            " | Restaurante ID: " + p.getRestaurante_id() +
                            " | Total: R$ " + String.format("%.2f", p.getPreco_total())
            );
            System.out.println("Itens:");
            for (ItemPedido item : p.getItens()) {
                Produto prod = produtoDAO.buscarPorId(item.getProduto_id());
                String nomeProd = (prod != null) ? prod.getNome() : "Produto removido";
                System.out.println(
                        "Produto: " + nomeProd +
                                " | Qtd: " + item.getQuantidade() +
                                " | R$ " + String.format("%.2f", item.getPreco_unitario())
                );
            }
            System.out.println("----------------------------");
        }
    }
}
