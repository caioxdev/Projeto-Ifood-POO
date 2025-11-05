package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.SuperAdmin;
import java.sql.*;
import java.util.*;

public class SuperAdminDAO {

    public void create(SuperAdmin sa) {
        String sql = "INSERT INTO SuperAdmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sa.getNome());
            stmt.setString(2, sa.getEmail());
            stmt.setString(3, sa.getSenha());
            stmt.setString(4, sa.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<SuperAdmin> readAll() {
        List<SuperAdmin> admins = new ArrayList<>();
        String sql = "SELECT * FROM SuperAdmin";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SuperAdmin s = new SuperAdmin();
                s.setId_superadmin(rs.getInt("id_superadmin"));
                s.setNome(rs.getString("nome"));
                s.setEmail(rs.getString("email"));
                s.setSenha(rs.getString("senha"));
                s.setTelefone(rs.getString("telefone"));
                admins.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return admins;
    }

    public void update(SuperAdmin sa) {
        String sql = "UPDATE SuperAdmin SET nome=?, email=?, senha=?, telefone=? WHERE id_superadmin=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sa.getNome());
            stmt.setString(2, sa.getEmail());
            stmt.setString(3, sa.getSenha());
            stmt.setString(4, sa.getTelefone());
            stmt.setInt(5, sa.getId_superadmin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM SuperAdmin WHERE id_superadmin=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
