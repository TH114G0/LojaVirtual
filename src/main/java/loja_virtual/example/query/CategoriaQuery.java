package loja_virtual.example.query;

/**
 * Contém as consultas SQL utilizadas para acessar e manipular dados
 * relacionados às categorias no banco de dados.
 */
public class CategoriaQuery {

    /**
     * Consulta SQL para listar todas as categorias, retornando o ID e o nome de cada categoria.
     */
    public static final String LISTAR = "SELECT ID, NOME FROM CATEGORIA";

    /**
     * Consulta SQL para listar todas as categorias juntamente com seus produtos.
     * <p>
     * Esta consulta realiza um join entre as tabelas CATEGORIA e PRODUTOS, retornando
     * o ID e nome da categoria, bem como o ID, nome e descrição dos produtos associados.
     * </p>
     */
    public static final String LISTAR_COM_PRODUTO = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO " +
            "FROM CATEGORIA C " +
            "INNER JOIN PRODUTOS P ON C.ID = P.CATEGORIA_ID";
}
