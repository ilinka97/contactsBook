package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContactPhotoService {

	private static String UPLOADED_FOLDER = "C:\\Users\\Danijel&Ika\\Documents\\contact-book-images\\";
	
	private ResourceLoader resourceLoader;
	
	@Autowired
	public ContactPhotoService(ResourceLoader resourceLoader){
		this.resourceLoader=resourceLoader;
	}
	
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
	
	public Resource findOnePhoto(String photoPath) {
		return resourceLoader.getResource(photoPath);
	}
}
