package com.luv2code.springsecurity.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="room_order")
public class Order {
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;

	@Column(name ="burger")
	private int burgerNumber;
	
	@Column(name ="iskender")
	private int iskenderNumber;
	
	@Column(name ="kebap")
	private int kebapNumber;
	
	@Column(name ="tost")
	private int tostNumber;
	
	@Column(name ="kofte")
	private int kofteNumber;
	
	@Column(name ="ice_cream")
	private int iceNumber;
	
	@Column(name ="ayran")
	private int ayranNumber;
	
	@Column(name ="cola")
	private int colaNumber;
	
	@Column(name ="fruit_juice")
	private int fruitNumber;
	
	@Column(name="order_status")
	private String status = "waiting";
	
	@ManyToOne (cascade= {CascadeType.PERSIST,CascadeType.MERGE,
						  CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="room_id")
	private Room room;
	
	@Column(name="total_amount")
	private int total;
	
	public Order() {
		
	}

	public Order(int burgerNumber, int iskenderNumber, int kebapNumber, int tostNumber, int kofteNumber, int iceNumber,
			int ayranNumber, int colaNumber, int fruitNumber,String status) {
		this.burgerNumber = burgerNumber;
		this.iskenderNumber = iskenderNumber;
		this.kebapNumber = kebapNumber;
		this.tostNumber = tostNumber;
		this.kofteNumber = kofteNumber;
		this.iceNumber = iceNumber;
		this.ayranNumber = ayranNumber;
		this.colaNumber = colaNumber;
		this.fruitNumber = fruitNumber;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBurgerNumber() {
		return burgerNumber;
	}

	public void setBurgerNumber(int burgerNumber) {
		this.burgerNumber = burgerNumber;
	}

	public int getIskenderNumber() {
		return iskenderNumber;
	}

	public void setIskenderNumber(int iskenderNumber) {
		this.iskenderNumber = iskenderNumber;
	}

	public int getKebapNumber() {
		return kebapNumber;
	}

	public void setKebapNumber(int kebapNumber) {
		this.kebapNumber = kebapNumber;
	}

	public int getTostNumber() {
		return tostNumber;
	}

	public void setTostNumber(int tostNumber) {
		this.tostNumber = tostNumber;
	}

	public int getKofteNumber() {
		return kofteNumber;
	}

	public void setKofteNumber(int kofteNumber) {
		this.kofteNumber = kofteNumber;
	}

	public int getIceNumber() {
		return iceNumber;
	}

	public void setIceNumber(int iceNumber) {
		this.iceNumber = iceNumber;
	}

	public int getAyranNumber() {
		return ayranNumber;
	}

	public void setAyranNumber(int ayranNumber) {
		this.ayranNumber = ayranNumber;
	}

	public int getColaNumber() {
		return colaNumber;
	}

	public void setColaNumber(int colaNumber) {
		this.colaNumber = colaNumber;
	}

	public int getFruitNumber() {
		return fruitNumber;
	}

	public void setFruitNumber(int fruitNumber) {
		this.fruitNumber = fruitNumber;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", burgerNumber=" + burgerNumber + ", iskenderNumber=" + iskenderNumber
				+ ", kebapNumber=" + kebapNumber + ", tostNumber=" + tostNumber + ", kofteNumber=" + kofteNumber
				+ ", iceNumber=" + iceNumber + ", ayranNumber=" + ayranNumber + ", colaNumber=" + colaNumber
				+ ", fruitNumber=" + fruitNumber + "]";
	}

	public int getTotal() {
		return this.getBurgerNumber()*10 +
			    this.getIskenderNumber()*20 +
			    this.getKebapNumber()*15 +
			    this.getTostNumber()*5 +
			    this.getKofteNumber()*20 +
			    this.getIceNumber()*5 +
			    this.getAyranNumber()*5 +
			    this.getColaNumber()*8 +
			    this.getFruitNumber()*3;
	}

	public void setTotal(int total) {
		this.total = this.getBurgerNumber()*10 +
				    this.getIskenderNumber()*20 +
				    this.getKebapNumber()*15 +
				    this.getTostNumber()*5 +
				    this.getKofteNumber()*20 +
				    this.getIceNumber()*5 +
				    this.getAyranNumber()*5 +
				    this.getColaNumber()*8 +
				    this.getFruitNumber()*3;
	}

	
	
}

