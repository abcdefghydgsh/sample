package com.jsp.ContactBook.CONTROLLER;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.ContactBook.DTO.Contact;
import com.jsp.ContactBook.EXCEL.ExcelService;
import com.jsp.ContactBook.SERVICE.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	ContactService service;
	
	@Autowired
	ExcelService excel;
	
	// api to add contact
	@PostMapping
	public ResponseEntity<Contact> addContact(@RequestBody Contact con)
	{
		Contact savedContact = service.addContact(con);
		return ResponseEntity.ok(savedContact);
	}
	
	// api to get all the contacts
	@GetMapping
	public ResponseEntity<List<Contact>> findAllContact()
	{
		List<Contact> list = service.getAllContacts();
		return ResponseEntity.ok(list);
	}
	
	// api to get a contact based on ID
	@GetMapping("/byid")
	public ResponseEntity<Contact> searchById(@RequestParam int id)
	{
		Contact con = service.searchContact(id);
		if(con != null)
		{
			return ResponseEntity.ok(con);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// api to update contact in DB
	@PutMapping
	public ResponseEntity<Contact> updateContact(@RequestParam String name, @RequestParam int id)
	{
		Contact updatedContact = service.updateContact(name, id);
		if(updatedContact != null)
		{
			return ResponseEntity.ok(updatedContact);
		}
		return ResponseEntity.notFound().build();
	}
	
	// api to delete contact in DB
	@DeleteMapping
	public ResponseEntity<Void> deleteContact(@RequestParam int id)
	{
		 int a = service.removeContact(id);
		 if(a == 1)
		 {
			 return ResponseEntity.ok().build();
		 }
		 else
		 {
			 return ResponseEntity.notFound().build();
		 }
	}
	
	// api to upload file to database.
	@PostMapping("/upload")
	public ResponseEntity<List<Contact>> uploadFile(@RequestParam("file") MultipartFile file) throws EncryptedDocumentException
	{
		try
		{
		 List<Contact> list = excel.parseExcelFile(file);
		 for(Contact c : list)
		 {
			 service.addContact(c);
		 }
		 return ResponseEntity.ok(list);
		}
		catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}













