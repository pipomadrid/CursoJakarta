package org.psaez.java.jdbc.repository;

import org.psaez.java.jdbc.models.Category;
import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.util.ConexiónBaseDatosPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Clase de repositorio para usar el pool de conexiones
public class ProductRepositoryPoolImpl implements Repository<Product> {

    // Nos devuelve una conexión tomada del pool de conexiones
    private Connection getConnection() throws SQLException {
        return ConexiónBaseDatosPool.getConnection();
    }

    // Solo implementamos un método del repositorio como ejemplo
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*,c.nombre as categoria from productos as p " +
                    "inner join categorias as c ON (p.categoria_id = c.id)")){
            while(rs.next()){
                Product product =  createProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void insert(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    private Product createProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong(1));
        product.setNombre(rs.getString("nombre"));
        product.setPrecio(rs.getInt("precio"));
        product.setFechaRegistro(rs.getDate("fecha_registro"));
        Category category = new Category();
        category.setId(rs.getLong("categoria_id"));
        category.setNombre(rs.getString("categoria"));
        product.setCategory(category);
        return product;
    }
}
