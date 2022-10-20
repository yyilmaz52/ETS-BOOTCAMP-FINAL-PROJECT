package com.luv2code.springsecurity.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "hotel_name")
	private String hotelName;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check_in")
	private Date checkIn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check_out")
	private Date checkOut;
	
	@Column(name="bill")
	private int bill;
	
	public Date getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}	
	
	public Reservation() {
		
	}


	public Reservation(String userName, String hotelName, String firstName, String lastName, String phoneNumber,
		String email, Date checkIn, Date checkOut) {
	this.userName = userName;
	this.hotelName = hotelName;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
	
}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getCheckOut() {
		return checkOut;
	}


	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public void addToBill(Order theOrder) {
		this.bill = bill + theOrder.getTotal();
	}

	public int getBill() {
		return bill;
	}


	public void setBill(int bill) {
		this.bill = bill;
	}


	@Override
	public String toString() {
		return "Reservation [id=" + id + ", userName=" + userName + ", hotelName=" + hotelName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}
}

