package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Categoria;
import java.sql.*;
import java.util.*;

public class CategoriaDAO {

    public void create(Categoria categoria) {
        String sql = "INSERT INTO Categoria (nome, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Categoria> readAll() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                categorias.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categorias;
    }

    public void update(Categoria categoria) {
        String sql = "UPDATE Categoria SET nome=?, descricao=? WHERE id_categoria=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getId_categoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Categoria WHERE id_categoria=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
