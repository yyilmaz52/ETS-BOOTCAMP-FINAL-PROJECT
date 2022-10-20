package com.luv2code.springsecurity.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.luv2code.springsecurity.demo.entity.Order;
import com.luv2code.springsecurity.demo.entity.Room;
import com.luv2code.springsecurity.demo.service.UserService;


@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	public UserService userService;
	
	@GetMapping("/my-orders")
	public String getMyOrders(@RequestParam("roomId")int theId,Model theModel) {
		
		List<Order> myOrders = userService.findMyOrdersByRoomId(theId);
		theModel.addAttribute("orders",myOrders);
		
		return "my-orders";
	}
	
	@PostMapping("/addToBasket")
	public String addToBasket(@ModelAttribute("order") Order theOrder,@RequestParam("roomId")int theId) {
		
		Room theRoom = userService.findRoomById(theId);
		userService.saveFood(theRoom, theOrder);
			
		return "redirect:/customer/information";
	}
	
}










