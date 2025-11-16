package br.com.poo.ifood.model;

public class Restaurante {

    private int idRestaurante;
    private String nome;
    private String telefone;
    private String endereco;
    private int categoriaId;
    private double avaliacao;

    public Restaurante() {
    }

    public Restaurante(int idRestaurante, String nome, String telefone, String endereco, int categoriaId, double avaliacao) {
        this.idRestaurante = idRestaurante;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.categoriaId = categoriaId;
        this.avaliacao = avaliacao;
    }

    public Restaurante(String nome, String telefone, String endereco, int categoriaId, double avaliacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.categoriaId = categoriaId;
        this.avaliacao = avaliacao;
    }

    // -------------------------
    // GETTERS E SETTERS OFICIAIS
    // -------------------------

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    // -------------------------------------
    // GETTERS EXTRA para evitar erros na VIEW
    // (Compatíveis com getId() e setId())
    // -------------------------------------

    public int getId() {
        return idRestaurante;
    }

    public void setId(int id) {
        this.idRestaurante = id;
    }

    @Override
    public String toString() {
        return "Restaurante {" +
                "id=" + idRestaurante +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", categoriaId=" + categoriaId +
                ", avaliacao=" + avaliacao +
                '}';
    }
}
