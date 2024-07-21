package com.fpoly.Dao;

import java.util.List;

import com.fpoly.Utils.JpaUtils;
import com.fpoly.entity.FeedBack;
import com.fpoly.entity.Room;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class FeedBackDAO {
	private EntityManager em = JpaUtils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close(); // đóng EntityManager khi DAO bị giải phóng
		super.finalize();
	}

	// thêm các phương thức CRUD
	 public FeedBack remove(Long id) {
	        try {
	            FeedBack entity = this.findById(id);
	            em.getTransaction().begin();
	            em.remove(entity);
	            em.getTransaction().commit();
	            return entity;
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw new RuntimeException(e);
	        }
	    }

	// Tìm theo id user
	// Huy //Tìm theo Username --> Session lưu username
	public List<FeedBack> findFeedBack(Integer roomID) {
		List<FeedBack> list = null;
		try {
			String jpql = "SELECT o FROM FeedBack o WHERE o.RoomID =: roomID";
			TypedQuery<FeedBack> query = em.createQuery(jpql, FeedBack.class);
			query.setParameter("roomID", roomID);
			list = query.getResultList();

		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public FeedBack create(FeedBack entity) {
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

	public FeedBack update(FeedBack entity) {
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
//		public void updateUserInfo(String fullname, String email, String phone) {
//			try {
//				em.getTransaction().begin();
//				String jpql = "UPDATE User e SET e.fullname = :newFullname, e.email = :newEmail, e.phone= :newPhone WHERE e.username = :username";
//				TypedQuery<User> query = em.createQuery(jpql, User.class);
//				query.setParameter(fullname, "newFullname");
//				query.setParameter(email, "newEmail");
//				query.setParameter(phone, "newPhone");
//				int updatedEntities = query.executeUpdate();
//				em.getTransaction().commit();
//			} catch (Exception e) {
//				// TODO: handle exception
//				em.getTransaction().rollback();
//				throw new RuntimeException(e);
//			}
//		}

	// ------------------Phần của Bảo --------------------------------------
//		public User finById(String id) {
//			User entity = em.find(User.class, id);
//			return entity;
//		}
		public FeedBack findById (Long id) {
			FeedBack entity = em.find(FeedBack.class, id);
			return entity;
		}
//
		public List<FeedBack> FindAll() {
			String jpql = "select o from FeedBack o";
			TypedQuery<FeedBack> query = em.createQuery(jpql, FeedBack.class);
			List<FeedBack> list = query.getResultList();
			return list;
		}
//
//		public List<Booking> findAllById(String id) {
//			String jpql = "select o from Booking o Where o.UserId = :userId";
//			TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
//			List<Booking> list = query.getResultList();
//			return list;
//		}
//
//		public List<User> findByEmail(String email) {
//			String jpql = "SELECT u FROM Users u WHERE u.email = :email";
//			TypedQuery<User> query = em.createQuery(jpql, User.class);
//			query.setParameter("email", email); // Đặt giá trị cho tham số email
//			List<User> list = query.getResultList();
//			return list;
//		}
//		

}
