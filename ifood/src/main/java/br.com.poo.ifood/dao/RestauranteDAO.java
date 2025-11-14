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
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoriaId());
            stmt.setDouble(5, r.getAvaliacao());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("RestauranteDAO.create: " + e.getMessage());
            return false;
        }
    }

    public List<Restaurante> findAll() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoriaId(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
                lista.add(r);
            }

        } catch (Exception e) {
            System.out.println("RestauranteDAO.findAll: " + e.getMessage());
        }
        return lista;
    }

    public Restaurante findById(int id) {
        String sql = "SELECT * FROM restaurante WHERE id_restaurante = ?";
        Restaurante r = null;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                r = new Restaurante();
                r.setId(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoriaId(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
            }

        } catch (Exception e) {
            System.out.println("RestauranteDAO.findById: " + e.getMessage());
        }

        return r;
    }

    public boolean update(Restaurante r) {
        String sql = "UPDATE restaurante SET nome=?, telefone=?, endereco=?, categoria_id=?, avaliacao=? WHERE id_restaurante=?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoriaId());
            stmt.setDouble(5, r.getAvaliacao());
            stmt.setInt(6, r.getId());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("RestauranteDAO.update: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("RestauranteDAO.delete: " + e.getMessage());
            return false;
        }
    }
}
