package br.com.poo.ifood.model;

public class Restaurante {
    private Integer id_restaurante;
    private String nome;
    private String telefone;
    private String endereco;
    private Integer categoria_id;
    private Double avaliacao;

    public Restaurante() {}

    public Restaurante(String nome, String telefone, String endereco, Integer categoria_id, Double avaliacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.categoria_id = categoria_id;
        this.avaliacao = avaliacao;
    }

    public Integer getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(Integer id_restaurante) {
        this.id_restaurante = id_restaurante;
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

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Restaurante {" +
                "id=" + id_restaurante +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", categoria_id=" + categoria_id +
                ", avaliacao=" + avaliacao +
                '}';
    }
}
