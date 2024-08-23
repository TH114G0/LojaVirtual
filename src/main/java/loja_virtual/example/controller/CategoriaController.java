package loja_virtual.example.controller;

import loja_virtual.example.DAO.CategoriaDAO;
import loja_virtual.example.Model.Categoria;
import loja_virtual.example.dataManager.DataManager;

import java.sql.Connection;
import java.util.List;

/**
 * Esta classe atua como o controlador para operações relacionadas às categorias,
 * gerenciando a comunicação entre a camada de apresentação e a camada de acesso aos dados.
 */
public class CategoriaController {
	private CategoriaDAO categoriaDAO;

	/**
	 * Construtor que configura o controlador de categorias.
	 * <p>
	 * 	Estabelece uma conexão com o banco de dados utilizando o DataManager,
	 * 	e inicializa o objeto CategoriaDAO, passando a conexão como parâmetro
	 * 	para que possa realizar operações no banco de dados.
	 * </p>
	 */
	public CategoriaController() {
		Connection connection = new DataManager().getConnection();
		this.categoriaDAO = new CategoriaDAO(connection);
	}

	/**
	 * Retorna uma lista de todas as categorias disponíveis no banco de dados.
	 * Utiliza o método listar() da classe CategoriaDAO para buscar todas as categorias.
	 * @return uma lista de objetos Categoria representando todas as categorias disponíveis.
	 */
	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}

	/**
	 * Retorna uma lista de todas as categorias, incluindo os produtos associados a cada uma.
	 * Utiliza o método listarComProduto() da classe CategoriaDAO para buscar as categorias
	 * juntamente com seus respectivos produtos.
	 *
	 * @return uma lista de objetos Categoria, cada um contendo a lista de produtos associados.
	 */
	public List<Categoria> listarComCategoria() {
		return categoriaDAO.listarComProduto();
	}
}
