package loja_virtual.example.query;

public class CategoriaQuery {
    public static final String LISTAR = "SELECT ID, NOME FROM CATEGORIA";
    public static final String LISTAR_COM_PRODUTO = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO " +
            "FROM CATEGORIA C " + "INNER JOIN PRODUTOS P ON C.ID = P.CATEGORIA_ID";
}
