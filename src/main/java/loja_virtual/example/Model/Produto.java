package loja_virtual.example.Model;
/**
 * Representa um produto em uma loja. Cada produto possui um identificador, nome, descrição
 * e um identificador de categoria ao qual pertence.
 */
public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Integer categoriaId;

	/**
	 * Construtor que inicializa um novo produto com nome e descrição.
	 *
	 * @param nome o nome do produto.
	 * @param descricao a descrição do produto.
	 */
	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * Construtor que inicializa um novo produto com ID, nome e descrição.
	 *
	 * @param id o identificador único do produto.
	 * @param nome o nome do produto.
	 * @param descricao a descrição do produto.
	 */
	public Produto(Integer id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * Obtém o identificador do produto.
	 *
	 * @return o identificador do produto.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Define o identificador do produto.
	 *
	 * @param id o identificador do produto a ser definido.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Obtém o nome do produto.
	 *
	 * @return o nome do produto.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Obtém a descrição do produto.
	 *
	 * @return a descrição do produto.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Obtém o identificador da categoria à qual o produto pertence.
	 *
	 * @return o identificador da categoria.
	 */
	public Integer getCategoriaId() {
		return categoriaId;
	}

	/**
	 * Define o identificador da categoria à qual o produto pertence.
	 *
	 * @param categoriaId o identificador da categoria a ser definido.
	 */
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Retorna uma representação em string do produto, incluindo ID, nome e descrição.
	 *
	 * @return uma string formatada contendo informações sobre o produto.
	 */
	@Override
	public String toString() {
		return String.format("O produto �: %d, %s, %s", this.id, this.nome, this.descricao);
	}
}
