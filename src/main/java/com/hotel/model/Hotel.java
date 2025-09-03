package com.hotel.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String direccion;
    private String ciudad;
    private int estrellas;
    private String descripcion;
    
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;
    
    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public List<Habitacion> getHabitaciones() { return habitaciones; }
    public void setHabitaciones(List<Habitacion> habitaciones) { this.habitaciones = habitaciones; }
}