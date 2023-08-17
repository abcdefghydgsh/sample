package com.jsp.ContactBook.EXCEL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.ContactBook.DTO.Contact;

@Service
public class ExcelService {

	public List<Contact> parseExcelFile(MultipartFile file) throws EncryptedDocumentException, IOException {
		List<Contact> contacts = new ArrayList<Contact>();

		Workbook book = WorkbookFactory.create(file.getInputStream());

		Sheet sheet = book.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();

		while(rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			
			String name = row.getCell(0).getStringCellValue();
			String number = row.getCell(1).getStringCellValue();
			
			Contact con = new Contact();
			con.setName(name);
			con.setPhone(Long.parseLong(number));
			
			contacts.add(con);
			
		}
		
		return contacts;
	}

}
