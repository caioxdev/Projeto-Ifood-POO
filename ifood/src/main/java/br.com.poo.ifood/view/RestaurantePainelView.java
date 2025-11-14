package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Restaurante;

import java.util.Scanner;

public class RestaurantePainelView {

    private RestauranteController controller = new RestauranteController();

    public void menu(Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- PAINEL DO RESTAURANTE ---");
            System.out.println("1 Cadastrar");
            System.out.println("2 Listar");
            System.out.println("3 Atualizar");
            System.out.println("4 Remover");
            System.out.println("0 Voltar");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrar(sc);
                case 2 -> listar();
                case 3 -> atualizar(sc);
                case 4 -> remover(sc);
            }
        } while (opcao != 0);
    }

    private void cadastrar(Scanner sc) {
        Restaurante r = new Restaurante();

        System.out.print("Nome: ");
        r.setNome(sc.nextLine());

        System.out.print("Telefone: ");
        r.setTelefone(sc.nextLine());

        System.out.print("Endereço: ");
        r.setEndereco(sc.nextLine());

        System.out.print("Categoria ID: ");
        r.setCategoriaId(Integer.parseInt(sc.nextLine()));

        System.out.print("Avaliação (0-5): ");
        r.setAvaliacao(Double.parseDouble(sc.nextLine()));

        boolean ok = controller.create(r);
        System.out.println(ok ? "Cadastrado!" : "Erro ao cadastrar.");
    }

    private void listar() {
        var lista = controller.findAll();
        if (lista.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
            return;
        }

        for (Restaurante r : lista) {
            System.out.println(r);
        }
    }

    private void atualizar(Scanner sc) {
        System.out.print("ID para atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        controller.atualizarViaTerminal(sc, id);
    }

    private void remover(Scanner sc) {
        System.out.print("ID para remover: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean ok = controller.delete(id);
        System.out.println(ok ? "Removido." : "Erro ao remover.");
    }
}
