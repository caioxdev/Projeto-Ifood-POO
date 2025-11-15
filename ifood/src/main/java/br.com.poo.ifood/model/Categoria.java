package br.com.poo.ifood.model;

public class Categoria {
    private static int contador = 1;
    private int id_categoria;
    private String nome;
    private String descricao;

    public Categoria() {
        this.id_categoria = contador++;
    }

    public Categoria(String nome, String descricao) {
        this.id_categoria = contador++;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id_categoria;
    }

    public void setId(int id) {
        this.id_categoria = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return id_categoria + " | " + nome + " | " + descricao;
    }
}
