package com.clayfin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clayfin.model.User;
import com.clayfin.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo uRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user =  uRepo.findById(username).get();
		 
		 if(user==null)
			 throw new UsernameNotFoundException("User not found with username "+username);
		
		 System.out.println("User loaded from database "+user);
		 return new CustomUserDetails(user);
	}

}
