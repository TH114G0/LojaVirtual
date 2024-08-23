package loja_virtual.example.query;

/**
 * Contém as consultas SQL utilizadas para acessar e manipular dados
 * relacionados aos produtos no banco de dados.
 */
public class ProdutosQuery {

    /**
     * Consulta SQL para inserir um novo produto na tabela PRODUTOS.
     * <p>
     * Esta consulta insere o nome e a descrição do produto.
     * </p>
     */
    public static final String SALVAR = "INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES (?, ?)";

    /**
     * Consulta SQL para inserir um novo produto na tabela PRODUTOS com uma categoria associada.
     * <p>
     * Esta consulta insere o nome, a descrição e o ID da categoria do produto.
     * </p>
     */
    public static final String SALVAR_COM_CATEGORIA = "INSERT INTO PRODUTOS (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

    /**
     * Consulta SQL para listar todos os produtos na tabela PRODUTOS.
     * <p>
     * Esta consulta retorna o ID, nome e descrição de cada produto.
     * </p>
     */
    public static final String LISTAR = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS";

    /**
     * Consulta SQL para buscar produtos que pertencem a uma categoria específica.
     * <p>
     * Esta consulta retorna o ID, nome e descrição dos produtos onde o ID da categoria é igual ao parâmetro fornecido.
     * </p>
     */
    public static final String BUSCAR = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS WHERE CATEGORIA_ID = ?";

    /**
     * Consulta SQL para deletar um produto da tabela PRODUTOS.
     * <p>
     * Esta consulta remove o produto cujo ID corresponde ao parâmetro fornecido.
     * </p>
     */
    public static final String DELETAR = "DELETE FROM PRODUTOS WHERE ID = ?";

    /**
     * Consulta SQL para atualizar os detalhes de um produto existente na tabela PRODUTOS.
     * <p>
     * Esta consulta modifica o nome e a descrição do produto cujo ID corresponde ao parâmetro fornecido.
     * </p>
     */
    public static final String ALTERAR = "UPDATE PRODUTOS P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?";
}
