package br.com.poo.ifood.model;

public class Restaurante {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private int categoriaId;
    private double avaliacao;

    public Restaurante() {
    }

    public Restaurante(String nome, String telefone, String endereco, int categoriaId, double avaliacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.categoriaId = categoriaId;
        this.avaliacao = avaliacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return id + " | " + nome + " | " + telefone + " | " + endereco + " | Cat: " + categoriaId + " | Aval: " + avaliacao;
    }
}
