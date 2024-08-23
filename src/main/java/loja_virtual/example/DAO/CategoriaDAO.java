package loja_virtual.example.DAO;

import loja_virtual.example.Model.Categoria;
import loja_virtual.example.Model.Produto;
import loja_virtual.example.query.CategoriaQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo acesso a dados relacionados às categorias no banco de dados.
 * Utiliza JDBC para executar consultas SQL e mapear os resultados para objetos Categoria e Produto.
 */
public class CategoriaDAO {

	private Connection connection;

	/**
	 * Construtor que inicializa a conexão com o banco de dados.
	 *
	 * @param connection a conexão com o banco de dados a ser utilizada pelos métodos desta classe.
	 */
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Recupera todas as categorias do banco de dados.
	 * <p>
	 * Executa uma consulta SQL para listar todas as categorias e mapeia os resultados para uma lista de objetos Categoria.
	 * </p>
	 *
	 * @return uma lista contendo todas as categorias.
	 * @throws RuntimeException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public List<Categoria> listar() {
		try {
			List<Categoria> categorias = new ArrayList<>();

			try (PreparedStatement pstm = connection.prepareStatement(CategoriaQuery.LISTAR)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));

						categorias.add(categoria);
					}
				}
			}
			return categorias;
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Recupera todas as categorias juntamente com seus produtos associados.
	 * <p>
	 * Executa uma consulta SQL que retorna categorias e produtos. Mapeia os resultados para uma lista de objetos Categoria,
	 * e adiciona produtos a cada categoria conforme necessário.
	 * </p>
	 *
	 * @return uma lista de categorias, cada uma contendo seus produtos associados.
	 * @throws RuntimeException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public List<Categoria> listarComProduto() {
		Categoria ultima = null;
		try {
			List<Categoria> categorias = new ArrayList<>();

			try (PreparedStatement pstm = connection.prepareStatement(CategoriaQuery.LISTAR_COM_PRODUTO)) {
				pstm.execute();
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
							Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));

							categorias.add(categoria);
							ultima = categoria;
						}
						Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
						ultima.adicionar(produto);
					}
				}
				return categorias;
			}
		}catch (SQLException ex) {
			throw new RuntimeException();
		}
	}
}
