package com.example.dto;

import lombok.Data;

@Data
public class UserDto {

	private String username;
	private String email;
	private String password;
	
	public UserDto(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
		
}
