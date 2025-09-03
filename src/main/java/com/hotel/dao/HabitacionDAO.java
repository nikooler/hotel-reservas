package com.hotel.dao;

import com.hotel.model.Habitacion;
import javax.persistence.*;

public class HabitacionDAO {
    private EntityManagerFactory emf;
    
    public HabitacionDAO() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }
    
    public Habitacion buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }
    
    public void actualizarHabitacion(Habitacion habitacion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(habitacion);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}