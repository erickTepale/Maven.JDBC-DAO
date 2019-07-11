package daos;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBC_demo";
    private static final String USER = "root";
    private static final String PASS = "password";
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    private ConnectionFactory(){

    }

    public static Connection getConnectionFactory(){
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
