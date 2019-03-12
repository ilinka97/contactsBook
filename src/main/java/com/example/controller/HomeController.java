package com.example.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.Contact;
import com.example.entities.User;
import com.example.service.ContactPhotoService;
import com.example.service.ContactService;

@Controller
@RequestMapping("/home")
public class HomeController {

	private ContactService contactService;
	private ContactPhotoService photoService;
	
	@Autowired
	public HomeController(ContactService contactService, ContactPhotoService photoService) {
		this.contactService=contactService;
		this.photoService=photoService;
	}
	
	@GetMapping("/{filename}")
	@ResponseBody
	public ResponseEntity<?> showOnePhoto(@PathVariable String filename){
		
		try {
			Resource file=photoService.findOnePhoto(filename);
			return ResponseEntity.ok()
					.contentLength(file.contentLength())
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(file.getInputStream()));
		} catch (IOException e) {
			return ResponseEntity.badRequest()
					.body("Couldn't find "+filename+e.getMessage());
		}
		
	}
	
	@GetMapping
	public String showAllContacts(Model model, @AuthenticationPrincipal User user) {
		
		List<Contact> contacts=contactService.findAllByUser(user);
		model.addAttribute("contacts", contacts);
		return "home";
		
	}
	
	@PostMapping("/searchContacts")
	public String searchContacts(@RequestParam("searchField") String contactName, Model model, @AuthenticationPrincipal User user) {
		
		List<Contact> searchResults=contactService.findAllByNameWhereUser(user, contactName);
		model.addAttribute("searchResults", searchResults);
		return "searchContacts";
		
	}
	
	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("deleteButton") Long id) {
		
		contactService.deleteContact(id);
		return "redirect:/home";
		
	}
}
