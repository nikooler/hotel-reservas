package com.hotel.dao;

import com.hotel.model.Usuario;
import javax.persistence.*;

public class UsuarioDAO {
    private EntityManagerFactory emf;
    
    public UsuarioDAO() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }
    
    public Usuario validarUsuario(String email, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password", 
                Usuario.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
    
    public void guardarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(usuario);
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
    
    public Usuario buscarPorEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", 
                Usuario.class);
            query.setParameter("email", email);
            
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
}