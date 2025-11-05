package br.com.poo.ifood.model;

public class SuperAdmin {
    private Integer id_superadmin;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public SuperAdmin() {}

    public SuperAdmin(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public Integer getId_superadmin() {
        return id_superadmin;
    }

    public void setId_superadmin(Integer id_superadmin) {
        this.id_superadmin = id_superadmin;
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
        return "SuperAdmin {" +
                "id=" + id_superadmin +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
