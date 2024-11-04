package com.smallUrl.small_url.service.implementation;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.service.UrlService;


@Service
public class UrlServiceImpl implements UrlService{

	
	
	
	@Autowired
	UrlEntity urlEntity;
	
	@Override
	public UrlEntity generateShorternUrl(String originalUrl) {
		final int shortUrlLength = 6;
		final String urlHash = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		final Random random = new Random();
		StringBuffer shortUrl = new StringBuffer("www.shortUrl.com/");
		
		
		for(int i=0 ; i<shortUrlLength ; i++) {
			int randomIndex = random.nextInt(urlHash.length());
			shortUrl.append(urlHash.charAt(randomIndex));
		}
		
		urlEntity.setOriginalUrl(originalUrl);
		urlEntity.setSmallUrl(shortUrl.toString());
		
		
		return urlEntity;
		
		
	}

}
