package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.PedidoDAO;
import br.com.poo.ifood.model.Cliente;
import br.com.poo.ifood.model.ItensPedido;
import br.com.poo.ifood.model.Pedido;
import br.com.poo.ifood.model.Produto;

import java.util.List;
import java.util.Scanner;

public class PedidoController {
    private PedidoDAO dao = new PedidoDAO();

    // ================== Cores ANSI ==================
    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROXO = "\u001B[35m";
    private static final String CIANO = "\u001B[36m";

    public void fazerPedido(Cliente cliente, int restauranteId, List<Produto> produtos, Scanner sc) {
        if (produtos.isEmpty()) {
            System.out.println(VERMELHO + "Nenhum produto disponível." + RESET);
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setCliente_id(cliente.getId_cliente());
        pedido.setRestaurante_id(restauranteId);

        double total = 0;
        boolean adicionar = true;

        while (adicionar) {
            System.out.println(AZUL + "\n--- PRODUTOS ---" + RESET);
            for (Produto p : produtos) {
                System.out.println(p.getId_produto() + " - " + p.getNome() + " | R$ " + p.getPreco());
            }

            System.out.print(AMARELO + "ID do produto: " + RESET);
            int idProd = Integer.parseInt(sc.nextLine());
            Produto p = produtos.stream().filter(prod -> prod.getId_produto() == idProd).findFirst().orElse(null);

            if (p != null) {
                System.out.print(AMARELO + "Quantidade: " + RESET);
                int qtd = Integer.parseInt(sc.nextLine());

                ItensPedido item = new ItensPedido();
                item.setProduto_id(p.getId_produto());
                item.setQuantidade(qtd);
                item.setPreco_unitario(p.getPreco());

                pedido.getItens().add(item);
                total += p.getPreco() * qtd;
            } else {
                System.out.println(VERMELHO + "Produto inválido." + RESET);
            }

            System.out.print(AMARELO + "Adicionar outro produto? (s/n): " + RESET);
            adicionar = sc.nextLine().equalsIgnoreCase("s");
        }

        pedido.setPreco_total(total);

        if (dao.cadastrar(pedido)) {
            System.out.println(VERDE + "Pedido realizado! Total: R$ " + total + RESET);
        } else {
            System.out.println(VERMELHO + "Erro ao realizar pedido." + RESET);
        }
    }

    public List<Pedido> listarPedidosPorCliente(int clienteId) {
        return dao.listarPorCliente(clienteId);
    }

    public List<Pedido> listarTodos() {
        return dao.listarTodos();
    }

}
