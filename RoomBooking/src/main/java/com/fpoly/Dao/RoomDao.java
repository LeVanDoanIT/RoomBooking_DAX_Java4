package com.fpoly.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fpoly.Utils.JpaUtils;
import com.fpoly.entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RoomDao {
    private EntityManager em = JpaUtils.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        em.close(); // đóng EntityManager khi DAO bị giải phóng
        super.finalize();
    }
    
    public Room create(Room entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
    
    public Room update(Room entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
    
    public Room remove(Integer id) {
        try {
            Room entity = this.findById(id);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
    
    public Room findById(Integer id) {
        Room entity = em.find(Room.class, id);
        return entity;
    }
    
    // Lấy danh sách phòng theo loại phòng
    public List<Room> findAllRoomType(String roomType) {
        String jpql = "SELECT o FROM Room o WHERE o.roomType = :roomType";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        query.setParameter("roomType", roomType);
        List<Room> list = query.getResultList();
        return list;
    }

    // Lấy tất cả các phòng
    public List<Room> findAll() {
        String jpql = "SELECT o FROM Room o";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        List<Room> list = query.getResultList();
        return list;
    }
    
    public List<Room> findAllIsActive() {
        String jpql = "SELECT o FROM Room o where o.isActive =: tt";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        query.setParameter("tt", true);
        List<Room> list = query.getResultList();
        return list;
    }
    
    public List<Room> findRoomsByTypeAndLocation(String roomType, String location) {
        String jpql = "SELECT r FROM Room r WHERE ";
        Map<String, Object> params = new HashMap<>();
        
        if (!"All".equalsIgnoreCase(roomType)) {
            jpql += "r.roomType = :roomType ";
            params.put("roomType", roomType);
        }
        
        if (!"All".equalsIgnoreCase(location)) {
            if (!"All".equalsIgnoreCase(roomType)) {
                jpql += "AND ";
            }
            jpql += "r.location = :location ";
            params.put("location", location);
        }
        
        if (params.isEmpty()) {
            jpql = "SELECT r FROM Room r";
        }
        
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        
        return query.getResultList();
    }

    public List<Room> findAllRoomsOrderByPriceAsc() {
        String jpql = "SELECT r FROM Room r ORDER BY r.price ASC";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        List<Room> list = query.getResultList();
        return list;
    }

    public List<Room> findAllRoomsOrderByPriceDesc() {
        String jpql = "SELECT r FROM Room r ORDER BY r.price DESC";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        List<Room> list = query.getResultList();
        return list;
    }


}
