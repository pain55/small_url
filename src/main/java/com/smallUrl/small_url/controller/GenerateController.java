package com.smallUrl.small_url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.service.UrlService;

@RestController
public class GenerateController {
	
	@Autowired
	UrlService urlService;
	
	@PostMapping("/generate")
	public ResponseEntity<UrlEntity> generateUrl(@RequestParam String originalUrl) {
		UrlEntity urlEntity = urlService.generateShorternUrl(originalUrl);
		
		return new ResponseEntity<UrlEntity>(urlEntity, HttpStatusCode.valueOf(200));
	}
	
	
	
}
