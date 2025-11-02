package br.com.poo.ifood.model;

public class Produto {
    private Integer id_produto;
    private String descricao;
    private Integer quantidade;
    private Double preco;
    private String nome;

    public Produto() {}

    public Produto (String descricao, Integer quantidade, Double preco, String nome) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.nome = nome;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}