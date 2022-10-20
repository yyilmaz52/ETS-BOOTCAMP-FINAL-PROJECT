package com.luv2code.springsecurity.demo.service;

import com.luv2code.springsecurity.demo.entity.KitchenInventory;
import com.luv2code.springsecurity.demo.entity.Order;
import com.luv2code.springsecurity.demo.entity.Reservation;
import com.luv2code.springsecurity.demo.entity.Role;
import com.luv2code.springsecurity.demo.entity.Room;
import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.user.CrmReservation;
import com.luv2code.springsecurity.demo.user.CrmUser;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
	
	public List<Role> findRoles();

	public void save(CrmReservation theCrmReservation);

	public List<Reservation> findMyReservations(String name);

	public Reservation findById(int theId);

	public void update(Reservation theReservation);

	public Room findRoomById(int theId);

	public void saveFood(Room theRoom, Order theOrder);

	public List<Order> findMyOrdersByRoomId(int theId);

	public List<Order> findOrders();

	public Order findOrderById(int theId);

	public void saveStatus(Order theOrder);

	public KitchenInventory getKitchenInventory();

	public void updateKitchenInventory(KitchenInventory kitchenInventory, Order order);

	public void save(KitchenInventory theKitchenInventory);

	public List<KitchenInventory> findInventoryOrders();

	public void addInventory(int theId);

	public void deleteKitchenOrderById(int theId);

	public List<User> findEmployees();

	public void deleteEmployeeById(long theId);

	public void deleteReservationByReservationId(int theId);

	public void setRoomToDirty(int theId);

	public List<Room> findDirtyRooms();

	public void cleanRooms();

	public void checkOut(int theId);

	public Reservation findReservationById(int theId);

	public List<Room> findCheckedOutRooms();

	public List<Room> findRooms();


	

	

	
}
