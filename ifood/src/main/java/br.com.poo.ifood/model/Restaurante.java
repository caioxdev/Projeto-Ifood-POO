package br.com.poo.ifood.model;

public class Restaurante {
    private String nome;
    private String telefone;
    private String endereco;
    private String categoria;
    private Double avaliacao;

    public Restaurante() {}

    public Restaurante(String nome, String telefone, String endereco, String categoria, double avaliacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Restaurante {" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", categoria='" + categoria + '\'' +
                ", avaliacao=" + avaliacao +
                '}';
    }
}