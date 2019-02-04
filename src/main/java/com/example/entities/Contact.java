package com.example.entities;

import javax.persistence.Column;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long contactId;
	
	private String contactName;
	private String phoneNumber;
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Group group; 
	
	private String address;
	
	
}
