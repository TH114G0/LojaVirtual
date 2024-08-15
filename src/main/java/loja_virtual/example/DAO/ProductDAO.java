package loja_virtual.example.DAO;

import loja_virtual.example.Model.Product;
import loja_virtual.example.dataManager.DataManager;
import loja_virtual.example.query.ProductQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public static void insert(Product product) throws SQLException {
        try(Connection connection = new DataManager().getConnection()) {
            connection.setAutoCommit(false);

            try(PreparedStatement pstm = connection.prepareStatement(ProductQuery.INSERT)) {

                pstm.setString(1, product.getNome());
                pstm.setString(2, product.getDescricao());

                pstm.executeUpdate();
                System.out.println("Produto inserido com SUCESSO!");
                connection.commit();
            }catch (SQLException ex) {
                connection.rollback();
                throw new SQLException("Falha ao inserir produto! Transação revertida.",ex);
            }
        }catch (SQLException ex) {
            throw new SQLException("A conexão com o banco de dados falhou.",ex);
        }
    }

    public static void delete(int id) throws SQLException {
        try(Connection connection = new DataManager().getConnection()) {
            connection.setAutoCommit(false);
            try(PreparedStatement pstm = connection.prepareStatement(ProductQuery.DELETE)) {
                pstm.setInt(1, id);
                pstm.executeUpdate();
                System.out.println("Produto DELETADO com SUCESSO!");
                connection.commit();
            }catch (SQLException ex) {
                connection.rollback();
                throw new SQLException("Falha ao deletar produto! Transação revertida.");
            }
        }catch (SQLException ex) {
            throw new SQLException("A conexão com o banco de dados falhou.",ex);
        }
    }

    public static void selectById(int id) throws SQLException {
        Product product = null;
        try(Connection connection = new DataManager().getConnection()) {
            try(PreparedStatement pstm = connection.prepareStatement(ProductQuery.SELECT_BY_ID)) {
                pstm.setInt(1, id);
                try(ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        product = new Product();
                        product.setId(rst.getInt("id"));
                        product.setNome(rst.getString("nome"));
                        product.setDescricao(rst.getString("descricao"));

                        System.out.println("ID: " + product.getId());
                        System.out.println("NOME: " + product.getNome());
                        System.out.println("DESCRIÇÃO: " + product.getDescricao());
                    }else {
                        System.out.println("Nenhum produto encontrado com o ID: " + id);
                    }
                }
            }catch (SQLException ex) {
                throw new SQLException("Falha ao selecionar produto por ID.",ex);
            }
        }catch (SQLException ex) {
            throw new SQLException("A conexão com o banco de dados falhou.",ex);
        }
    }

    public static void AllProduct() throws SQLException {
        try(Connection connection = new DataManager().getConnection()) {
            try(PreparedStatement pstm = connection.prepareStatement(ProductQuery.LIST)) {
                try(ResultSet rst = pstm.executeQuery()) {

                    if (!rst.isBeforeFirst()) {
                        System.out.println("Nenhum produto encontrado.");
                        return;
                    }

                    while (rst.next()) {
                        Product product = new Product();
                        product.setId(rst.getInt("id"));
                        product.setNome(rst.getString("nome"));
                        product.setDescricao(rst.getString("descricao"));

                        System.out.println("ID: " + product.getId());
                        System.out.println("NOME: " + product.getNome());
                        System.out.println("DESCRIÇÃO: " + product.getDescricao());
                    }
                }
            }catch (SQLException ex) {
                throw new SQLException("Falha ao selecionar todos os produtos.", ex);
            }
        }catch (SQLException ex) {
            throw new SQLException("A conexão com o banco de dados falhou.", ex);
        }
    }
}
