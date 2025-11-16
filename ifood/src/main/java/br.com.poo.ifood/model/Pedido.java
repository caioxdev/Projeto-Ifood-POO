package br.com.poo.ifood.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id_pedido;
    private int cliente_id;
    private int restaurante_id;
    private double preco_total;
    private List<ItemPedido> itens;

    public Pedido() {
        itens = new ArrayList<>();
    }

    // Getters e setters
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

    public double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(double preco_total) {
        this.preco_total = preco_total;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    // **Método que faltava**
    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id_pedido=" + id_pedido +
                ", cliente_id=" + cliente_id +
                ", restaurante_id=" + restaurante_id +
                ", preco_total=" + preco_total +
                ", itens=" + itens +
                '}';
    }
}
