package loja_virtual.example.controller;

import loja_virtual.example.DAO.CategoriaDAO;
import loja_virtual.example.Model.Categoria;
import loja_virtual.example.dataManager.DataManager;

import java.sql.Connection;
import java.util.List;


public class CategoriaController {
	private CategoriaDAO categoriaDAO;

	public CategoriaController() {
		Connection connection = new DataManager().getConnection();
		this.categoriaDAO = new CategoriaDAO(connection);
	}

	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}
	public List<Categoria> listarComCategoria() {
		return categoriaDAO.listarComProduto();
	}
}
