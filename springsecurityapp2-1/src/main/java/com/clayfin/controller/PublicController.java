package com.clayfin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.model.User;
import com.clayfin.repository.UserRepo;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private BCryptPasswordEncoder passwordencoder;

	@GetMapping("/home")
	public String home() {
		return "This is Public Home";
	}

	@PostMapping("/saveUser")
	public String saveUser(@RequestBody User user) {

		System.out.println("Saving user");

		user.setPassword(passwordencoder.encode(user.getPassword()));

		User u = uRepo.save(user);

		return "User Saved" + u;
	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		System.out.println("getting all users");
		return uRepo.findAll();
	}

	@DeleteMapping("/deleteUser/{username}")
	public User deleteUser(@PathVariable String username) {
		System.out.println("Username is " + username);
		User user = uRepo.findById(username).get();
		if (user != null)
			uRepo.delete(user);

		return user;
	}

}
