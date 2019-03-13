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
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Contact;
import com.example.entities.User;
import com.example.service.ContactPhotoService;
import com.example.service.ContactService;

@Controller
@RequestMapping("/saveContact")
public class SaveContactController {

	private static String UPLOAD_DIR = "/images/contact-photos/";
	private static String UPLOAD_DEF_DIR = "/images/defaultContact.png";
	
	private ContactService contactService;
	private ContactPhotoService photoService;
	
	@Autowired
	public SaveContactController(ContactService contactService, ContactPhotoService photoService) {
		this.contactService=contactService;
		this.photoService=photoService;
	}
	
	@GetMapping
	public String getAddContactsForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "addContact";
	}
	
	@PostMapping
	public String addContact(@Valid Contact contact, Errors errors,  @AuthenticationPrincipal User user,
							@RequestParam("contactPhoto") MultipartFile file) {
		
		if (errors.hasErrors()) {
			return "addContact";
		}
		
		if (file.isEmpty()) {
            contact.setPhotoFilename(UPLOAD_DEF_DIR);
        }else{
			contact.setPhotoFilename(UPLOAD_DIR + photoService.savePhoto(file));
        }
		
		contact.setUser(user);
		contactService.saveContact(contact);
		return "redirect:/home";
	}
}
