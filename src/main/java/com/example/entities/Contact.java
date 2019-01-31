package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long contactId;
	private String contactName;
	private String phoneNumber;
	private String email;
	private Group group;
	private String address;
	
	public static enum Group{
		FAMILY,WORK,FRIENDS
	}
	
}
