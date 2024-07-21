package com.fpoly.Dao;

import java.util.List;

import com.fpoly.Utils.JpaUtils;
import com.fpoly.entity.Booking;
import com.fpoly.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.metamodel.Type;

public class UserDAO {
	private EntityManager em = JpaUtils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close(); // đóng EntityManager khi DAO bị giải phóng
		super.finalize();
	}

	// thêm các phương thức CRUD

	// Tìm theo id user
	//Huy //Tìm theo Username --> Session lưu username
	public User findByUsername(String username) {
		User entity = new User();
		try {
			String jpql = "SELECT o FROM User o WHERE o.username =: username";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("username", username);
			entity = query.getSingleResult();
			return entity;
		} catch (Exception e) {
			System.out.println(e);
		}
		return entity;
	}

	public User create(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public User update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	public void updateUserInfo(String fullname, String email, String phone) {
		try {
			em.getTransaction().begin();
			String jpql = "UPDATE User e SET e.fullname = :newFullname, e.email = :newEmail, e.phone= :newPhone WHERE e.username = :username";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter(fullname, "newFullname");
			query.setParameter(email, "newEmail");
			query.setParameter(phone, "newPhone");
			int updatedEntities = query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	// ------------------Phần của Bảo --------------------------------------
	public User finById(String id) {
		User entity = em.find(User.class, id);
		return entity;
	}
	public User findById (Integer id) {
		User entity = em.find(User.class, id);
		return entity;
	}

	public List<User> findAllByUse(Boolean role) {
		String jpql = "select o from User o Where o.role = false";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
		return list;
	}

	public List<Booking> findAllById(String id) {
		String jpql = "select o from Booking o Where o.UserId = :userId";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		List<Booking> list = query.getResultList();
		return list;
	}

	public List<User> findByEmail(String email) {
		String jpql = "SELECT u FROM Users u WHERE u.email = :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", email); // Đặt giá trị cho tham số email
		List<User> list = query.getResultList();
		return list;
	}
	
	
	/*
	 * public String findUserIdById(String id) { User entity = em.find(User.class,
	 * id); if (entity != null) { return entity.getId().toString(); } return null; }
	 * 
	 * public List<Booking> findAllBookingsByUserId(String userId) { String jpql =
	 * "select o from Booking o where o.UserId = :UserId"; TypedQuery<Booking> query
	 * = em.createQuery(jpql, Booking.class); query.setParameter("userId", userId);
	 * List<Booking> list = query.getResultList(); return list; }
	 */

	// --------------------------------------------------------------------


}
