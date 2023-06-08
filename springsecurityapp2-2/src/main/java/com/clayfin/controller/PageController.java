package com.clayfin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

	@GetMapping(value = { "/","/home"})
	public String Homee() {
		System.out.println("calling Homee page");
		return "home"; // returns login.html
	}

	@GetMapping("/login")
	public String loginPage() {
		System.out.println("calling login page");
		return "login"; // returns login.html
	}
	
	@GetMapping("/login?error")
	public String loginPageError() {
		System.out.println("calling login error page");
		return "error"; // returns login.html
	}


	

}
