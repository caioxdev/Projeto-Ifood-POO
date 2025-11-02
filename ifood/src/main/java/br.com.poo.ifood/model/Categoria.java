package br.com.poo.ifood.model;

public class Categoria {
    private Integer id_categoria;
    private String nome;
    private String descricao;

    public Categoria() {}

    public Categoria( String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
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
}
