package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Contact;
import com.example.service.ContactService;

@Controller
@RequestMapping("/home")
public class HomeController {

	private ContactService contactService;
	
	@Autowired
	public HomeController(ContactService contactService) {
		this.contactService=contactService;
	}
	
	@GetMapping
	public String showAllContacts(Model model) {
		
		List<Contact> contacts=contactService.findAllContacts();
		model.addAttribute("contacts", contacts);
		return "home";
		
	}
	
	@PostMapping("/searchContacts")
	public String searchContacts(@RequestParam("searchField") String contactName, Model model) {
		
		List<Contact> searchResults=contactService.findAllByName(contactName);
		model.addAttribute("searchResults", searchResults);
		return "searchContacts";
		
	}
	
}
