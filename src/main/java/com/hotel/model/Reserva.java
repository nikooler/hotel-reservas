package com.hotel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    
    private double total;
    private String estado;
    
    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
    
    public Date getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(Date fechaEntrada) { this.fechaEntrada = fechaEntrada; }
    
    public Date getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(Date fechaSalida) { this.fechaSalida = fechaSalida; }
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}