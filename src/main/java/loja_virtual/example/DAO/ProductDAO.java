package loja_virtual.example.DAO;

import loja_virtual.example.Model.Product;
import loja_virtual.example.dataManager.DataManager;
import loja_virtual.example.query.ProductQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A classe {@code ProductDAO} é responsável por realizar operações de CRUD
 * relacionadas à entidade {@link Product} no banco de dados.
 * <p>
 * Esta classe utiliza SQL para interagir com o banco de dados, permitindo que
 * produtos sejam adicionados, recuperados, atualizados e excluídos.
 * </p>
 */
public class ProductDAO {

    /**
     *  Insere um novo produto no banco de dados.
     *  Se ocorrer algum erro durante a execução,
     *  a transação será revertida (rollback), garantindo a consistência dos dados.
     * @param product O objeto {@link Product} a ser inserido no banco de dados.
     * @throws SQLException Se ocorrer um erro durante a inserção do produto ou na conexão com o banco de dados.
     */
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

    /**
     * Deleta um produto do banco de dados com base no seu identificador.
     *  Se ocorrer algum erro durante a execução,
     *  a transação será revertida (rollback), garantindo a consistência dos dados.
     * @param id O identificador único do produto que será deletado.
     * @throws SQLException  Se ocorrer um erro durante a deleção do produto ou na conexão com o banco de dados.
     */
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

    /**
     * Seleciona e exibe um produto do banco de dados com base no seu identificador.
     * Se o produto for encontrado, seus detalhes (ID, nome e descrição) são exibidos.
     * Caso contrário, é exibida uma mensagem indicando que nenhum produto foi encontrado com o ID fornecido.
     * @param id O identificador único do produto a ser selecionado.
     * @throws SQLException Se ocorrer um erro durante a seleção do produto ou na conexão com o banco de dados.
     */
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

    /**
     *  Lista todos os produtos armazenados no banco de dados.
     *  <p>Este método executa uma consulta para obter todos os produtos da tabela de produtos.
     *  Se não houver produtos na tabela, uma mensagem será exibida indicando que nenhum produto foi encontrado.
     *  Caso contrário, os detalhes de cada produto (ID, nome e descrição) serão exibidos.</p>
     * @throws SQLException Se ocorrer um erro durante a execução da consulta ou na conexão com o banco de dados.
     */
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
