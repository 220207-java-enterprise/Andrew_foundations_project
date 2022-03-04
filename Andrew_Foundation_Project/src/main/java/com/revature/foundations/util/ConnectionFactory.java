package com.revature.foundations.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Properties property = new Properties();

    private ConnectionFactory() {
        try {
            this.property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

            //property.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(property.getProperty("db-url"),
                property.getProperty("db-username"), property.getProperty("db-password"));

        if (conn == null) {
            throw new RuntimeException("Could not establish connection with the database!");
        }

        return conn;

    }

}
