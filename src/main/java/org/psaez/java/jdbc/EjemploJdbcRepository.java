package org.psaez.java.jdbc;

import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.repository.ProductRepositoryImpl;
import org.psaez.java.jdbc.repository.Repository;
import org.psaez.java.jdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EjemploJdbcRepository {
    public static void main(String[] args) {
        // Se incluye la conexión para cerrarla cuando acabe
        try(Connection conn = ConexionBD.getInstance()) {
            Repository<Product> repository = new ProductRepositoryImpl();
            List<Product> products = repository.findAll();
            products.forEach(System.out::println);

            System.out.println(repository.findById(1L));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
