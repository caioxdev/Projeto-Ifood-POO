package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.controller.ProdutoController;
import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Cliente;
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

    public void fazerPedido(Cliente cliente, PedidoController pedidoController) {

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

        List<Produto> produtos = produtoController.listarPorRestaurante(idRest);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto nesse restaurante.");
            return;
        }

        pedidoController.fazerPedido(cliente, idRest, produtos, sc);
    }
}
