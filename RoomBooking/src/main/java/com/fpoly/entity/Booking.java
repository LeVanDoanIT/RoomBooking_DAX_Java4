package com.fpoly.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//Start
@Entity
@Table(name = "Booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long BookingId;

	@Column(name = "UserId")
	private Integer userid;

	@Column(name = "RoomId")
	int RoomId;

	@Column(name = "BookDay")
	Date BookDay;

	@Temporal(TemporalType.DATE)
	@Column(name = "CheckIn")
	Date CheckIn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CheckOut")
	Date CheckOut;

	@Column(name = "Status")
	Boolean Status;

	@Column(name = "FeedbackID")
	Long FeedbackID;

	@Column(name = "TotalPrice")
	Float TotalPrice;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdAt")
	Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "updatedAt")
	Date updatedAt;

	@Column(name = "createdBy")
	int createdBy;

	@Column(name = "updatedBy")
	int updatedBy;

	@Column(name = "isActive")
	Boolean isActive;

	public Long getBookingId() {
		return BookingId;
	}

	public void setBookingId(Long bookingId) {
		BookingId = bookingId;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public int getRoomId() {
		return RoomId;
	}

	public void setRoomId(int roomId) {
		RoomId = roomId;
	}

	public Date getBookDay() {
		return BookDay;
	}

	public void setBookDay(Date bookDay) {
		BookDay = bookDay;
	}

	public Date getCheckIn() {
		return CheckIn;
	}

	public void setCheckIn(Date checkIn) {
		CheckIn = checkIn;
	}

	public Date getCheckOut() {
		return CheckOut;
	}

	public void setCheckOut(Date checkOut) {
		CheckOut = checkOut;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public Long getFeedbackID() {
		return FeedbackID;
	}

	public void setFeedbackID(Long feedbackID) {
		FeedbackID = feedbackID;
	}

	public Float getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		TotalPrice = totalPrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}