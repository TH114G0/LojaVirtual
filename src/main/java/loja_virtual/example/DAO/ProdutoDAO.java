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

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

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

	public List<Produto> buscar(Categoria ct) throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		try (PreparedStatement pstm = connection.prepareStatement(ProdutosQuery.BUSCAR)) {
			pstm.setInt(1, ct.getId());
			pstm.execute();

			trasformarResultSetEmProduto(produtos, pstm);
		}
		return produtos;
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement(ProdutosQuery.DELETAR)) {
			stm.setInt(1, id);
			stm.execute();
		}catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

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

	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

				produtos.add(produto);
			}
		}
	}
}
