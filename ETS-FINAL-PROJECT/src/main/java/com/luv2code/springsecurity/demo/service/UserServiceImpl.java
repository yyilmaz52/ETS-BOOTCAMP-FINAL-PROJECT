package com.luv2code.springsecurity.demo.service;

import com.luv2code.springsecurity.demo.dao.RoleDao;
import com.luv2code.springsecurity.demo.dao.UserDao;
import com.luv2code.springsecurity.demo.entity.KitchenInventory;
import com.luv2code.springsecurity.demo.entity.Order;
import com.luv2code.springsecurity.demo.entity.Reservation;

import com.luv2code.springsecurity.demo.entity.Role;
import com.luv2code.springsecurity.demo.entity.Room;
import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.user.CrmReservation;
import com.luv2code.springsecurity.demo.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	 
	
	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public List<Role> findRoles() {
		// TODO Auto-generated method stub
		return roleDao.findRoles();
	}
	
	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		
		
		
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		
		// give user default role of "employee"
		if(crmUser.getFormRole() == null) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CUSTOMER")));
		}else if(crmUser.getFormRole().equals("MANAGER")) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_MANAGER")));
		}else if (crmUser.getFormRole().equals("ADMIN")) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_ADMIN")));
		}else if (crmUser.getFormRole().equals("CHEF")) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CHEF")));
		}
		

		 // save user in the database
		userDao.save(user);
	}
	@Override
	@Transactional
	public void save(CrmReservation theCrmReservation) {
		Reservation theReservation = new Reservation();
		String hotelName = theCrmReservation.getHotelName();
		System.out.println(hotelName);
		theReservation.setUserName(theCrmReservation.getUserName());
		theReservation.setHotelName(theCrmReservation.getHotelName());
		theReservation.setFirstName(theCrmReservation.getFirstName());
		theReservation.setLastName(theCrmReservation.getLastName());
		theReservation.setPhoneNumber(theCrmReservation.getPhoneNumber());
		theReservation.setEmail(theCrmReservation.getEmail());
		theReservation.setCheckIn(theCrmReservation.getCheckIn());
		theReservation.setCheckOut(theCrmReservation.getCheckOut());
		
		long diff = Math.abs(theCrmReservation.getCheckOut().getTime()- theCrmReservation.getCheckIn().getTime());
		long daysDiff = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
		if(theCrmReservation.getHotelName().equals("Four Seasons Bosphorus")) {
			theReservation.setBill(2000*(int)daysDiff);
		}else if(theCrmReservation.getHotelName().equals("Hilton Istanbul Hotel")) {
			theReservation.setBill(1500*(int)daysDiff);
		}else if(theCrmReservation.getHotelName().equals("Fairmont Quasar İstanbul")) {
			theReservation.setBill(1000*(int)daysDiff);
		}
		
		userDao.save(theReservation);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Reservation> findMyReservations(String name) {
		
		return userDao.findMyReservations(name);
		
		
		
	}

	@Override
	@Transactional
	public Reservation findById(int theId) {
		
		return userDao.findById(theId);
	}

	@Override
	@Transactional
	public void update(Reservation theReservation) {
		
		 userDao.update(theReservation);
		
	}

	@Override
	@Transactional
	public Room findRoomById(int theId) {
		return userDao.findRoomById(theId);
	}

	@Override
	@Transactional
	public void saveFood(Room theRoom, Order theOrder) {
		
		
		userDao.saveFood(theRoom,theOrder);
		
	}

	@Override
	@Transactional
	public List<Order> findMyOrdersByRoomId(int theId) {
		
		return userDao.findMyOrdersByRoomId(theId);
	}

	@Override
	@Transactional
	public List<Order> findOrders() {
		
		return userDao.findOrders();
	}

	@Override
	@Transactional
	public Order findOrderById(int theId) {
		
		return userDao.findOrderById(theId);
	}

	@Override
	@Transactional
	public void saveStatus(Order theOrder) {
	
		userDao.saveStatus(theOrder);
		
	}

	@Override
	@Transactional
	public KitchenInventory getKitchenInventory() {
		
		return userDao.getKitchenInventory();
	}

	@Override
	@Transactional
	public void updateKitchenInventory(KitchenInventory kitchenInventory, Order order) {
		
		userDao.updateKitchenInventory(kitchenInventory,order);
		
	}

	@Override
	@Transactional
	public void save(KitchenInventory theKitchenInventory) {
		
		userDao.save(theKitchenInventory);
		
	}

	@Override
	@Transactional
	public List<KitchenInventory> findInventoryOrders() {
		
		return userDao.findInventoryOrders();
	}

	@Override
	@Transactional
	public void addInventory(int theId) {
		
		userDao.addInventory(theId);
		
	}

	@Override
	@Transactional
	public void deleteKitchenOrderById(int theId) {
		
		userDao.deleteKitchenOrderById(theId);
		
	}

	@Override
	@Transactional
	public List<User> findEmployees() {
		
		return userDao.findEmployees();
	}

	@Override
	@Transactional
	public void deleteEmployeeById(long theId) {
		
		userDao.deleteEmployeeById(theId);
		
	}

	@Override
	@Transactional
	public void deleteReservationByReservationId(int theId) {
		
		userDao.deleteReservationByReservationId(theId);
		
	}

	@Override
	@Transactional
	public void setRoomToDirty(int theId) {
		
		userDao.setRoomToDirty(theId);
		
	}

	@Override
	@Transactional
	public List<Room> findDirtyRooms() {
		
		return userDao.findDirtyRooms();
	}

	@Override
	@Transactional
	public void cleanRooms() {
		
		userDao.cleanRooms();
		
	}

	@Override
	@Transactional
	public void checkOut(int theId) {
		
		userDao.checkOut(theId);
		
	}

	@Override
	@Transactional
	public Reservation findReservationById(int theId) {
		
		return userDao.findReservationById(theId);
	}

	@Override
	@Transactional
	public List<Room> findCheckedOutRooms() {
		
		return userDao.findCheckedOutRooms();
	}

	@Override
	@Transactional
	public List<Room> findRooms() {
		
		return userDao.findRooms();
	}

	



	

	
}
