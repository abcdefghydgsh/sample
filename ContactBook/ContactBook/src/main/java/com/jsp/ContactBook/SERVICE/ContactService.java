package com.jsp.ContactBook.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.ContactBook.DTO.Contact;
import com.jsp.ContactBook.REPOSITORY.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository repository;
	
	// to save contact object into DB
	public Contact addContact(Contact con)
	{
		return repository.save(con);
	}
	
	// to search for a contact in DB
	public Contact searchContact(int id)
	{
		Optional<Contact> opt = repository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		return null;
	}
	
	// to remove a contact from DB
	public int removeContact(int id)
	{
		Contact c = searchContact(id);
		if(c != null)
		{
			repository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	// to get All Contacts from DB
	public List<Contact> getAllContacts()
	{
		return repository.findAll();
	}
	
	// to update contact name in DB
	public Contact updateContact(String name, int id)
	{
		Contact c = searchContact(id);
		if(c != null)
		{
			c.setName(name);
			repository.save(c);
			return c;
		}
		return null;
	}
}






