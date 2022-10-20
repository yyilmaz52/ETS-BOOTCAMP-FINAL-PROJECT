package com.luv2code.springsecurity.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springsecurity.demo.entity.Room;
import com.luv2code.springsecurity.demo.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/information")
	public String getCustomer() {
		return "manager";
	}

	@GetMapping("/rooms-to-clean")
	public String roomsToClean(Model theModel) {
		
		List<Room> roomsToClean = userService.findDirtyRooms();
		List<Room> checkedOutRooms = userService.findCheckedOutRooms();
		
		for(Room tempRoom:checkedOutRooms) {
			if(!roomsToClean.contains(tempRoom)) {
				roomsToClean.add(tempRoom);
			}
		}
		
		theModel.addAttribute("number",roomsToClean.size());
		theModel.addAttribute("dirtyRooms",roomsToClean);
		
		return "rooms-to-clean";
	}
	
	@PostMapping("/clean-rooms")
	public String cleanRooms() {
		
		userService.cleanRooms();
		
		return "redirect:/manager/information";
	}
	
}
