package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Contact;
import com.example.service.ContactService;

@Controller
@RequestMapping("/saveContact")
public class SaveContactController {

	private ContactService contactService;
	
	@Autowired
	public SaveContactController(ContactService contactService) {
		this.contactService=contactService;
	}
	
	@GetMapping
	public String getAddContactsForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "addContact";
	}
	
	@PostMapping
	public String addContact(@Valid Contact contact, Errors errors) {
		if (errors.hasErrors()) {
			return "addContact";
		}
		contactService.saveContact(contact);
		return "redirect:/home";
	}
}
