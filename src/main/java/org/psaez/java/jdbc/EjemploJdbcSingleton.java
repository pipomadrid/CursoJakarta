package org.psaez.java.jdbc;

import org.psaez.java.jdbc.util.ConexionBD;

import java.sql.*;

public class EjemploJdbcSingleton {
    public static void main(String[] args) {
        try(Connection conn = ConexionBD.getInstance();
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
