package br.com.poo.ifood.model;

public class SuperAdmin {
    private static int contador = 1;
    private int id_superadmin;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public SuperAdmin() {
        this.id_superadmin = contador++;
    }

    public SuperAdmin(String nome, String email, String senha, String telefone) {
        this.id_superadmin = contador++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public int getId_superadmin() {
        return id_superadmin;
    }

    public void setId_superadmin(int id_superadmin) {
        this.id_superadmin = id_superadmin;
    }

    // Métodos getId e setId para compatibilidade com Controller/View
    public int getId() {
        return id_superadmin;
    }

    public void setId(int id) {
        this.id_superadmin = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return id_superadmin + " | " + nome + " | " + email + " | " + telefone;
    }
}
