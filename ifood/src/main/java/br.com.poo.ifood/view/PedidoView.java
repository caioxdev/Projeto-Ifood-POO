package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.PedidoController;
import br.com.poo.ifood.model.Pedido;
import java.util.List;
import java.util.Scanner;

public class PedidoView {
    private PedidoController controller = new PedidoController();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("1 Cadastrar");
            System.out.println("2 Listar");
            System.out.println("3 Atualizar");
            System.out.println("4 Deletar");
            System.out.println("0 Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> cadastrar(sc);
                case 2 -> listar();
                case 3 -> atualizar(sc);
                case 4 -> deletar(sc);
            }
        } while (op != 0);
    }

    public void cadastrar(Scanner sc) {
        System.out.print("ID Cliente: ");
        int cli = Integer.parseInt(sc.nextLine());
        System.out.print("ID Restaurante: ");
        int res = Integer.parseInt(sc.nextLine());
        System.out.print("ID Produto: ");
        int prod = Integer.parseInt(sc.nextLine());
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("Preço total: ");
        double preco = Double.parseDouble(sc.nextLine());
        Pedido p = new Pedido(cli, res, prod, qtd, preco);
        controller.cadastrar(p);
        System.out.println("OK");
    }

    public void listar() {
        List<Pedido> lista = controller.listar();
        System.out.println("\nID | Cliente | Restaurante | Produto | Qtd | Preço");
        for (Pedido p : lista) System.out.println(p);
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("ID Cliente: ");
        int cli = Integer.parseInt(sc.nextLine());
        System.out.print("ID Restaurante: ");
        int res = Integer.parseInt(sc.nextLine());
        System.out.print("ID Produto: ");
        int prod = Integer.parseInt(sc.nextLine());
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("Preço total: ");
        double preco = Double.parseDouble(sc.nextLine());
        Pedido p = new Pedido(cli, res, prod, qtd, preco);
        p.setId(id);
        controller.atualizar(p);
        System.out.println("Atualizado");
    }

    private void deletar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        controller.deletar(id);
        System.out.println("Deletado");
    }
}
