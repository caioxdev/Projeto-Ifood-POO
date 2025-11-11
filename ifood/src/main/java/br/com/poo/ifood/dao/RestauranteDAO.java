package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Restaurante;
import java.sql.*;
import java.util.*;

public class RestauranteDAO {

    public void create(Restaurante restaurante) {
        String sql = "INSERT INTO Restaurante (nome, telefone, endereco, categoria_id, avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTelefone());
            stmt.setString(3, restaurante.getEndereco());
            stmt.setInt(4, restaurante.getCategoria_id());
            stmt.setDouble(5, restaurante.getAvaliacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Restaurante> readAll() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId_restaurante(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoria_id(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
                restaurantes.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return restaurantes;
    }

    public void update(Restaurante restaurante) {
        String sql = "UPDATE Restaurante SET nome=?, telefone=?, endereco=?, categoria_id=?, avaliacao=? WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTelefone());
            stmt.setString(3, restaurante.getEndereco());
            stmt.setInt(4, restaurante.getCategoria_id());
            stmt.setDouble(5, restaurante.getAvaliacao());
            stmt.setInt(6, restaurante.getId_restaurante());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Restaurante WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
