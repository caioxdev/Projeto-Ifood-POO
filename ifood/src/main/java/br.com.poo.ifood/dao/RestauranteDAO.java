package br.com.poo.ifood.dao;

import br.com.poo.ifood.database.Conexao;
import br.com.poo.ifood.model.Restaurante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    // --- CADASTRAR RESTAURANTE ---
    public boolean cadastrar(Restaurante r) {
        String sql = """
            INSERT INTO restaurante (nome, telefone, endereco, categoria_id, avaliacao, ativo)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoria_id());
            stmt.setDouble(5, r.getAvaliacao());
            stmt.setBoolean(6, r.isAtivo());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("ERRO ao cadastrar restaurante: " + e.getMessage());
            return false;
        }
    }

    // --- LISTAR TODOS ---
    public List<Restaurante> listar() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Restaurante r = new Restaurante(
                        rs.getInt("id_restaurante"),
                        rs.getString("nome"),
                        rs.getString("telefone")
                );
                lista.add(r);
            }

        } catch (Exception e) {
            System.out.println("ERRO ao listar restaurantes: " + e.getMessage());
        }

        return lista;
    }

    // --- BUSCAR POR ID ---
    public Restaurante buscarPorId(int id) {
        String sql = "SELECT * FROM restaurante WHERE id_restaurante = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Restaurante(
                        rs.getInt("id_restaurante"),
                        rs.getString("nome"),
                        rs.getString("telefone")
                );
            }

        } catch (Exception e) {
            System.out.println("ERRO ao buscar restaurante: " + e.getMessage());
        }

        return null;
    }

    // --- ATUALIZAR RESTAURANTE ---
    public boolean atualizar(Restaurante r) {
        String sql = """
            UPDATE restaurante 
            SET nome = ?, telefone = ?, endereco = ?, categoria_id = ?, avaliacao = ?, ativo = ?
            WHERE id_restaurante = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTelefone());
            stmt.setString(3, r.getEndereco());
            stmt.setInt(4, r.getCategoria_id());
            stmt.setDouble(5, r.getAvaliacao());
            stmt.setBoolean(6, r.isAtivo());
            stmt.setInt(7, r.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("ERRO ao atualizar restaurante: " + e.getMessage());
            return false;
        }
    }

    // --- REMOVER RESTAURANTE ---
    public boolean remover(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("ERRO ao remover restaurante: " + e.getMessage());
            return false;
        }
    }
}
