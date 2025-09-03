package com.hotel.dao;

import com.hotel.model.Hotel;
import javax.persistence.*;
import java.util.List;

public class HotelDAO {
    private EntityManagerFactory emf;

    public HotelDAO() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }

    public List<Hotel> buscarHoteles(String ciudad) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hotel> query = em.createQuery(
                "SELECT DISTINCT h FROM Hotel h LEFT JOIN FETCH h.habitaciones WHERE h.ciudad LIKE :ciudad",
                Hotel.class
            );
            query.setParameter("ciudad", "%" + ciudad + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
