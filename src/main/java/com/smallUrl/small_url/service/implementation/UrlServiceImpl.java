package com.smallUrl.small_url.service.implementation;

import com.smallUrl.small_url.config.UrlConfig;
import com.smallUrl.small_url.dto.RequestDTO;
import com.smallUrl.small_url.dto.ResponseDTO;
import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.repository.UrlRepository;
import com.smallUrl.small_url.service.UrlFoundException;
import com.smallUrl.small_url.service.UrlNotFoundException;
import com.smallUrl.small_url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UrlServiceImpl implements UrlService{




	@Autowired
	RequestDTO requestDTO;

	@Autowired
	UrlConfig urlConfig;

	@Autowired
	ResponseDTO responseDTO;

	@Autowired
	UrlRepository urlRepository;
	
	@Override
	public ResponseDTO generateShortUrl() throws UrlFoundException {

//		Getting the user from DB.

		UrlEntity urlEntity = urlRepository.findByOriginalUrl(requestDTO.getOriginalUrl());



//		checking if the original url already exist in db and userId is same.
//		if yes -> we return exception has already exist
//		else -> we create new tuple.
//		we will write proper exception handling for this edge case.

		if( urlEntity != null && requestDTO.getUserID()!=null && requestDTO.getUserID().equals(urlEntity.getUserId())) {
			System.out.println("The original url already exist!!!!!!");
			throw new UrlFoundException();
		}

		if(urlEntity==null)
			urlEntity = new UrlEntity();

		final Random random = new Random();
		StringBuffer newSmallUrl = new StringBuffer("www.shortUrl.com/");

		while(true) {
			for(int i=0 ; i<urlConfig.getShortUrlLength() ; i++) {
				int randomIndex = random.nextInt(urlConfig.getUrlHash().length());
				newSmallUrl.append(urlConfig.getUrlHash().charAt(randomIndex));
			}
			if(!containsSmallUrl(newSmallUrl.toString()))
				break;
//
		}

		System.out.println(requestDTO.getUserID());

//		mapping it to userEntity
		urlEntity.setUserId(requestDTO.getUserID());
		urlEntity.setOriginalUrl(requestDTO.getOriginalUrl());
		urlEntity.setSmallUrl(newSmallUrl.toString());
		urlRepository.save(urlEntity);

//		sending response
		responseDTO.setSmallUrl(newSmallUrl.toString());
		responseDTO.setOriginalUrl(requestDTO.getOriginalUrl());
		
		return responseDTO;

	}

	@Override
	public ResponseDTO getOriginalUrl() throws UrlNotFoundException {

			UrlEntity urlEntity = urlRepository.findBySmallUrl(requestDTO.getSmallUrl());
			if(urlEntity == null)
				throw new UrlNotFoundException();
			responseDTO.setOriginalUrl(urlEntity.getOriginalUrl());
			responseDTO.setSmallUrl(requestDTO.getSmallUrl());

			return responseDTO;

	}



	private boolean containsSmallUrl(String smallUrl) {
		return urlRepository.findBySmallUrl(smallUrl) != null;
	}

}
