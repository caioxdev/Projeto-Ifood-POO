package br.com.poo.ifood.dao;

import br.com.poo.ifood.model.Favorito;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoritoDAO {

    public boolean adicionar(int clienteId, int restauranteId) {
        String sql = "INSERT INTO favoritos (cliente_id, restaurante_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            stmt.setInt(2, restauranteId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar favorito: " + e.getMessage());
            return false;
        }
    }

    public List<Favorito> listarPorCliente(int clienteId) {
        List<Favorito> lista = new ArrayList<>();
        String sql = "SELECT * FROM favoritos WHERE cliente_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Favorito f = new Favorito();
                f.setId_favorito(rs.getInt("id_favorito"));
                f.setCliente_id(rs.getInt("cliente_id"));
                f.setRestaurante_id(rs.getInt("restaurante_id"));
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar favoritos: " + e.getMessage());
        }

        return lista;
    }

    public boolean remover(int clienteId, int restauranteId) {
        String sql = "DELETE FROM favoritos WHERE cliente_id = ? AND restaurante_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            stmt.setInt(2, restauranteId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover favorito: " + e.getMessage());
            return false;
        }
    }
}
