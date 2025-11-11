package br.com.poo.ifood.model;

public class Pedido {
    private Integer id_pedido;
    private Integer cliente_id;
    private Integer restaurante_id;
    private Integer produto_id;
    private Integer quantidade;
    private Double preco_total;

    public Pedido() {}

    public Pedido(Integer cliente_id, Integer restaurante_id, Integer produto_id, Integer quantidade, Double preco_total) {
        this.cliente_id = cliente_id;
        this.restaurante_id = restaurante_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco_total = preco_total;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Integer getRestaurante_id() {
        return restaurante_id;
    }

    public void setRestaurante_id(Integer restaurante_id) {
        this.restaurante_id = restaurante_id;
    }

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(Double preco_total) {
        this.preco_total = preco_total;
    }

    @Override
    public String toString() {
        return "Pedido {" +
                "id=" + id_pedido +
                ", cliente_id=" + cliente_id +
                ", restaurante_id=" + restaurante_id +
                ", produto_id=" + produto_id +
                ", quantidade=" + quantidade +
                ", preco_total=" + preco_total +
                '}';
    }
}
