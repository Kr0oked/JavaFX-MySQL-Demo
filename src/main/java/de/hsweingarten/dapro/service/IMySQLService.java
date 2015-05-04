package de.hsweingarten.dapro.service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface of MySQLService for Dependency Injection
 */
public interface IMySQLService {

    /**
     * Returns a Connection to a MySQL Database
     *
     * @return Connection to a MySQL Database
     */
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
