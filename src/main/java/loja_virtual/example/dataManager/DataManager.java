package loja_virtual.example.dataManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  A classe {@code DataManager} é responsável por gerenciar a conexão com o
 *  banco de dados MySQL utilizado no sistema Loja Virtual.
 */
public class DataManager {
    /**
     *  A URL de conexão com o banco de dados MySQL.
     */
    private final String URL = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";

    /**
     * O nome de usuário para acessar o banco de dados.
     */
    private final String USER = "root";
    /**
     * A senha para acessar o banco de dados.
     */
    private final String PASSWORD = "Thiago2826.";
    public DataSource source;
    public DataManager() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(URL);
        comboPooledDataSource.setUser(USER);
        comboPooledDataSource.setPassword(PASSWORD);

        this.source = comboPooledDataSource;
    }

    /**
     * Estabelece uma conexão com o banco de dados utilizando as credenciais
     * fornecidas e retorna a conexão estabelecida.
     * @return Uma conexão {@link Connection} com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao tentar se conectar ao banco de dados.
     */
    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
