package br.com.poo.ifood.model;

public class Favorito {

    private int id_favorito;
    private int cliente_id;
    private int restaurante_id;

    public Favorito() {
    }

    public Favorito(int id_favorito, int cliente_id, int restaurante_id) {
        this.id_favorito = id_favorito;
        this.cliente_id = cliente_id;
        this.restaurante_id = restaurante_id;
    }

    // GETTERS E SETTERS
    public int getId_favorito() {
        return id_favorito;
    }

    public void setId_favorito(int id_favorito) {
        this.id_favorito = id_favorito;
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

    @Override
    public String toString() {
        return "Favorito{" +
                "id_favorito=" + id_favorito +
                ", cliente_id=" + cliente_id +
                ", restaurante_id=" + restaurante_id +
                '}';
    }
}
