package loja_virtual.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import loja_virtual.example.Model.Categoria;
import loja_virtual.example.Model.Produto;
import loja_virtual.example.query.ProdutosQuery;
/**
 * Classe responsável pelo acesso a dados relacionados aos produtos no banco de dados.
 * Utiliza JDBC para executar consultas SQL e mapear os resultados para objetos Produto.
 */
public class ProdutoDAO {

	private Connection connection;
	/**
	 * Construtor que inicializa a conexão com o banco de dados.
	 *
	 * @param connection a conexão com o banco de dados a ser utilizada pelos métodos desta classe.
	 */
	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Salva um novo produto no banco de dados.
	 * <p>
	 * Executa uma consulta SQL para inserir um novo produto e, em seguida, recupera a chave gerada automaticamente
	 * para definir o ID do produto no objeto Produto.
	 * </p>
	 *
	 * @param produto o objeto Produto a ser salvo no banco de dados.
	 * @throws RuntimeException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public void salvar(Produto produto) {

		try (PreparedStatement pstm = connection.prepareStatement(ProdutosQuery.SALVAR, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	/**
	 * Salva um novo produto no banco de dados com uma categoria associada.
	 * <p>
	 * Executa uma consulta SQL para inserir um produto com um ID de categoria associado e, em seguida, recupera a chave gerada automaticamente
	 * para definir o ID do produto no objeto Produto.
	 * </p>
	 *
	 * @param produto o objeto Produto a ser salvo, incluindo o ID da categoria.
	 * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public void salvarComCategoria(Produto produto) throws SQLException {

		try (PreparedStatement pstm = connection.prepareStatement(ProdutosQuery.SALVAR_COM_CATEGORIA, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setInt(3, produto.getCategoriaId());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}
	}
	/**
	 * Recupera todos os produtos disponíveis no banco de dados.
	 * <p>
	 * Executa uma consulta SQL para listar todos os produtos e mapeia os resultados para uma lista de objetos Produto.
	 * </p>
	 *
	 * @return uma lista contendo todos os produtos.
	 * @throws RuntimeException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public List<Produto> listar(){
		try {
			List<Produto> produtos = new ArrayList<Produto>();

			try (PreparedStatement pstm = connection.prepareStatement(ProdutosQuery.LISTAR)) {
				pstm.execute();
				trasformarResultSetEmProduto(produtos, pstm);
			}
			return produtos;
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}


	}
	/**
	 * Busca produtos associados a uma categoria específica.
	 * <p>
	 * Executa uma consulta SQL que retorna produtos filtrados pelo ID da categoria fornecida e mapeia os resultados
	 * para uma lista de objetos Produto.
	 * </p>
	 *
	 * @param ct a categoria cujos produtos devem ser recuperados.
	 * @return uma lista de produtos associados à categoria fornecida.
	 * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public List<Produto> buscar(Categoria ct) throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		try (PreparedStatement pstm = connection.prepareStatement(ProdutosQuery.BUSCAR)) {
			pstm.setInt(1, ct.getId());
			pstm.execute();

			trasformarResultSetEmProduto(produtos, pstm);
		}
		return produtos;
	}
	/**
	 * Remove um produto do banco de dados com base no identificador fornecido.
	 * <p>
	 * Executa uma consulta SQL para excluir um produto com o ID fornecido.
	 * </p>
	 *
	 * @param id o identificador do produto a ser excluído.
	 * @throws RuntimeException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement(ProdutosQuery.DELETAR)) {
			stm.setInt(1, id);
			stm.execute();
		}catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	/**
	 * Atualiza as informações de um produto existente no banco de dados.
	 * <p>
	 * Executa uma consulta SQL para atualizar o nome e a descrição do produto com o ID fornecido.
	 * </p>
	 *
	 * @param nome o novo nome do produto.
	 * @param descricao a nova descrição do produto.
	 * @param id o identificador do produto a ser atualizado.
	 * @throws RuntimeException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public void alterar(String nome, String descricao, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement(ProdutosQuery.ALTERAR)) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setInt(3, id);
			stm.execute();
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	/**
	 * Converte o ResultSet obtido de uma consulta SQL em uma lista de objetos Produto.
	 * <p>
	 * Este método é chamado internamente para processar o ResultSet e popular a lista de produtos.
	 * </p>
	 *
	 * @param produtos a lista de produtos a ser preenchida com os dados do ResultSet.
	 * @param pstm o PreparedStatement usado para executar a consulta SQL.
	 * @throws SQLException se ocorrer um erro durante a leitura do ResultSet.
	 */
	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

				produtos.add(produto);
			}
		}
	}
}
