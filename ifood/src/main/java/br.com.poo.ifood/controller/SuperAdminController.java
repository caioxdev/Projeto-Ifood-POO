package br.com.poo.ifood.controller;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.SuperAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminController {

    public boolean criar(SuperAdmin s) {
        String sql = "INSERT INTO superadmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNome());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getSenha());
            ps.setString(4, s.getTelefone());
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException ex) {
            // email duplicado
            return false;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public SuperAdmin login(String email, String senha) {
        String sql = "SELECT * FROM superadmin WHERE email = ? AND senha = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SuperAdmin s = new SuperAdmin();
                s.setId(rs.getInt("id_superadmin"));
                s.setNome(rs.getString("nome"));
                s.setEmail(rs.getString("email"));
                s.setSenha(rs.getString("senha"));
                s.setTelefone(rs.getString("telefone"));
                return s;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean deletar(String email, String senha) {
        String sql = "DELETE FROM superadmin WHERE email = ? AND senha = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<SuperAdmin> listar() {
        List<SuperAdmin> lista = new ArrayList<>();
        String sql = "SELECT * FROM superadmin";
        try (Connection conn = Conexao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                SuperAdmin s = new SuperAdmin();
                s.setId(rs.getInt("id_superadmin"));
                s.setNome(rs.getString("nome"));
                s.setEmail(rs.getString("email"));
                s.setTelefone(rs.getString("telefone"));
                lista.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}
