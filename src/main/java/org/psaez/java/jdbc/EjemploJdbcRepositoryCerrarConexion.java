package org.psaez.java.jdbc;

import org.psaez.java.jdbc.models.Category;
import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.repository.ProductRepositoryCerrarConexionImpl;
import org.psaez.java.jdbc.repository.ProductRepositoryImpl;
import org.psaez.java.jdbc.repository.Repository;
import org.psaez.java.jdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EjemploJdbcRepositoryCerrarConexion {
    public static void main(String[] args) {

        Repository<Product> repository = new ProductRepositoryCerrarConexionImpl();
        List<Product> products = repository.findAll();
        System.out.println("-------------------Find all-------------------");
        repository.findAll().forEach(System.out::println);
        System.out.println("-------------------Find by Id-------------------");
        System.out.println(repository.findById(1L));
        System.out.println("-------------------Delete-------------------");
        repository.delete(4L);
        repository.findAll().forEach(System.out::println);
    }

}
