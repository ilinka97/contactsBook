package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{
	
	List<Contact> findByContactNameContaining(String contactName);
	Contact findByContactId(Long contactId);
}
