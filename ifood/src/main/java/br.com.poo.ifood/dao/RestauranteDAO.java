package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Restaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    public boolean cadastrar(Restaurante r) {
        String sql = "INSERT INTO restaurante (nome, telefone, endereco, categoria_id, avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getNome());
            ps.setString(2, r.getTelefone());
            ps.setString(3, r.getEndereco());
            ps.setInt(4, r.getCategoria_id());
            ps.setDouble(5, r.getAvaliacao());
            int linhas = ps.executeUpdate();
            if (linhas == 0) return false;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId_restaurante(rs.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar restaurante: " + e.getMessage());
            return false;
        }
    }

    public List<Restaurante> listar() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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

    public Restaurante buscarPorId(int id) {
        String sql = "SELECT * FROM restaurante WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Restaurante r = new Restaurante();
                    r.setId_restaurante(rs.getInt("id_restaurante"));
                    r.setNome(rs.getString("nome"));
                    r.setTelefone(rs.getString("telefone"));
                    r.setEndereco(rs.getString("endereco"));
                    r.setCategoria_id(rs.getInt("categoria_id"));
                    r.setAvaliacao(rs.getDouble("avaliacao"));
                    return r;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar restaurante por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizar(Restaurante r) {
        String sql = "UPDATE restaurante SET nome=?, telefone=?, endereco=?, categoria_id=?, avaliacao=? WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getNome());
            ps.setString(2, r.getTelefone());
            ps.setString(3, r.getEndereco());
            ps.setInt(4, r.getCategoria_id());
            ps.setDouble(5, r.getAvaliacao());
            ps.setInt(6, r.getId_restaurante());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar restaurante: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover restaurante: " + e.getMessage());
            return false;
        }
    }
}
