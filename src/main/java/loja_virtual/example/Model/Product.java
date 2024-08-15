package loja_virtual.example.Model;

/**
 *  Classe {@code Product} representa um produto da Loja Virtual
 *  Esta classe encapsula as informações de um produto, incluindo
 *  identificador único, nome, descrição.
 */
public class Product {
    /**
     * Identificador único do produto.
     */
    private int id;
    /**
     * Nome do produto.
     */
    private String nome;
    /**
     * Descrição do produto.
     */
    private String descricao;

    /**
     * Construtor da classe {@code Product} que inicializa seus atributos
     * @param nome Nome do produto
     * @param descricao Descrição do produto
     * @throws IllegalArgumentException Se o nome ou a descrição forem nulos ou vazios.
     */

    public Product(String nome, String descricao) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Construtor padrão da classe {@code Product}.
     * <p>
     *  Este construtor cria uma nova instância de {@code Product} com valores
     *  padrão.
     * </p>
     */
    public Product() {
    }

    /**
     * Obtém o identificador único do produto.
     * @return O identificador único do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica o identificar único do produto.
     * @param id O novo identificador do produto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     * @return O nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica o nome do produto.
     * @param nome O novo nome do produto.
     */
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto.
     * @return A descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Modifica a descrição do produto.
     * @param descricao A nova descrição do produto.
     */
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não pode ser nulo ou vazio.");
        }
        this.descricao = descricao;
    }
}
