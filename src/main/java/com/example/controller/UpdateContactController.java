package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ContactService;

@Controller
@RequestMapping("/updateContact")
public class UpdateContactController {

	private ContactService contactService;
	
	@Autowired
	public UpdateContactController(ContactService contactService) {
		this.contactService=contactService;
	}
	
	@GetMapping
	public String updateContact() {
		return "editContact";
	}
}
