package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.model.Produto;

import java.util.List;
import java.util.Scanner;

public class PedidoView {
    private Scanner sc;
    private ProdutoController produtoController = new ProdutoController();
    private RestauranteController restauranteController = new RestauranteController();

    public PedidoView(Scanner sc) {
        this.sc = sc;
    }

    public void fazerPedido() {
        // Listar restaurantes
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

        // Listar produtos do restaurante
        List<Produto> produtos = produtoController.listarPorRestaurante(r.getId_restaurante());
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto nesse restaurante.");
            return;
        }

        boolean adicionar = true;
        while (adicionar) {
            System.out.println("\n--- PRODUTOS ---");
            for (Produto p : produtos) {
                System.out.println(p.getId_produto() + " - " + p.getNome() + " | R$ " + p.getPreco());
            }

            System.out.print("ID do produto: ");
            int idProd = Integer.parseInt(sc.nextLine());
            Produto p = produtoController.buscarPorId(idProd);
            if (p == null) {
                System.out.println("Produto inválido.");
            }

            System.out.print("Adicionar outro produto? (s/n): ");
            adicionar = sc.nextLine().equalsIgnoreCase("s");
        }

        System.out.println("Pedido finalizado!");
    }
}
