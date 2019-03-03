package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.User;
import com.example.repository.UserRepository;

@Service
public class UserService{

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	public void registerUser(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		userRepo.save(user);
		      
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
		
}
