package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ClienteController;
import br.com.poo.ifood.model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private ClienteController controller = new ClienteController();

    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- CLIENTES ---");
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
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Cliente c = new Cliente();
        c.setNome(nome);
        c.setEmail(email);
        c.setSenha(senha);
        c.setTelefone(telefone);

        if (controller.cadastrar(c)) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar cliente.");
        }
    }

    private void listar() {
        List<Cliente> clientes = controller.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            System.out.println("\n--- LISTA DE CLIENTES ---");
            for (Cliente c : clientes) {
                System.out.println(
                        "ID: " + c.getId_cliente() +
                                " | Nome: " + c.getNome() +
                                " | Email: " + c.getEmail() +
                                " | Telefone: " + c.getTelefone()
                );
            }
        }
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID do cliente a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Cliente c = controller.buscarPorId(id);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + c.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Novo email (" + c.getEmail() + "): ");
        String email = sc.nextLine();
        System.out.print("Nova senha: ");
        String senha = sc.nextLine();
        System.out.print("Novo telefone (" + c.getTelefone() + "): ");
        String tel = sc.nextLine();

        c.setNome(nome.isEmpty() ? c.getNome() : nome);
        c.setEmail(email.isEmpty() ? c.getEmail() : email);
        c.setSenha(senha.isEmpty() ? c.getSenha() : senha);
        c.setTelefone(tel.isEmpty() ? c.getTelefone() : tel);

        if (controller.atualizar(c)) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar cliente.");
        }
    }

    private void remover(Scanner sc) {
        listar();
        System.out.print("ID do cliente a remover: ");
        int id = Integer.parseInt(sc.nextLine());
        if (controller.remover(id)) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Erro ao remover cliente.");
        }
    }
}
