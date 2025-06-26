package org.psaez.java.jdbc.repository;

import org.psaez.java.jdbc.models.Product;
import org.psaez.java.jdbc.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements  Repository<Product> {
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }


    @Override
    public List<Product> findAll() {
         List<Product> products = new ArrayList<>();
         try(Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from productos")){
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
        try(PreparedStatement stmt = getConnection().prepareStatement("SELECT * from productos WHERE id= ?")){
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
            sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, fecha_registro) VALUES(?,?,?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1,product.getNombre());
            stmt.setLong(2,product.getPrecio());
            if (product.getId() != null && product.getId() >0) {
                stmt.setLong(3,product.getId());
            }else{
                stmt.setDate(3,new Date(product.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
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
        return product;
    }
}
