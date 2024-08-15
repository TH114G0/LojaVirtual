package loja_virtual.example.query;

public class ProductQuery {
    public static final String LIST = "SELECT * FROM produtos";
    public static final String INSERT = "INSERT INTO produtos (nome, descricao) VALUES (?, ?)";
    public static final String DELETE = "DELETE FROM produtos WHERE id = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM produtos WHERE id = ?";
}
