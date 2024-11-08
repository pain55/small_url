package com.smallUrl.small_url.service;

import com.smallUrl.small_url.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import com.smallUrl.small_url.entity.UrlEntity;

@Service
public interface UrlService {
	
	public ResponseDTO generateShortUrl() throws UrlFoundException;

	public ResponseDTO getOriginalUrl() throws UrlNotFoundException;
}