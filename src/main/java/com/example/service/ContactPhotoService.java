package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContactPhotoService {

	private static String UPLOADED_FOLDER = "C:\\Users\\Danijel&Ika\\Documents\\contact-book-images\\";
	
	public String savePhoto(MultipartFile file) {
		
		String absolutePath="";
		
		try{
			byte[] bytes = file.getBytes();
	        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	        Files.write(path, bytes);
	        absolutePath= path.toAbsolutePath().toString();
	    }catch(IOException e){
	        e.printStackTrace();
	    }
		
		return absolutePath;
	}
	
}
