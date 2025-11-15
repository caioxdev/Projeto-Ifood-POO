package br.com.poo.ifood.model;

public class Produto {
    private int id_produto;
    private int restaurante_id;
    private String nome;
    private String descricao;
    private int quantidade;
    private double preco;

    // Construtor para inserir (sem ID)
    public Produto(int restaurante_id, String nome, String descricao, int quantidade, double preco) {
        this.restaurante_id = restaurante_id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Construtor para quando vem do banco (com ID)
    public Produto(int id_produto, int restaurante_id, String nome, String descricao, int quantidade, double preco) {
        this.id_produto = id_produto;
        this.restaurante_id = restaurante_id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getRestaurante_id() {
        return restaurante_id;
    }

    public void setRestaurante_id(int restaurante_id) {
        this.restaurante_id = restaurante_id;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // toString estilizado
    @Override
    public String toString() {
        return "\n----------------------------" +
                "\nProduto #" + id_produto +
                "\nNome: " + nome +
                "\nDescrição: " + descricao +
                "\nQuantidade: " + quantidade +
                "\nPreço: R$ " + String.format("%.2f", preco) +
                "\nRestaurante ID: " + restaurante_id +
                "\n----------------------------";
    }
}
