package projetojava.mvc.Repository;

import projetojava.mvc.ConfigBD.ConnectionBD;
import projetojava.mvc.Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public void inserirProduto(Produto produto) {

        String sql = "INSERT INTO Produto VALUES (?, ?, ?, ?)";

        try(Connection conn = ConnectionBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> buscarProdutos() {

        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, categoria, quantidade FROM Produto";

        try(Connection conn = ConnectionBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Integer id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    Integer qtd = rs.getInt("quantidade");

                    produtos.add(new Produto(id, nome, categoria, qtd));
                }
                return produtos;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto buscarProdutoPorNome(String nome) {

        String sql = "SELECT id, nome, categoria, quantidade FROM Produto WHERE nome = ?";

        try(Connection conn = ConnectionBD.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

        try(ResultSet rs = stmt.executeQuery()) {

            if(rs.next()) {
                Integer idQuery = rs.getInt("id");
                String nomeQuery = rs.getString("nome");
                String categoriaQuery = rs.getString("categoria");
                Integer qtdQuery = rs.getInt("quantidade");

                return new Produto(idQuery, nomeQuery, categoriaQuery, qtdQuery);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletarProduto(Integer id) {

        String sql = "DELETE FROM Produto WHERE id = ?";

        try(Connection conn = ConnectionBD.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNome(Integer id, String nome) {

        String sql = "UPDATE Produto SET nome = ? WHERE id = ?";

        try(Connection conn = ConnectionBD.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarQuantidade(Integer id, Integer qtd) {

        String sql = "UPDATE Produto SET quantidade = ? WHERE id = ?";

        try(Connection conn = ConnectionBD.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, qtd);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> buscarProdutosPorCategoria(String categoria) {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM Produto WHERE categoria = ?";

        try(Connection conn = ConnectionBD.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria);

            try(ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Integer id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String cat = rs.getString("categoria");
                    Integer qtd = rs.getInt("quantidade");

                    produtos.add(new Produto(id, nome, cat, qtd));
                }
                return produtos;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto buscarPorId(Integer id) {

        String sql = "SELECT * FROM Produto WHERE id = ?";

        try(Connection conn = ConnectionBD.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    Integer idQuery = rs.getInt("id");
                    String nomeQuery = rs.getString("nome");
                    String categoriaQuery = rs.getString("categoria");
                    Integer qtdQuery = rs.getInt("quantidade");

                    return new Produto(idQuery, nomeQuery, categoriaQuery, qtdQuery);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}