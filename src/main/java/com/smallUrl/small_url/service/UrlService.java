package com.smallUrl.small_url.service;

import org.springframework.stereotype.Service;

import com.smallUrl.small_url.entity.UrlEntity;

@Service
public interface UrlService {
	
	public UrlEntity generateShorternUrl(String originalUrl);
}