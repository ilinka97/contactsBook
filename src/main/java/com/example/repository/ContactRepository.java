package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{

}
