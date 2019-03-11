package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long contactId;
	
	@NotBlank(message="Contact name is required!")
	private String contactName;
	
	@Pattern(regexp="\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})", 
			message="Enter valid phone number!")
	private String phoneNumber;
	
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private ContactGroup groupType; 
	
	private String address;

	@ManyToOne
	private User user;
	
	private String photoPath;
	
}
