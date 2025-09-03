package com.hotel.dao;

import com.hotel.model.Reserva;
import javax.persistence.*;

public class ReservaDAO {
    private EntityManagerFactory emf;
    
    public ReservaDAO() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }
    
    public void guardarReserva(Reserva reserva) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(reserva);
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
    
    public Reserva buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }
}
