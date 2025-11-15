package br.com.poo.ifood.controller;

import br.com.poo.ifood.model.SuperAdmin;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminController {

    // Cadastrar SuperAdmin
    public boolean cadastrar(SuperAdmin sa) {
        String sql = "INSERT INTO superadmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sa.getNome());
            ps.setString(2, sa.getEmail());
            ps.setString(3, sa.getSenha());
            ps.setString(4, sa.getTelefone());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login de SuperAdmin
    public SuperAdmin login(String email, String senha) {
        String sql = "SELECT * FROM superadmin WHERE email = ? AND senha = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SuperAdmin sa = new SuperAdmin();
                sa.setId(rs.getInt("id_superadmin"));
                sa.setNome(rs.getString("nome"));
                sa.setEmail(rs.getString("email"));
                sa.setSenha(rs.getString("senha"));
                sa.setTelefone(rs.getString("telefone"));
                return sa;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Listar todos os SuperAdmins
    public List<SuperAdmin> listar() {
        List<SuperAdmin> lista = new ArrayList<>();
        String sql = "SELECT * FROM superadmin";

        try (Connection conn = Conexao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                SuperAdmin sa = new SuperAdmin();
                sa.setId(rs.getInt("id_superadmin"));
                sa.setNome(rs.getString("nome"));
                sa.setEmail(rs.getString("email"));
                sa.setSenha(rs.getString("senha"));
                sa.setTelefone(rs.getString("telefone"));
                lista.add(sa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar SuperAdmin por ID
    public SuperAdmin buscarPorId(int id) {
        String sql = "SELECT * FROM superadmin WHERE id_superadmin = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SuperAdmin sa = new SuperAdmin();
                sa.setId(rs.getInt("id_superadmin"));
                sa.setNome(rs.getString("nome"));
                sa.setEmail(rs.getString("email"));
                sa.setSenha(rs.getString("senha"));
                sa.setTelefone(rs.getString("telefone"));
                return sa;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Remover SuperAdmin
    public boolean remover(int id) {
        String sql = "DELETE FROM superadmin WHERE id_superadmin = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
