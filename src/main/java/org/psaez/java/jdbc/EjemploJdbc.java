package org.psaez.java.jdbc;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_curso";
        String username = "root";
        String password = "root";
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultado = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
            stmt = conn.createStatement();
            resultado = stmt.executeQuery("SELECT * from productos");
            while(resultado.next()) {
                System.out.println(resultado.getString("nombre"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultado.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
