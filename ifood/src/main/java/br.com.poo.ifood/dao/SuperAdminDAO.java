package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.SuperAdmin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminDAO {

    public void create(SuperAdmin s) {
        String sql = "INSERT INTO superadmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getNome());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getSenha());
            ps.setString(4, s.getTelefone());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("SuperAdminDAO.create: " + e.getMessage());
        }
    }

    public SuperAdmin findById(int id) {
        String sql = "SELECT * FROM superadmin WHERE id_superadmin = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SuperAdmin s = new SuperAdmin();
                    s.setId(rs.getInt("id_superadmin"));
                    s.setNome(rs.getString("nome"));
                    s.setEmail(rs.getString("email"));
                    s.setSenha(rs.getString("senha"));
                    s.setTelefone(rs.getString("telefone"));
                    return s;
                }
            }
        } catch (SQLException e) {
            System.out.println("SuperAdminDAO.findById: " + e.getMessage());
        }
        return null;
    }

    public SuperAdmin findByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM superadmin WHERE email = ? AND senha = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SuperAdmin s = new SuperAdmin();
                    s.setId(rs.getInt("id_superadmin"));
                    s.setNome(rs.getString("nome"));
                    s.setEmail(rs.getString("email"));
                    s.setSenha(rs.getString("senha"));
                    s.setTelefone(rs.getString("telefone"));
                    return s;
                }
            }
        } catch (SQLException e) {
            System.out.println("SuperAdminDAO.findByEmailAndSenha: " + e.getMessage());
        }
        return null;
    }

    public List<SuperAdmin> findAll() {
        List<SuperAdmin> lista = new ArrayList<>();
        String sql = "SELECT * FROM superadmin";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SuperAdmin s = new SuperAdmin();
                s.setId(rs.getInt("id_superadmin"));
                s.setNome(rs.getString("nome"));
                s.setEmail(rs.getString("email"));
                s.setSenha(rs.getString("senha"));
                s.setTelefone(rs.getString("telefone"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("SuperAdminDAO.findAll: " + e.getMessage());
        }
        return lista;
    }

    public void update(SuperAdmin s) {
        String sql = "UPDATE superadmin SET nome=?, email=?, senha=?, telefone=? WHERE id_superadmin = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNome());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getSenha());
            ps.setString(4, s.getTelefone());
            ps.setInt(5, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SuperAdminDAO.update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM superadmin WHERE id_superadmin = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SuperAdminDAO.delete: " + e.getMessage());
        }
    }
}