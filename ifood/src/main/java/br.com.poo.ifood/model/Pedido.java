package br.com.poo.ifood.model;

public class Pedido {
    private int id;
    private int clienteId;
    private int restauranteId;
    private int produtoId;
    private int quantidade;
    private double precoTotal;

    public Pedido() {
    }

    public Pedido(int clienteId, int restauranteId, int produtoId, int quantidade, double precoTotal) {
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(int restauranteId) {
        this.restauranteId = restauranteId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    @Override
    public String toString() {
        return id + " | cli=" + clienteId + " | rest=" + restauranteId + " | prod=" + produtoId + " | q=" + quantidade + " | R$ " + precoTotal;
    }
}