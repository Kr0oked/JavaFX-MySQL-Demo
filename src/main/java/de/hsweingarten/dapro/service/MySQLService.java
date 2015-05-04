package de.hsweingarten.dapro.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Manages the Connection to a MySQL Database
 */
public class MySQLService implements IMySQLService {

    private String driverClass;
    private String url;
    private String username;
    private String password;
    private Connection connection;

    /**
     * Loads values from a ResourceBundle for connecting to a MySQL Database
     */
    public MySQLService() {
        ResourceBundle bundle = ResourceBundle.getBundle("CarRental");
        driverClass = bundle.getString("DB_DRIVER_CLASS");
        url = bundle.getString("DB_URL");
        username = bundle.getString("DB_USERNAME");
        password = bundle.getString("DB_PASSWORD");
    }

    /**
     * Returns a Connection to a MySQL Database
     *
     * @return Connection to a MySQL Database
     */
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
