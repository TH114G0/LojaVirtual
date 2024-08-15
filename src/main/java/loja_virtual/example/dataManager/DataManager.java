package loja_virtual.example.dataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {
    private final String URL = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "Thiago2826.";
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
