package org.psaez.java.jdbc;

import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.repository.ProductRepositoryCerrarConexionImpl;
import org.psaez.java.jdbc.repository.ProductRepositoryPoolImpl;
import org.psaez.java.jdbc.repository.Repository;

import java.util.List;

// Uso de Pool De Conexiones
public class EjemploJdbcRepositoryPool {

    public static void main(String[] args) {

        Repository<Product> repository = new ProductRepositoryPoolImpl();
        System.out.println("-------------------Find all-------------------");
        repository.findAll().forEach(System.out::println);
    }
}
