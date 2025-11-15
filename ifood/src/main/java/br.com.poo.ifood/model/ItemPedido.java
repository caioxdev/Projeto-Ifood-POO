package br.com.poo.ifood.model;

public class ItemPedido {
    private int id_item;
    private int pedido_id;
    private int produto_id;
    private int quantidade;
    private double preco_unitario;

    public ItemPedido() {
    }

    // Construtor usado quando o pedido_id ainda não existe
    public ItemPedido(int produto_id, int quantidade, double preco_unitario) {
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    // Construtor completo, quando já se tem o pedido_id
    public ItemPedido(int pedido_id, int produto_id, int quantidade, double preco_unitario) {
        this.pedido_id = pedido_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
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

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    @Override
    public String toString() {
        return id_item + " | Prod:" + produto_id + " | Qtd:" + quantidade + " | R$" + preco_unitario;
    }
}
