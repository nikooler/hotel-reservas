package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    private String tipo;
    private double precio;
    private int capacidad;
    private boolean disponibilidad;
    
    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    
    public boolean isDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }
}