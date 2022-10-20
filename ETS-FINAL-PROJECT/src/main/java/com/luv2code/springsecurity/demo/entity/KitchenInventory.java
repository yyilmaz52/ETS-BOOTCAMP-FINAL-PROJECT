package com.luv2code.springsecurity.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kitchen_inventory")
public class KitchenInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id ;

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
	
	public KitchenInventory() {
		
	}
	public KitchenInventory( int burgerNumber, int iskenderNumber, int kebapNumber, int tostNumber,
			int kofteNumber, int iceNumber, int ayranNumber, int colaNumber, int fruitNumber) {
		
		this.burgerNumber = burgerNumber;
		this.iskenderNumber = iskenderNumber;
		this.kebapNumber = kebapNumber;
		this.tostNumber = tostNumber;
		this.kofteNumber = kofteNumber;
		this.iceNumber = iceNumber;
		this.ayranNumber = ayranNumber;
		this.colaNumber = colaNumber;
		this.fruitNumber = fruitNumber;
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

	

	@Override
	public String toString() {
		return "kitchenInventory [id=" + id + ", burgerNumber=" + burgerNumber + ", iskenderNumber=" + iskenderNumber
				+ ", kebapNumber=" + kebapNumber + ", tostNumber=" + tostNumber + ", kofteNumber=" + kofteNumber
				+ ", iceNumber=" + iceNumber + ", ayranNumber=" + ayranNumber + ", colaNumber=" + colaNumber
				+ ", fruitNumber=" + fruitNumber + "]";
	}
	
	public void addInventory(KitchenInventory kitchenInventory) {
		this.burgerNumber= this.burgerNumber + kitchenInventory.getBurgerNumber();
		this.iskenderNumber = this.iskenderNumber + kitchenInventory.getIskenderNumber();
		this.kebapNumber = this.kebapNumber + kitchenInventory.getKebapNumber();
		this.tostNumber = this.tostNumber + kitchenInventory.getTostNumber();
		this.kofteNumber = this.kofteNumber + kitchenInventory.getKofteNumber();
		this.iceNumber = this.iceNumber + kitchenInventory.getIceNumber();
		this.ayranNumber = this.ayranNumber + kitchenInventory.getAyranNumber();
		this.colaNumber = this.colaNumber + kitchenInventory.getColaNumber();
		this.fruitNumber = this.fruitNumber + kitchenInventory.getFruitNumber();
	}
	
}













