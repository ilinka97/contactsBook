package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long contactId;
	
	private String contactName;
	
	private String phoneNumber;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private ContactGroup groupType; 
	
	private String address;

}
