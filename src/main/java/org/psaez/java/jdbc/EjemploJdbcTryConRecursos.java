package org.psaez.java.jdbc;

import java.sql.*;

public class EjemploJdbcTryConRecursos {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_curso";
        String username = "root";
        String password = "root";

        try(Connection conn =DriverManager.getConnection(url, username, password);
                Statement stmt =conn.createStatement();
                ResultSet resultado= stmt.executeQuery("SELECT * from productos"); ) {

            while (resultado.next()) {
                System.out.print(resultado.getInt(1));
                System.out.print(" | ");
                System.out.print(resultado.getString("nombre"));
                System.out.print(" | ");
                System.out.print(resultado.getInt("precio"));
                System.out.print(" | ");
                System.out.println(resultado.getDate("fecha_registro"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
