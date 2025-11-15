package br.com.poo.ifood.view;

import br.com.poo.ifood.controller.ClienteController;
import br.com.poo.ifood.model.Cliente;

import java.util.Scanner;

public class ClienteCadastroView {

    private ClienteController clienteController = new ClienteController();

    public void cadastrarCliente(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Cliente cliente = new Cliente(nome, email, senha, telefone);

        if (clienteController.cadastrar(cliente)) {
            System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId_cliente());
        } else {
            System.out.println("Erro ao cadastrar cliente.");
        }
    }
}
