package com.example.ImageService.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ImageService.exception.StorageException;
import com.example.ImageService.service.StorageService;

@Controller
public class ImageController {

	@Autowired
	private StorageService storageService;
	
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST, consumes =  {"multipart/form-data"})
	public String upload(@RequestParam("file") MultipartFile file) {

        storageService.uploadFile(file);

        return "done";
       // return "redirect:/success.html";
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String id) throws IOException {

        PathResource imgFile = new PathResource("/Users/anjan/image/"+id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
	
	@ExceptionHandler(StorageException.class)
	public String handleStorageFileNotFound(StorageException e) {
		return "redirect:/failure.html";
	}
}
