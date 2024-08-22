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


public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

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
