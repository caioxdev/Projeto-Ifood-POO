package br.com.poo.ifood.controller;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.SuperAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminController {

    public void criar(SuperAdmin s) {
        String sql = "INSERT INTO superadmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getSenha());
            stmt.setString(4, s.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar superadmin: " + e.getMessage());
        }
    }

    public List<SuperAdmin> listar() {
        List<SuperAdmin> lista = new ArrayList<>();
        String sql = "SELECT * FROM superadmin";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SuperAdmin s = new SuperAdmin();
                s.setId_superadmin(rs.getInt("id_superadmin"));
                s.setNome(rs.getString("nome"));
                s.setEmail(rs.getString("email"));
                s.setSenha(rs.getString("senha"));
                s.setTelefone(rs.getString("telefone"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar superadmins: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(SuperAdmin s) {
        String sql = "UPDATE superadmin SET nome=?, email=?, senha=?, telefone=? WHERE id_superadmin=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getSenha());
            stmt.setString(4, s.getTelefone());
            stmt.setInt(5, s.getId_superadmin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar superadmin: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM superadmin WHERE id_superadmin=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar superadmin: " + e.getMessage());
        }
    }
}
