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

    // Método para fazer pedido
    public void fazerPedido(Cliente cliente, List<Produto> produtos, Scanner sc) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível.");
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setCliente_id(cliente.getId_cliente());

        double total = 0;
        boolean adicionar = true;

        while (adicionar) {
            System.out.println("\n--- PRODUTOS ---");
            for (Produto p : produtos) {
                System.out.println(p.getId_produto() + " - " + p.getNome() + " | R$ " + p.getPreco());
            }

            System.out.print("ID do produto: ");
            int idProd = Integer.parseInt(sc.nextLine());
            Produto p = produtos.stream().filter(prod -> prod.getId_produto() == idProd).findFirst().orElse(null);

            if (p != null) {
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(sc.nextLine());

                ItensPedido item = new ItensPedido();
                item.setProduto_id(p.getId_produto());
                item.setQuantidade(qtd);
                item.setPreco_unitario(p.getPreco());

                pedido.getItens().add(item);
                total += p.getPreco() * qtd;
            } else {
                System.out.println("Produto inválido.");
            }

            System.out.print("Adicionar outro produto? (s/n): ");
            adicionar = sc.nextLine().equalsIgnoreCase("s");
        }

        pedido.setPreco_total(total);

        if (dao.cadastrar(pedido)) {
            System.out.println("Pedido realizado! Total: R$ " + total);
        } else {
            System.out.println("Erro ao realizar pedido.");
        }
    }

    // Método **corrigido**: retorna lista de pedidos de um cliente
    public List<Pedido> listarPedidosPorCliente(int clienteId) {
        return dao.listarPorCliente(clienteId);
    }
}
