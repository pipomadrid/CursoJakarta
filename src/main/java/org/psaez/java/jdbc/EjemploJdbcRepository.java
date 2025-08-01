package org.psaez.java.jdbc;

import org.psaez.java.jdbc.models.Category;
import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.repository.ProductRepositoryImpl;
import org.psaez.java.jdbc.repository.Repository;
import org.psaez.java.jdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EjemploJdbcRepository {
    public static void main(String[] args) {
        // Se incluye la conexión para cerrarla cuando acabe, se usa con patrón Singleton
        try(Connection conn = ConexionBD.getInstance()) {
            Repository<Product> repository = new ProductRepositoryImpl();
            List<Product> products = repository.findAll();
            System.out.println("-------------------Find all-------------------");
            repository.findAll().forEach(System.out::println);
            System.out.println("-------------------Find by Id-------------------");
            System.out.println(repository.findById(1L));
            System.out.println("-------------------Delete-------------------");
            repository.delete(4L);
            repository.findAll().forEach(System.out::println);
            System.out.println("-------------------Create-------------------");
            Product product = new Product();
            product.setNombre("Samsung Tv OLed");
            product.setPrecio(2000);
            Category categoria = new Category();
            categoria.setId(2L);
            categoria.setNombre("Tecnología");
            product.setCategory(categoria);
            product.setFechaRegistro(new Date());
            repository.insert(product);
            repository.findAll().forEach(System.out::println);

            System.out.println("-------------------Update-------------------");
            Product product2 = new Product();
            product2.setId(3L);
            product2.setNombre("Samsung Oled Tv");
            product2.setPrecio(8000);
            Category categoria2 = new Category();
            categoria2.setId(2L);
            product2.setCategory(categoria2);
            repository.insert(product2);
            repository.findAll().forEach(System.out::println);





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
