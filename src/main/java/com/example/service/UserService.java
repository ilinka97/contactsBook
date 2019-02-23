package com.example.service;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dto.UserDto;
import com.example.entities.User;
import com.example.repository.UserRepository;

public class UserService{

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	public User registerUserAccount(UserDto userDto){
		         
		if (emailExists(userDto.getEmail())) {   
            throw new EntityExistsException(
              "There is already an account with that email address:" + userDto.getEmail());
        }
		        User user = new User();    
		        user.setUsername(userDto.getUsername());
		        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		        user.setEmail(userDto.getEmail());
		        return userRepo.save(user);       
	 	}
	
		private boolean emailExists(String email) {
			  return userRepo.findByEmail(email) != null;
		}
		
}
