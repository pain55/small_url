package com.smallUrl.small_url.controller;

import com.smallUrl.small_url.dto.ResponseDTO;
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
	@Autowired
	ResponseDTO responseDTO;

	@PostMapping("/generate")
	public ResponseEntity<ResponseDTO> generateUrl(@RequestParam String originalUrl) {
		ResponseDTO urlEntity = urlService.generateShortUrl(originalUrl);
		
		return ResponseEntity.ok(responseDTO);
	}
	
	
	
}
