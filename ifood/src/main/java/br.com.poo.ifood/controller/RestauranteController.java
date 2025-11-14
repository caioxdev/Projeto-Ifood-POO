package br.com.poo.ifood.controller;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Restaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteController {

    public void criar(Restaurante r) {
        String sql = "INSERT INTO restaurante (nome, telefone, endereco, categoria_id, avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoria_id());
            stmt.setDouble(5, r.getAvaliacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar restaurante: " + e.getMessage());
        }
    }

    public List<Restaurante> listar() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId_restaurante(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoria_id(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar restaurantes: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Restaurante r) {
        String sql = "UPDATE restaurante SET nome=?, telefone=?, endereco=?, categoria_id=?, avaliacao=? WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoria_id());
            stmt.setDouble(5, r.getAvaliacao());
            stmt.setInt(6, r.getId_restaurante());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar restaurante: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar restaurante: " + e.getMessage());
        }
    }
}
