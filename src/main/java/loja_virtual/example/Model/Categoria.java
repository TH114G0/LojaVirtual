package loja_virtual.example.Model;

import java.util.ArrayList;
import java.util.List;
/**
 * Representa uma categoria de produtos em uma loja.
 * Cada categoria possui um identificador, um nome e uma lista de produtos associados.
 */
public class Categoria {

	private Integer id;
	private String nome;
	private List<Produto> produtos = new ArrayList<Produto>();

	/**
	 * Construtor que inicializa uma nova categoria com um ID e um nome.
	 *
	 * @param id o identificador único da categoria.
	 * @param nome o nome da categoria.
	 */
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/**
	 * Obtém o nome da categoria.
	 *
	 * @return o nome da categoria.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Obtém o identificador da categoria.
	 *
	 * @return o identificador da categoria.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Adiciona um produto à lista de produtos desta categoria.
	 *
	 * @param produto o produto a ser adicionado à categoria.
	 */
	public void adicionar(Produto produto) {
		produtos.add(produto);
	}

	/**
	 * Obtém a lista de produtos associados a esta categoria.
	 *
	 * @return uma lista contendo os produtos da categoria.
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * Retorna uma representação em string do nome da categoria.
	 *
	 * @return o nome da categoria.
	 */
	@Override
	public String toString() {
		return this.nome;
	}
}
