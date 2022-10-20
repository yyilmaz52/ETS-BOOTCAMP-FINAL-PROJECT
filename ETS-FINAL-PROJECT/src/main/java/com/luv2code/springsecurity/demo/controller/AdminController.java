package com.luv2code.springsecurity.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springsecurity.demo.entity.KitchenInventory;
import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/information")
	public String getCustomer() {
		return "admin";
	}

	@GetMapping("/inventory-orders")
	public String inventoryOrders(Model theModel) {
		
		List<KitchenInventory> inventoryOrders = userService.findInventoryOrders();
		
		KitchenInventory inventory = userService.getKitchenInventory();
		
		
		theModel.addAttribute("inventory", inventory);
		theModel.addAttribute("inventoryOrders", inventoryOrders);
		
		return "inventory-orders";
	}
	
	@GetMapping("/user-list")
	public String employeeList(Model theModel) {
		
		
		List<User> employees = userService.findEmployees();
		
		
		
		theModel.addAttribute("employees", employees);
		
		return "user-list";
	}
	
	@PostMapping("/processKitchenOrder")
	public String processKitchenOrder(@RequestParam("kitchenOrderId")int theId) {
		
		System.out.println(theId);
		userService.addInventory(theId);
		
		return "redirect:/admin/inventory-orders";
	}
	
	@PostMapping("/processDeleteKitchenOrder")
	public String processDeleteKitchenOrder(@RequestParam("kitchenOrderId")int theId) {
		
		
		userService.deleteKitchenOrderById(theId);
		
		return "redirect:/admin/inventory-orders";
	}
	
	@PostMapping("/processDeleteEmployee")
	public String processDeleteEmployee(@RequestParam("employeeId")long theId) {
		
		
		userService.deleteEmployeeById(theId);
		
		return "redirect:/admin/user-list";
	}
}
