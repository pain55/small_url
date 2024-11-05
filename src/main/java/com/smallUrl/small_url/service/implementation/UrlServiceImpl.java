package com.smallUrl.small_url.service.implementation;

import java.util.Random;

import com.smallUrl.small_url.config.UrlConfig;
import com.smallUrl.small_url.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.service.UrlService;


@Service
public class UrlServiceImpl implements UrlService{

	
	
	
	@Autowired
	UrlEntity urlEntity;

	@Autowired
	UrlConfig urlConfig;

	@Autowired
	ResponseDTO responseDTO;
	
	@Override
	public ResponseDTO generateShortUrl(String originalUrl) {


		final Random random = new Random();
		StringBuffer shortUrl = new StringBuffer("www.shortUrl.com/");
		
		
		for(int i=0 ; i<urlConfig.getShortUrlLength() ; i++) {
			int randomIndex = random.nextInt(urlConfig.getUrlHash().length());
			shortUrl.append(urlConfig.getUrlHash().charAt(randomIndex));
		}
		
		urlEntity.setOriginalUrl(originalUrl);
		urlEntity.setSmallUrl(shortUrl.toString());
		responseDTO.setShortUrl(shortUrl.toString());


		
		
		return responseDTO;
		
		
	}

}
