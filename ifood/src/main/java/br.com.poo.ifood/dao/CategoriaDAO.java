package br.com.poo.ifood.dao;

import br.com.poo.ifood.model.Categoria;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public boolean cadastrar(Categoria c) {
        String sql = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    c.setId(rs.getInt(1)); // corrigido
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id_categoria")); // corrigido
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                categorias.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        return categorias;
    }

    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id_categoria")); // corrigido
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                return c;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizar(Categoria c) {
        String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            stmt.setInt(3, c.getId()); // corrigido

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
            return false;
        }
    }
}
