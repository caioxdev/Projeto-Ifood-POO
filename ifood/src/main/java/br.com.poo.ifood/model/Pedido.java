package br.com.poo.ifood.model;

public class Pedido {
    private int id_pedido;
    private int cliente_id;
    private int restaurante_id;
    private int produto_id;
    private int quantidade;
    private double preco_total;

    public Pedido() {}

    public Pedido(int cliente_id, int restaurante_id, int produto_id, int quantidade, double preco_total) {
        this.cliente_id = cliente_id;
        this.restaurante_id = restaurante_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco_total = preco_total;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getRestaurante_id() {
        return restaurante_id;
    }

    public void setRestaurante_id(int restaurante_id) {
        this.restaurante_id = restaurante_id;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(double preco_total) {
        this.preco_total = preco_total;
    }

    @Override
    public String toString() {
        return "Pedido { id=" + id_pedido + ", cliente=" + cliente_id + ", produto=" + produto_id + " }";
    }
}
