package br.com.poo.ifood.controller;

import br.com.poo.ifood.dao.RestauranteDAO;
import br.com.poo.ifood.model.Restaurante;

import java.util.List;
import java.util.Scanner;

public class RestauranteController {
    private RestauranteDAO dao = new RestauranteDAO();

    public boolean create(Restaurante r) {
        return dao.create(r);
    }

    public List<Restaurante> findAll() {
        return dao.findAll();
    }

    public Restaurante findById(int id) {
        return dao.findById(id);
    }

    public boolean update(Restaurante r) {
        return dao.update(r);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public void atualizarViaTerminal(Scanner sc, int id) {
        Restaurante r = dao.findById(id);
        if (r == null) {
            System.out.println("Restaurante não encontrado!");
            return;
        }

        System.out.print("Nome (" + r.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) r.setNome(nome);

        System.out.print("Telefone (" + r.getTelefone() + "): ");
        String telefone = sc.nextLine();
        if (!telefone.isEmpty()) r.setTelefone(telefone);

        System.out.print("Endereço (" + r.getEndereco() + "): ");
        String endereco = sc.nextLine();
        if (!endereco.isEmpty()) r.setEndereco(endereco);

        System.out.print("Avaliação (" + r.getAvaliacao() + "): ");
        String aval = sc.nextLine();
        if (!aval.isEmpty()) r.setAvaliacao(Double.parseDouble(aval));

        System.out.print("Categoria ID (" + r.getCategoriaId() + "): ");
        String cat = sc.nextLine();
        if (!cat.isEmpty()) r.setCategoriaId(Integer.parseInt(cat));

        boolean ok = dao.update(r);
        System.out.println(ok ? "Atualizado com sucesso." : "Erro ao atualizar.");
    }
}
