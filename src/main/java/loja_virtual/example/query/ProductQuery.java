package loja_virtual.example.query;

/**
 *  A classe {@code ProductQuery} contém os comandos SQL utilizadas para
 *  operações de CRUD (Create, Read, Update, Delete) na tabela {@code produtos}.
 */
public class ProductQuery {
    /**
     * Comando SQL para listar todos os produtos da tabela {@code produtos}.
     */
    public static final String LIST = "SELECT * FROM produtos";
    /**
     * Comando SQL para inserir um novo produto na tabela {@code produtos}.
     */
    public static final String INSERT = "INSERT INTO produtos (nome, descricao) VALUES (?, ?)";
    /**
     * Comando SQL para excluir um produto da tabela {@code produtos} com base
     * em seu identificador único {@code id}.
     */
    public static final String DELETE = "DELETE FROM produtos WHERE id = ?";
    /**
     * Comando SQL para selecionar um produto da tabela {@code produtos} com
     * base em seu identificador único {@code id}.
     */
    public static final String SELECT_BY_ID = "SELECT * FROM produtos WHERE id = ?";
}
