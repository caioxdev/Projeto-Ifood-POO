package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Restaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    public boolean create(Restaurante r) {
        String sql = "INSERT INTO restaurante (nome, telefone, endereco, categoria_id, avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoria_id());
            stmt.setDouble(5, r.getAvaliacao());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) return false;

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                r.setId(generatedKeys.getInt(1));
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao criar restaurante: " + e.getMessage());
            return false;
        }
    }

    public List<Restaurante> findAll() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId(rs.getInt("id_restaurante"));
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

    public Restaurante findById(int id) {
        String sql = "SELECT * FROM restaurante WHERE id_restaurante = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoria_id(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
                return r;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar restaurante: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Restaurante r) {
        String sql = "UPDATE restaurante SET nome=?, telefone=?, endereco=?, categoria_id=?, avaliacao=? WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoria_id());
            stmt.setDouble(5, r.getAvaliacao());
            stmt.setInt(6, r.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar restaurante: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar restaurante: " + e.getMessage());
            return false;
        }
    }
}
