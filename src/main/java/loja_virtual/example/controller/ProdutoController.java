package loja_virtual.example.controller;


import loja_virtual.example.DAO.ProdutoDAO;
import loja_virtual.example.Model.Produto;
import loja_virtual.example.dataManager.DataManager;

import java.sql.Connection;
import java.util.List;

/**
 * Esta classe atua como o controlador para operações relacionadas aos produtos,
 * gerenciando a comunicação entre a camada de apresentação e a camada de acesso aos dados.
 */
public class ProdutoController {

	private ProdutoDAO produtoDAO;

	/**
	 * Construtor que configura o controlador de produtos.
	 * <p>
	 * 	Estabelece uma conexão com o banco de dados utilizando o DataManager,
	 * 	e inicializa o objeto ProdutoDAO, passando a conexão como parâmetro
	 * 	para que possa realizar operações no banco de dados.
	 * </p>
	 */
	public ProdutoController() {
		Connection connection = new DataManager().getConnection();
		this.produtoDAO = new ProdutoDAO(connection);
	}
	/**
	 * Remove um produto do banco de dados com base no identificador fornecido.
	 * <p>
	 * Utiliza o método deletar() da classe ProdutoDAO para realizar a exclusão.
	 * </p>
	 *
	 * @param id o identificador do produto a ser excluído.
	 */
	public void deletar(Integer id) {
		produtoDAO.deletar(id);
	}

	/**
	 * Salva um novo produto ou atualiza um produto existente no banco de dados.
	 * <p>
	 * Utiliza o método salvar() da classe ProdutoDAO para persistir o produto.
	 * </p>
	 *
	 * @param produto o objeto Produto a ser salvo ou atualizado.
	 */
	public void salvar(Produto produto) {
		produtoDAO.salvar(produto);
	}

	/**
	 * Lista todos os produtos disponíveis no banco de dados.
	 * <p>
	 * Utiliza o método listar() da classe ProdutoDAO para recuperar todos os produtos.
	 * </p>
	 *
	 * @return uma lista de objetos Produto representando todos os produtos disponíveis.
	 */
	public List<Produto> listar() {
		return produtoDAO.listar();
	}

	/**
	 * Altera as informações de um produto existente no banco de dados.
	 * <p>
	 * Utiliza o método alterar() da classe ProdutoDAO para atualizar o produto com base
	 * nos novos valores fornecidos.
	 * </p>
	 *
	 * @param nome a novo nome do produto.
	 * @param descricao a nova descrição do produto.
	 * @param id o identificador do produto a ser alterado.
	 */
	public void alterar(String nome, String descricao, Integer id) {
		produtoDAO.alterar(nome, descricao, id);
	}
}
