package com.clayfin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/home")
	public String home() {

		return "This is User Home";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "This is User Profile ";
	}

}
