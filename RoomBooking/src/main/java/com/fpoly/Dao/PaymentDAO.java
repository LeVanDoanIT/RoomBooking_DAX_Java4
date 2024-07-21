package com.fpoly.Dao;

import java.util.List;

import com.fpoly.Utils.JpaUtils;
import com.fpoly.entity.Payment;
import com.fpoly.entity.Room;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PaymentDAO {
	private EntityManager em = JpaUtils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close(); // đóng EntityManager khi DAO bị giải phóng
		super.finalize();
	}

	public Payment create(Payment entity) {
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

	public List<Payment> findAll() {
		String jpql = "select o from Payment o";
		TypedQuery<Payment> query = em.createQuery(jpql, Payment.class);
		List<Payment> list = query.getResultList();
		return list;
	}
	public List<Payment> findByUserID(Integer UserID){
		String jpql = "select o from Payment o Where o.userid := UserID";
		TypedQuery<Payment> query = em.createQuery(jpql, Payment.class);
		query.setParameter("UserID", UserID);
		List<Payment> list = query.getResultList();
		return list;
	}
}
