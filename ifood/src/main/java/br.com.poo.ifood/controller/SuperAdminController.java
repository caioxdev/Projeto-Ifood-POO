package br.com.poo.ifood.controller;

import br.com.poo.ifood.model.SuperAdmin;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminController {

    private List<SuperAdmin> superAdmins;

    public SuperAdminController() {
        this.superAdmins = new ArrayList<>();
    }

    /**
     * Cadastra um novo SuperAdmin.
     * @param sa Objeto SuperAdmin a ser cadastrado
     * @return true se cadastrado com sucesso, false se já existir
     */
    public boolean cadastrar(SuperAdmin sa) {
        // Evita cadastro duplicado pelo email
        for (SuperAdmin s : superAdmins) {
            if (s.getEmail().equalsIgnoreCase(sa.getEmail())) {
                return false; // já existe
            }
        }
        return superAdmins.add(sa);
    }

    /**
     * Lista todos os SuperAdmins cadastrados.
     * @return Lista de SuperAdmins
     */
    public List<SuperAdmin> listar() {
        return new ArrayList<>(superAdmins); // devolve uma cópia para evitar alterações externas
    }

    /**
     * Procura um SuperAdmin pelo email
     * @param email Email a ser buscado
     * @return SuperAdmin se encontrado, null se não existir
     */
    public SuperAdmin buscarPorEmail(String email) {
        for (SuperAdmin s : superAdmins) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Remove um SuperAdmin pelo email
     * @param email Email do SuperAdmin a ser removido
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String email) {
        SuperAdmin sa = buscarPorEmail(email);
        if (sa != null) {
            return superAdmins.remove(sa);
        }
        return false;
    }
}
