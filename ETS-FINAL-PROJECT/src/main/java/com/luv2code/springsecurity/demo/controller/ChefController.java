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

import com.luv2code.springsecurity.demo.entity.KitchenInventory;
import com.luv2code.springsecurity.demo.entity.Order;
import com.luv2code.springsecurity.demo.service.UserService;


@Controller
@RequestMapping("/chef")
public class ChefController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/information")
	public String getCustomer(Model theModel) {
		
		return "chef";
	}

	@GetMapping("/orders")
	public String getOrders(Model theModel) {
		
		List<Order> orders = userService.findOrders();
		
		
		theModel.addAttribute("orders",orders);
		
		return "orders";
	}
	
	
	@GetMapping("/order-form-for-inventory")
	public String orderForInventory(Model theModel) {
		
		KitchenInventory kitchenInventory = new KitchenInventory();
		
		
		theModel.addAttribute("inventory",kitchenInventory);
		
		return "order-for-inventory";
	}
	
	@PostMapping("/processOrderForInventory")
	public String orderForInventory(@ModelAttribute("inventory") KitchenInventory theKitchenInventory) {
		
		
		
		userService.save(theKitchenInventory);
		
		return "redirect:/chef/information";
		
	}
	
	@PostMapping("deliver-order")
	public String deliverOrder(@RequestParam("orderId")int theId) {
		
		
		Order order = userService.findOrderById(theId);
		KitchenInventory kitchenInventory = userService.getKitchenInventory();
		
		userService.updateKitchenInventory(kitchenInventory,order);
		
		userService.saveStatus(order);
		
		return "redirect:/chef/orders";
		
	}
}









