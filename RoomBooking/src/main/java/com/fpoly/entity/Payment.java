package com.fpoly.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentId")
	private Integer id;

	@Column(name = "UserId")
	private Integer userid;

	@Column(name = "BookingId")
	private Integer bookingid;

	@Temporal(TemporalType.DATE)
	@Column(name = "Timestamp")
	private Date timestamp;

	@Column(name = "PaymentMethod")
	private String paymentmethod;

	@Column(name = "Notes")
	private String note;

	@Column(name = "createdAt")
	private LocalDate createdAt = java.time.LocalDate.now();

	@Column(name = "updatedAt")
	private LocalDate updateAt = java.time.LocalDate.now();

	@Column(name = "createdBy")
	private Integer createdBy = null;

	@Column(name = "updatedBy")
	private Integer updatedBy = null;

	@Column(name = "isActive")
	private Boolean isActive;

	// Start

	// Một Payment cho một booking
//	@OneToOne
//	@JoinColumn(name = "BookingId")
//	Booking booking;
//
//	// Một Payment cho một booking
//	@OneToOne
//	@JoinColumn(name = "UserId")
//	User UserPayment;

	// End

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getBookingid() {
		return bookingid;
	}

	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	// Start Thêm Getter mới
//	public Booking getBooking() {
//		return booking;
//	}
//
//	public void setBooking(Booking booking) {
//		this.booking = booking;
//	}
	// End

}
