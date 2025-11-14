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

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        Cliente c = new Cliente(nome, email, senha, tel);
        controller.cadastrar(c);
        System.out.println("OK");
    }

    private void listar() {
        List<Cliente> lista = controller.listar();
        System.out.println("\nID | Nome | Email");
        for (Cliente c : lista) System.out.println(c);
    }

    private void atualizar(Scanner sc) {
        listar();
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        Cliente c = new Cliente(nome, email, senha, tel);
        c.setId(id);
        controller.atualizar(c);
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
