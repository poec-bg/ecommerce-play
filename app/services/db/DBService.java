package services.db;

import com.google.common.base.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    private static DBService instance;
    private Connection connection = null;

    private DBService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String ip = "127.0.0.1:3306";
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/formation?user=root&useSSL=false");
        } catch (ClassNotFoundException | SQLException e) {
            System.exit(0);
        }
    }

    public static DBService get() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}