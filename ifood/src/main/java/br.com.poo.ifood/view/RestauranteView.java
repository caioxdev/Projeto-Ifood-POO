package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.RestauranteController;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;
import java.util.Scanner;

public class RestauranteView {
    private RestauranteController controller = new RestauranteController();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- RESTAURANTES ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) line = "0";
            op = Integer.parseInt(line);

            switch (op) {
                case 1 -> cadastrar(sc);
                case 2 -> listar();
                case 3 -> atualizar(sc);
                case 4 -> remover(sc);
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("Endereço: ");
        String end = sc.nextLine();
        System.out.print("ID Categoria: ");
        int catId = Integer.parseInt(sc.nextLine());
        System.out.print("Avaliação: ");
        double aval = Double.parseDouble(sc.nextLine());

        Restaurante r = new Restaurante(nome, tel, end, catId, aval);
        if (controller.cadastrar(r)) {
            System.out.println("Restaurante cadastrado!");
        } else {
            System.out.println("Erro ao cadastrar restaurante.");
        }
    }

    private void listar() {
        List<Restaurante> restaurantes = controller.listar();
        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante encontrado.");
        } else {
            System.out.println("\n--- LISTA DE RESTAURANTES ---");
            for (Restaurante r : restaurantes) {
                System.out.println(
                        "ID: " + r.getIdRestaurante() +
                                " | Nome: " + r.getNome() +
                                " | Endereço: " + r.getEndereco() +
                                " | Telefone: " + r.getTelefone() +
                                " | Categoria: " + r.getCategoriaId() +
                                " | Avaliação: " + r.getAvaliacao()
                );
            }
        }
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID do restaurante a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Restaurante r = controller.buscarPorId(id);
        if (r == null) {
            System.out.println("Restaurante não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + r.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Novo telefone (" + r.getTelefone() + "): ");
        String tel = sc.nextLine();
        System.out.print("Novo endereço (" + r.getEndereco() + "): ");
        String end = sc.nextLine();
        System.out.print("Nova categoria (" + r.getCategoriaId() + "): ");
        String catStr = sc.nextLine();
        System.out.print("Nova avaliação (" + r.getAvaliacao() + "): ");
        String avalStr = sc.nextLine();

        r.setNome(nome.isEmpty() ? r.getNome() : nome);
        r.setTelefone(tel.isEmpty() ? r.getTelefone() : tel);
        r.setEndereco(end.isEmpty() ? r.getEndereco() : end);
        r.setCategoriaId(catStr.isEmpty() ? r.getCategoriaId() : Integer.parseInt(catStr));
        r.setAvaliacao(avalStr.isEmpty() ? r.getAvaliacao() : Double.parseDouble(avalStr));

        if (controller.atualizar(r)) {
            System.out.println("Restaurante atualizado!");
        } else {
            System.out.println("Erro ao atualizar restaurante.");
        }
    }

    private void remover(Scanner sc) {
        listar();
        System.out.print("ID do restaurante a remover: ");
        int id = Integer.parseInt(sc.nextLine());
        if (controller.remover(id)) {
            System.out.println("Restaurante removido!");
        } else {
            System.out.println("Erro ao remover restaurante.");
        }
    }
}
