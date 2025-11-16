package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.SuperAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperAdminDAO {

    public boolean cadastrar(SuperAdmin sa) {
        String sql = "INSERT INTO superadmin (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sa.getNome());
            stmt.setString(2, sa.getEmail());
            stmt.setString(3, sa.getSenha());
            stmt.setString(4, sa.getTelefone());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    sa.setId(rs.getInt("id_superadmin"));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar superadmin: " + e.getMessage());
        }
        return false;
    }

    public SuperAdmin buscarPorId(int id) {
        String sql = "SELECT * FROM superadmin WHERE id_superadmin = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

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
            System.out.println("Erro ao buscar superadmin: " + e.getMessage());
        }
        return null;
    }
}
