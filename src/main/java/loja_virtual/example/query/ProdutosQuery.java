package loja_virtual.example.query;

public class ProdutosQuery {
    public static final String SALVAR = "INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES (?, ?)";
    public static final String SALVAR_COM_CATEGORIA = "INSERT INTO PRODUTOS (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";
    public static final String LISTAR = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS";
    public static final String BUSCAR = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS WHERE CATEGORIA_ID = ?";
    public static final String DELETAR = "DELETE FROM PRODUTOS WHERE ID = ?";
    public static final String ALTERAR = "UPDATE PRODUTOS P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?";
}
