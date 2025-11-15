package br.com.poo.ifood.model;

public class Restaurante {
    private static int contador = 1;
    private int id_restaurante;
    private String nome;
    private String telefone;
    private String endereco;
    private int categoria_id;
    private double avaliacao;
    private boolean ativo;

    // Construtor vazio
    public Restaurante() {
        this.id_restaurante = contador++;
        this.ativo = true;
    }

    // Construtor existente: 5 parâmetros
    public Restaurante(String nome, String telefone, String endereco, int categoria_id, double avaliacao) {
        this.id_restaurante = contador++;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.categoria_id = categoria_id;
        this.avaliacao = avaliacao;
        this.ativo = true;
    }

    // Construtor necessário para o DAO (3 parâmetros)
    public Restaurante(int id_restaurante, String nome, String telefone) {
        this.id_restaurante = id_restaurante;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = true;
    }

    // Getters e Setters
    public int getId() { return id_restaurante; }
    public void setId(int id) { this.id_restaurante = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public int getCategoria_id() { return categoria_id; }
    public void setCategoria_id(int categoria_id) { this.categoria_id = categoria_id; }

    public double getAvaliacao() { return avaliacao; }
    public void setAvaliacao(double avaliacao) { this.avaliacao = avaliacao; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return id_restaurante + " | " + nome + " | " + telefone + " | " + endereco +
                " | Categoria: " + categoria_id + " | Avaliação: " + avaliacao + " | Ativo: " + ativo;
    }
}
