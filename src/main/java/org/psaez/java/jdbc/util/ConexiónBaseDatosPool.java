package org.psaez.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexiónBaseDatosPool{

    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String username = "root";
    private static String password = "root";
    private static BasicDataSource pool;


    // Devuelve un pool de Conexiones
    public static BasicDataSource getInstance() throws SQLException {
        if(pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3); // Tamaño inicial del Pool
            pool.setMinIdle(3); // Minimas conexiones activas
            pool.setMaxIdle(8); // Máximas conexiones Activas
            pool.setMaxTotal(8); // MTamaño máximo del Pool
        }
        return pool;
    }


    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
