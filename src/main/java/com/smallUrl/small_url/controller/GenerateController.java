package com.smallUrl.small_url.controller;

import com.smallUrl.small_url.dto.RequestDTO;
import com.smallUrl.small_url.dto.ResponseDTO;
import com.smallUrl.small_url.service.UrlFoundException;
import com.smallUrl.small_url.service.UrlNotFoundException;
import com.smallUrl.small_url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {
	
	@Autowired
	UrlService urlService;
	@Autowired
	RequestDTO requestDTO;
	@Autowired
	ResponseDTO responseDTO;

	@PostMapping("/generate")
	public ResponseEntity<ResponseDTO> generateUrl(@RequestParam(required = true) String originalUrl, @RequestParam(required = false) String userId) throws UrlFoundException {
		requestDTO.setOriginalUrl(originalUrl);
		requestDTO.setUserID(userId);
		try {
			responseDTO = urlService.generateShortUrl(requestDTO);
		}
		catch (UrlFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
		}
		
		return ResponseEntity.ok(responseDTO);
	}


	@GetMapping("/")
	public ResponseEntity<ResponseDTO> getOriginalUrl(@RequestParam String smallUrl) throws UrlNotFoundException {
		requestDTO.setSmallUrl(smallUrl);
		try{

		responseDTO = urlService.getOriginalUrl(requestDTO);
		}
		catch (UrlNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body(responseDTO);
	}
	
	
}
