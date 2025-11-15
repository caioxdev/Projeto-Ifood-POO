package br.com.poo.ifood.controller;

import br.com.poo.ifood.model.Restaurante;
import br.com.poo.ifood.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteController {

    // Cadastrar restaurante
    public boolean cadastrar(String nome, String telefone, String endereco, int categoriaId, double avaliacao) {
        String sql = "INSERT INTO restaurante (nome, telefone, endereco, categoria_id, avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, telefone);
            ps.setString(3, endereco);
            ps.setInt(4, categoriaId);
            ps.setDouble(5, avaliacao);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos os restaurantes
    public List<Restaurante> listar() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection conn = Conexao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoria_id(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
                r.setAtivo(rs.getBoolean("ativo"));
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar restaurante por ID
    public Restaurante buscarPorId(int id) {
        String sql = "SELECT * FROM restaurante WHERE id_restaurante = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId(rs.getInt("id_restaurante"));
                r.setNome(rs.getString("nome"));
                r.setTelefone(rs.getString("telefone"));
                r.setEndereco(rs.getString("endereco"));
                r.setCategoria_id(rs.getInt("categoria_id"));
                r.setAvaliacao(rs.getDouble("avaliacao"));
                r.setAtivo(rs.getBoolean("ativo"));
                return r;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Atualizar restaurante
    public boolean atualizar(Restaurante r) {
        String sql = "UPDATE restaurante SET nome = ?, telefone = ?, endereco = ?, categoria_id = ?, avaliacao = ?, ativo = ? WHERE id_restaurante = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getNome());
            ps.setString(2, r.getTelefone());
            ps.setString(3, r.getEndereco());
            ps.setInt(4, r.getCategoria_id());
            ps.setDouble(5, r.getAvaliacao());
            ps.setBoolean(6, r.isAtivo());
            ps.setInt(7, r.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remover restaurante
    public boolean remover(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante = ?";
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
