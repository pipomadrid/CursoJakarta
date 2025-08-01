package org.psaez.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String username = "root";
    private static String password = "root";
    private static Connection connection;

    // Patrón Singleton, si cerrarmos la conexión en cada método del repositorio nos dará error
    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url,username,password);
        }
        return connection;
    }

    // Crea una nueva conexión cada vez que se invoca
    public static Connection getInstanceNoSingle() throws SQLException {
        return connection = DriverManager.getConnection(url,username,password);
    }
}
