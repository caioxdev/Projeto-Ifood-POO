package br.com.poo.ifood.model;

public class Produto {
    private static int contador = 1;
    private int id_produto;
    private int restaurante_id;
    private String nome;
    private String descricao;
    private int quantidade;
    private double preco;

    // Construtor vazio (útil para consultas do banco)
    public Produto() {}

    // Construtor para criar produto novo
    public Produto(int restaurante_id, String nome, String descricao, int quantidade, double preco) {
        this.id_produto = contador++;
        this.restaurante_id = restaurante_id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Construtor completo (útil para consultas com ID do banco)
    public Produto(int id_produto, int restaurante_id, String nome, String descricao, int quantidade, double preco) {
        this.id_produto = id_produto;
        this.restaurante_id = restaurante_id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public int getId_produto() { return id_produto; }
    public void setId_produto(int id_produto) { this.id_produto = id_produto; }
    public int getRestaurante_id() { return restaurante_id; }
    public void setRestaurante_id(int restaurante_id) { this.restaurante_id = restaurante_id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return id_produto + " | " + nome + " | " + descricao + " | Qtd: " + quantidade + " | R$" + preco;
    }
}
