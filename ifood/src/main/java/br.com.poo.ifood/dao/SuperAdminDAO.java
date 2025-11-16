package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.SuperAdmin;

import java.sql.*;

public class SuperAdminDAO {

    public boolean cadastrar(SuperAdmin s) {
        String sql = "INSERT INTO superadmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getNome());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getSenha());
            ps.setString(4, s.getTelefone());

            int linhas = ps.executeUpdate();
            if(linhas == 0) return false;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) s.setId_admin(rs.getInt(1));
            }

            return true;

        } catch(SQLException e) {
            System.out.println("Erro ao cadastrar SuperAdmin: " + e.getMessage());
            return false;
        }
    }

    public SuperAdmin buscarPorEmailESenha(String email, String senha) {
        String sql = "SELECT * FROM superadmin WHERE email=? AND senha=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    SuperAdmin s = new SuperAdmin();
                    s.setId_admin(rs.getInt("id_superadmin"));
                    s.setNome(rs.getString("nome"));
                    s.setEmail(rs.getString("email"));
                    s.setSenha(rs.getString("senha"));
                    s.setTelefone(rs.getString("telefone"));
                    return s;
                }
            }

        } catch(SQLException e) {
            System.out.println("Erro ao buscar SuperAdmin: " + e.getMessage());
        }
        return null;
    }
}
