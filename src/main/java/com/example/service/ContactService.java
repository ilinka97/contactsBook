package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Contact;
import com.example.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository contactRepo;
	
	@Autowired
	public ContactService(ContactRepository contactRepo) {
		this.contactRepo=contactRepo;
	}
	
	public List<Contact> findAllContacts() {
		Iterable<Contact> contactsDAO=contactRepo.findAll();
		List<Contact> contacts=new ArrayList<>();
		
		for (Contact contact : contactsDAO) {
			contacts.add(contact);
		}
		return contacts;
		
	}
	
	public void saveContact(Contact contact) {
		contactRepo.save(contact);	
	}
	
	public List<Contact> findAllByName(String contactName){
		return contactRepo.findByContactNameContaining(contactName);
	}
	
 }
