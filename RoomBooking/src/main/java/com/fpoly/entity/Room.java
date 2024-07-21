package com.fpoly.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Room")
public class Room {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "RoomId")
	private Integer id;

	@Column(name = "RoomName")
	private String roomName;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Column(name = "RoomType")
	private String roomType;

	@Column(name = "Availability")
	private String availability;

	@Column(name = "Location")
	private String location;

	@Column(name = "Amenities")
	private String amenities;

	@Column(name = "Description")
	private String description;

	@Column(name = "Price")
	private Float price;

	@Column(name = "Image")
	private String image;
	@Temporal(TemporalType.DATE)
	@Column(name = "createdAt")
	private LocalDate createdAt;
	@Temporal(TemporalType.DATE)
	@Column(name = "updatedAt")
	private LocalDate updatedAt;

	@Column(name = "createdBy")
	private Integer createdBy;

	@Column(name = "updatedBy")
	private Integer updatedBy;

	@Column(name = "isActive")
	private Boolean isActive;

	// Start

	// Một Room có nhiều Booking ( 1 - N)
//	@OneToMany(mappedBy = "roomBooking")
//	List<Booking> roomBookings;

	// End

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
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

	// Start

//	public List<Booking> getBookings() {
//		return bookings;
//	}
//
//	public void setBookings(List<Booking> bookings) {
//		this.bookings = bookings;
//	}

	// End

}
