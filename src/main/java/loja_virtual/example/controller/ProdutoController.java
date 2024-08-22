package loja_virtual.example.controller;


import loja_virtual.example.DAO.ProdutoDAO;
import loja_virtual.example.Model.Produto;
import loja_virtual.example.dataManager.DataManager;

import java.sql.Connection;
import java.util.List;

public class ProdutoController {

	private ProdutoDAO produtoDAO;

	public ProdutoController() {
		Connection connection = new DataManager().getConnection();
		this.produtoDAO = new ProdutoDAO(connection);
	}

	public void deletar(Integer id) {
		produtoDAO.deletar(id);
	}

	public void salvar(Produto produto) {
		produtoDAO.salvar(produto);
	}

	public List<Produto> listar() {
		return produtoDAO.listar();
	}

	public void alterar(String nome, String descricao, Integer id) {
		produtoDAO.alterar(nome, descricao, id);
	}
}
