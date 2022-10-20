package com.luv2code.springsecurity.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.luv2code.springsecurity.demo.entity.KitchenInventory;
import com.luv2code.springsecurity.demo.entity.Order;
import com.luv2code.springsecurity.demo.entity.Reservation;
import com.luv2code.springsecurity.demo.entity.Room;
import com.luv2code.springsecurity.demo.entity.User;



@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user 
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public void save(Reservation theReservation) {
		
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theReservation);
		
		Room theRoom = new Room(theReservation.getId());
		currentSession.save(theRoom);
	}
	

	@Override
	public List<Reservation> findMyReservations(String name) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Reservation> theQuery = 
				currentSession.createQuery("from Reservation where username=:name",Reservation.class);
		
		theQuery.setParameter("name", name);	
		
		List<Reservation> myReservations = theQuery.getResultList();
				
		return myReservations;
		
	}

	@Override
	public Reservation findById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		Reservation theReservation =
				currentSession.get(Reservation.class, theId);
				
		return theReservation;
	}

	@Override
	public void update(Reservation theReservation) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theReservation);
		
	}

	@Override
	public Room findRoomById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
						
		// get the room
		Room theRoom =currentSession.get(Room.class, theId);
						
		return theRoom;
	
	}

	@Override
	public void saveFood(Room theRoom, Order theOrder) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Reservation theReservation = findReservationById(theRoom.getId());
		theReservation.addToBill(theOrder);
		theRoom.add(theOrder);
		currentSession.save(theOrder);
		
	}

	@Override
	public List<Order> findMyOrdersByRoomId(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Order> theQuery = 
				currentSession.createQuery("from Order where room_id=:id",Order.class);
		
		theQuery.setParameter("id", theId);	
		
		List<Order> myOrders = theQuery.getResultList();
				
		return myOrders;
		
	}

	@Override
	public List<Order> findOrders() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query
		Query <Order> theQuery = 
				currentSession.createQuery("from Order",Order.class);
		
		List<Order> orders = theQuery.getResultList();
		
		return orders;
	}

	@Override
	public Order findOrderById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query
		Query <Order> theQuery = 
				currentSession.createQuery("from Order where id=:id",Order.class);
		theQuery.setParameter("id", theId);	
		Order theOrder = theQuery.getSingleResult();
		
		
		return theOrder;
	}

	@Override
	public void saveStatus(Order order) {
		
		Session currentSession = entityManager.unwrap(Session.class);	
		order.setStatus("delivered");
		currentSession.saveOrUpdate(order);		
		
	}

	@Override
	public KitchenInventory getKitchenInventory() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <KitchenInventory> theQuery = 
				currentSession.createQuery("from KitchenInventory where id=:id",KitchenInventory.class);
		theQuery.setParameter("id", 1);	
		
		KitchenInventory kitchenInventory = theQuery.getSingleResult();
		
		return kitchenInventory;
	}

	@Override
	public void updateKitchenInventory(KitchenInventory kitchenInventory, Order order) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		kitchenInventory.setBurgerNumber(kitchenInventory.getBurgerNumber() - order.getBurgerNumber());
		kitchenInventory.setIskenderNumber(kitchenInventory.getIskenderNumber() - order.getIskenderNumber());
		kitchenInventory.setKebapNumber(kitchenInventory.getKebapNumber() - order.getKebapNumber());
		kitchenInventory.setTostNumber(kitchenInventory.getTostNumber() - order.getTostNumber());
		kitchenInventory.setKofteNumber(kitchenInventory.getKofteNumber() - order.getKofteNumber());
		kitchenInventory.setIceNumber(kitchenInventory.getIceNumber() - order.getIceNumber());
		kitchenInventory.setAyranNumber(kitchenInventory.getAyranNumber() - order.getAyranNumber());
		kitchenInventory.setColaNumber(kitchenInventory.getColaNumber() - order.getColaNumber());
		kitchenInventory.setFruitNumber(kitchenInventory.getFruitNumber() - order.getFruitNumber());
		currentSession.saveOrUpdate(kitchenInventory);
	}

	@Override
	public void save(KitchenInventory theKitchenInventory) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(theKitchenInventory);
		
	}

	@Override
	public List<KitchenInventory> findInventoryOrders() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <KitchenInventory> theQuery = 
				currentSession.createQuery("from KitchenInventory where id > 1",KitchenInventory.class);
		
		List<KitchenInventory> inventoryOrders = theQuery.getResultList();
		
		return inventoryOrders;
	}

	@Override
	public void addInventory(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <KitchenInventory> inventoryQuery = 
				currentSession.createQuery("from KitchenInventory where id=1",KitchenInventory.class);
		
		Query <KitchenInventory> inventoryOrderQuery = 
				currentSession.createQuery("from KitchenInventory where id=:id",KitchenInventory.class);
		inventoryOrderQuery.setParameter("id", theId);	
		
		KitchenInventory inventory = inventoryQuery.getSingleResult();
		KitchenInventory inventoryOrder = inventoryOrderQuery.getSingleResult();
		
		inventory.addInventory(inventoryOrder);
		currentSession.delete(inventoryOrder);
		
	}

	@Override
	public void deleteKitchenOrderById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query <KitchenInventory> kitchenOrder = 
				currentSession.createQuery("from KitchenInventory where id=:id",KitchenInventory.class);
		kitchenOrder.setParameter("id", theId);	
		
		KitchenInventory inventoryOrder = kitchenOrder.getSingleResult();
		
		currentSession.delete(inventoryOrder);
		
	}

	@Override
	public List<User> findEmployees() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <User> theQuery = 
				currentSession.createQuery("from User",User.class);
		
		List<User> employees = theQuery.getResultList();
		return employees;
	}

	@Override
	public void deleteEmployeeById(long theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query <User> theQuery = 
				currentSession.createQuery("from User where id=:id",User.class);
		theQuery.setParameter("id", theId);	
		User deleteUser = theQuery.getSingleResult();
		currentSession.delete(deleteUser);
		
	}

	@Override
	public void deleteReservationByReservationId(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query <Reservation> theQuery = 
				currentSession.createQuery("from Reservation where id=:id",Reservation.class);
		theQuery.setParameter("id", theId);	
		
		Reservation deleteReservation = theQuery.getSingleResult();
		
		Query <Room> roomQuery = 
				currentSession.createQuery("from Room where id=:id",Room.class);
		roomQuery.setParameter("id", theId);	
		
		Room deleteRoom = roomQuery.getSingleResult();
		
		currentSession.delete(deleteRoom);
		currentSession.delete(deleteReservation);
		
	}

	@Override
	public void setRoomToDirty(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Room> theQuery = 
				currentSession.createQuery("from Room where id=:id",Room.class);
		theQuery.setParameter("id", theId);	
		Room theRoom = theQuery.getSingleResult();
		theRoom.setDirty(true);
		currentSession.saveOrUpdate(theRoom);
		
	}

	@Override
	public List<Room> findDirtyRooms() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query <Room> dirtyRoomsQuery = 
				currentSession.createQuery("from Room where room_dirty=1",Room.class);
		List<Room> dirtyRooms = dirtyRoomsQuery.getResultList();
		
		return dirtyRooms;
	}

	@Override
	public void cleanRooms() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Room> findDirtyRooms = 
				currentSession.createQuery("from Room where room_dirty=1",Room.class);
		
		Query <Room> findCheckedOutRooms = 
				currentSession.createQuery("from Room where check_out=1",Room.class);
		
		List<Room> dirtyRooms = findDirtyRooms.getResultList();
		List<Room> checkedOutRooms = findCheckedOutRooms.getResultList();
		
		for(Room tempRoom: dirtyRooms) {
			tempRoom.setDirty(false);
		}
		
		for(Room tempRoom: checkedOutRooms) {
			tempRoom.setCheck_out(false);
		}
		
		
	}

	@Override
	public void checkOut(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query <Room> theQuery = 
				currentSession.createQuery("from Room where id=:id",Room.class);
		theQuery.setParameter("id", theId);	
		Room checkOutRoom = theQuery.getSingleResult();
		checkOutRoom.setCheck_out(true);
		checkOutRoom.setDirty(true);
		
	}

	@Override
	public Reservation findReservationById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Reservation> theQuery = 
				currentSession.createQuery("from Reservation where id=:id",Reservation.class);
		theQuery.setParameter("id", theId);	
		
		Reservation theReservation = theQuery.getSingleResult();
		
		return theReservation;
	}

	@Override
	public List<Room> findCheckedOutRooms() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Room> checkOutQuery = 
				currentSession.createQuery("from Room where check_out=1",Room.class);
		
		List<Room> checkedOutRooms = checkOutQuery.getResultList();
		
		return checkedOutRooms;
	}

	@Override
	public List<Room> findRooms() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query <Room> theQuery = 
				currentSession.createQuery("from Room",Room.class);
		
		List<Room> myRooms = theQuery.getResultList();
		
		return myRooms;
	}
	
	
	
	

}








