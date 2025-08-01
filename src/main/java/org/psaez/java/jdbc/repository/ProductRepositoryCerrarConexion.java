package org.psaez.java.jdbc.repository;

import org.psaez.java.jdbc.models.Category;
import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// En esta clase obtenemos una conexión sin Singleton y abrrimos y cerramos una nueva conexión en cada método
public class ProductRepositoryCerrarConexion  implements Repository<Product>{

    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstanceNoSingle();
    }


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
        Product product = null;
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT  p.*, c.nombre as categoria from productos as p " +
                "inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = createProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void insert(Product product) {
        String sql;
        if (product.getId() != null && product.getId() >0) {
            sql = "UPDATE productos SET nombre = ?, precio = ?, categoria_id=? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES(?,?,?,?)";
        }
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,product.getNombre());
            stmt.setLong(2,product.getPrecio());
            stmt.setLong(3,product.getCategory().getId());
            if (product.getId() != null && product.getId() >0) {
                stmt.setLong(4,product.getId());
            }else{
                stmt.setDate(4,new Date(product.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
