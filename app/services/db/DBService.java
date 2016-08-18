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
//            String ip = "10.110.10.26:3306";
            String ip = "192.168.1.6:3306";
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/lenaick?user=root&password=formation&useSSL=false");
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Pas de connection possible à la base de données");
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