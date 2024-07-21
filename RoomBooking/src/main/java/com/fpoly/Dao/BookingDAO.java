package com.fpoly.Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.fpoly.Utils.JpaUtils;
import com.fpoly.entity.Booking;
import com.fpoly.entity.FeedBack;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;

public class BookingDAO {

	private EntityManager em = JpaUtils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	// thêm các phương thức CRUD
	public Booking create(Booking entity) {
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

	public Booking update(Booking entity) {
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

	public Booking findBookingID(Integer id) {
		Booking entity = em.find(Booking.class, id);
		return entity;
	}

	//
	public Booking findById(int bookingId) {
		String jpql = "SELECT o FROM Booking o WHERE o.BookingId =: BookingId";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("BookingId", bookingId);
		Booking booking = query.getSingleResult();
		return booking;
	}

	public List<Booking> findAll() {
		String jpql = "select o from Booking o";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		List<Booking> list = query.getResultList();
		return list;
	}

	public List<Booking> finByUserID(Integer userID, Boolean stt) {
		String jpql = "select o from Booking o Where o.userid =: userID and o.Status =: stt";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("userID", userID);
		query.setParameter("stt", stt);
		List<Booking> list = query.getResultList();
		return list;
	}

	public List<Booking> finDateCheckIn(Integer userID, Date toDay) {

		String jpql = "select o from Booking o Where o.userid =: userID and o.CheckIn >=: toDay and o.Status =: stt";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("userID", userID);
		query.setParameter("toDay", toDay);
		query.setParameter("stt", true);
		List<Booking> list = query.getResultList();
		return list;
	}

	// Tìm booking lớn nhất(Mới vừa được tạo của userId trên session)
	public List<Booking> findMaxBookingIdByUserId(int userId) {
		String jpql = "SELECT o FROM Booking o WHERE o.userid = :userId ORDER BY o.BookingId DESC";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("userId", userId);
		List<Booking> resultList = query.getResultList();
		return resultList;
	}

	// Tìm booking với mã phòng đã được đặt trong khoản checkIn, checkOut
//	public List<Booking> findBookingOfBoooked(int RoomId, String checkInString, String checkOutString) {
//		String jpql = "SELECT b FROM Booking b WHERE b.RoomId = :RoomId AND b.CheckIn BETWEEN :CheckIn AND :CheckOut AND b.CheckOut BETWEEN :CheckIn AND :CheckOut";
//		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
//
//		// Truyền tham số
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//		LocalDateTime CheckIn = LocalDateTime.parse(checkInString, formatter);
//		LocalDateTime CheckOut = LocalDateTime.parse(checkOutString, formatter);
//		query.setParameter("RoomId", RoomId);
//		query.setParameter("CheckIn", CheckIn);
//		query.setParameter("CheckOut", CheckOut);
//		List<Booking> resultList = query.getResultList();
//		return resultList;
//	}

	public List<Booking> findBookingOfBoooked(int RoomId, Date checkIn, Date checkOut) {
		String jpql = "SELECT b FROM Booking b WHERE b.RoomId = :RoomId AND (b.CheckIn <= :checkOut AND b.CheckOut >= :checkIn)";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("RoomId", RoomId);
		query.setParameter("checkIn", checkIn);
		query.setParameter("checkOut", checkOut);
		List<Booking> resultList = query.getResultList();
		return resultList;
	}
}
