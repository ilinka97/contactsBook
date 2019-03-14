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

	private static String UPLOAD_FOLDER = "/Users/Danijel&Ika/Documents/contact-book-images/";
	
	private ResourceLoader resourceLoader;
	
	@Autowired
	public ContactPhotoService(ResourceLoader resourceLoader){
		this.resourceLoader=resourceLoader;
	}
	
	public String savePhoto(MultipartFile file) {
		
		try{
			byte[] bytes = file.getBytes();
	        Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
	        Files.write(path, bytes);
	    }catch(IOException e){
	        e.printStackTrace();
	    }
		
		return file.getOriginalFilename();
	}
	
	public Resource findOnePhoto(String photoFilename) {
		return resourceLoader.getResource(photoFilename);
	}
	
	public void deletePhoto(String photoFilename) throws IOException {
		Files.deleteIfExists(Paths.get(UPLOAD_FOLDER, photoFilename));
	}
}
