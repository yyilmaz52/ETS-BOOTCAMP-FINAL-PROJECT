package com.luv2code.springsecurity.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Room {

	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="room_dirty")
	private boolean dirty ;
	
	@Column(name ="check_out")
	private boolean check_out;
	
	@OneToMany(mappedBy="room",
			cascade= CascadeType.ALL)
	private List<Order> order;
	
	public Room() {
		
	}
	
	
	public Room(int id) {
		this.id = id;
	}


	public Room(int id,boolean dirty, boolean check_out) {
		this.id = id;
		this.dirty = dirty;
		this.check_out = check_out;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public boolean isDirty() {
		return dirty;
	}


	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isCheck_out() {
		return check_out;
	}

	public void setCheck_out(boolean check_out) {
		this.check_out = check_out;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> orders) {
		this.order = orders;
	}

	public void add(Order tempOrder) {
		
		if(order == null) {
			order = new ArrayList<>();
		}
		
		order.add(tempOrder);
		
		tempOrder.setRoom(this);
		
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", dirty=" + dirty + ", check_out=" + check_out + "]";
	}
	

	
}