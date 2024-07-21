package com.fpoly.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Feedback")
public class FeedBack {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer FeedbackId;
	private Integer UserId;
	private String Message;
	private Integer BookingId;
	@Temporal(TemporalType.DATE)
	private LocalDate Timestamp;
	@Temporal(TemporalType.DATE)
	private LocalDate createdAt;
	@Temporal(TemporalType.DATE)
	private LocalDate updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	private Boolean isActive;
	private Integer RoomID;
	

	public Integer getRoomID() {
		return RoomID;
	}

	public void setRoomID(Integer roomID) {
		RoomID = roomID;
	}

	public Integer getFeedbackId() {
		return FeedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		FeedbackId = feedbackId;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Integer getBookingId() {
		return BookingId;
	}

	public void setBookingId(Integer bookingId) {
		BookingId = bookingId;
	}

	public LocalDate getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		Timestamp = timestamp;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdateAt() {
		return updatedAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updatedAt = updateAt;
	}

	public Integer getCreateBy() {
		return createdBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createdBy = createBy;
	}

	public Integer updatedBy() {
		return updatedBy;
	}

	public void setUpdateBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
