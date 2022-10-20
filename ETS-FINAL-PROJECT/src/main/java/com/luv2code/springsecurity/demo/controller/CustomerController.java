package com.luv2code.springsecurity.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.luv2code.springsecurity.demo.entity.Order;
import com.luv2code.springsecurity.demo.entity.Reservation;
import com.luv2code.springsecurity.demo.entity.Room;


import com.luv2code.springsecurity.demo.service.UserService;
import com.luv2code.springsecurity.demo.user.CrmReservation;



@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private UserService userService;
	

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkIn;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkOut;
	
	@GetMapping("/information")
	public String getCustomer() {
		
		
		return "customer";
	}
		
	@GetMapping("/fairmont-reservation")
	public String fairmontReservation(Model theModel, Authentication authentication) {
		
		CrmReservation fairmont = new CrmReservation();
		fairmont.setHotelName("Fairmont Quasar İstanbul");
		fairmont.setUserName(authentication.getName());
		theModel.addAttribute("reservation", fairmont);
		
		return "reservation";
	}
	
	@GetMapping("/four-seasons-reservation")
	public String fourSeasonsReservation(Model theModel, Authentication authentication) {
		
		CrmReservation fourSeasons = new CrmReservation();
		fourSeasons.setHotelName("Four Seasons Bosphorus");
		fourSeasons.setUserName(authentication.getName());
		theModel.addAttribute("reservation", fourSeasons);
		return "reservation";
	}
	
	@GetMapping("/hilton-reservation")
	public String hiltonReservation(Model theModel, Authentication authentication) {
		
		CrmReservation hilton = new CrmReservation();
		hilton.setHotelName("Hilton Istanbul Hotel");
		hilton.setUserName(authentication.getName());
		theModel.addAttribute("reservation", hilton);
		
		return "reservation";
	}
	
	@GetMapping("/fairmont-hotel")
	public String eserHotel(Model theModel) {
		
		return "fairmont-hotel";
	}
	
	@GetMapping("/four-seasons-hotel")
	public String fourSeasonsHotel() {
		return "four-seasons";
	}
	
	@GetMapping("/hilton-hotel-istanbul")
	public String hiltonHotel() {
		return "hilton-istanbul";
	}
	
	@GetMapping("/my-reservations")
	public String myReservations(Model theModel,Authentication authentication) {
		
		List<Reservation> myReservations = userService.findMyReservations(authentication.getName());
		theModel.addAttribute("reservations",myReservations);
		
		return "my-reservations";
	}
	
	@GetMapping("/reservation-check-in")
	public String reservationDetails(@RequestParam("reservationId")int theId,
			Model theModel) {
		
		Reservation theReservation = userService.findById(theId);
		theModel.addAttribute("reservation", theReservation);
		
		return "check-in";
	}
	
	@GetMapping("/update-reservation")
	public String updateReservation(@RequestParam("reservationId")int theId,
			Model theModel) {
		
		Reservation theReservation = userService.findById(theId);
		theModel.addAttribute("reservation", theReservation);
				
		return "update-reservation";
	}
	@GetMapping("/order-food")
	public String orderFood(@RequestParam("roomId")int theId,Model theModel) {
		
		Room theRoom = userService.findRoomById(theId);
		Order order = new Order();
		theModel.addAttribute("room", theRoom);
		theModel.addAttribute("order", order);
		
		return "order-food";
		
	}
	@GetMapping("/exitAndNoHousekeeping")
	public String exitAndNoHousekeeping(@RequestParam("reservationId")int theId) {
		
		return "redirect:/customer/information";		
		
	}
	
	@GetMapping("/exitAndCallHousekeeping")
	public String exitAndCallHousekeeping(@RequestParam("reservationId")int theId) {
		
		userService.setRoomToDirty(theId);
	
		return "redirect:/customer/information";	
		
	}
	
	@GetMapping("/check-out")
	public String checkOut(@RequestParam("reservationId")int theId,Model theModel) {
		
		List<Order> orders = userService.findMyOrdersByRoomId(theId);
		Reservation theReservation = userService.findReservationById(theId);
		
		long diff = Math.abs(theReservation.getCheckOut().getTime()- theReservation.getCheckIn().getTime());
		long daysDiff = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
		
		if(theReservation.getHotelName().equals("Four Seasons Bosphorus")) {
			theModel.addAttribute("price",daysDiff*2000);
		}else if(theReservation.getHotelName().equals("Hilton Istanbul Hotel")) {
			theModel.addAttribute("price",daysDiff*1500);
		}else if(theReservation.getHotelName().equals("Fairmont Quasar İstanbul")) {
			theModel.addAttribute("price",daysDiff*1000);
		}
		
		theModel.addAttribute("reservation",theReservation);
		theModel.addAttribute("bill",theReservation.getBill());
		theModel.addAttribute("days",daysDiff);
		theModel.addAttribute("orders",orders);
		
		return "check-out";	
		
	}
	
	@PostMapping("/processReservation")
	public String processReservation(@Valid@ModelAttribute("reservation") CrmReservation theCrmReservation,
										BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()){
			
			 return "reservation";
		}
		userService.save(theCrmReservation);
		
		return "redirect:/customer/information";
	}
	
	@PostMapping("/processUpdate")
	public String processUpdate(@ModelAttribute("reservation") Reservation theReservation,
											BindingResult theBindingResult) {
		userService.update(theReservation);
		
		return "redirect:/customer/information";
	}
	
	@PostMapping("/processDeleteReservation")
	public String processDeleteReservation(@RequestParam("reservationId")int theId) {
		
		userService.deleteReservationByReservationId(theId);
		
		return "redirect:/customer/my-reservations";
	}
	
	
	
	@PostMapping("/processCheckOut")
	public String processCheckOut(@RequestParam("reservationId")int theId) {
		
		userService.checkOut(theId);
		
		return "redirect:/customer/my-reservations";
	}
}













