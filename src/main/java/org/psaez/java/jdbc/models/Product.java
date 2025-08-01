package org.psaez.java.jdbc.models;


import java.util.Date;

public class Product {

    private Long id;
    private String nombre;
    private int precio;
    private Date fechaRegistro;
    private Category category;

    public Product() {
    }

    @Override
    public String toString() {
        return  id +
                " | " + nombre +
                " | " + precio +
                " | "  + fechaRegistro +
                " | "  + category.getNombre();
    }

    public Product(Long id, String nombre, int precio, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Category getCategory() { return category;}

    public void setCategory(Category category) { this.category = category; }
}
