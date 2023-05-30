package org.ecommerce;
import java.sql.DriverManager;
import java.sql.*;

//digunakan untuk melakukan koneksi ke database
public class ConnectDatabase {
    private Connection connection;

    public ConnectDatabase() {
        try {
            this.connection = null;
            Class.forName("org.sqlite.JDBC");
            //nama database yang sudah dibuat
            connection = DriverManager.getConnection("jdbc:sqlite:db_ecommerce_dwita.db");
        }
        catch
        (ClassNotFoundException | SQLException e) {
            System.out.println(e + "");
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
