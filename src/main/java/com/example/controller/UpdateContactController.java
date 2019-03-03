package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Contact;
import com.example.entities.User;
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
	public String updateContact(@RequestParam("editButton") Long id, Model model) {
		Contact contact=contactService.findContactById(id);
	    model.addAttribute("contact", contact);
		return "editContact";
	}
	
	@PostMapping
	public String submitUpdateForm(@Valid Contact contact, Errors errors, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "editContact";
		}
		contact.setUser(user);
		contactService.saveContact(contact);
		return "redirect:/home";
	}
}
