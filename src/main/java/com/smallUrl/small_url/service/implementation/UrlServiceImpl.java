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

import java.util.Optional;
import java.util.Random;


@Service
public class UrlServiceImpl implements UrlService{

	@Autowired
	UrlConfig urlConfig;

	@Autowired
	ResponseDTO responseDTO;

	@Autowired
	UrlRepository urlRepository;
	
	@Override
	public ResponseDTO generateShortUrl(RequestDTO requestDTO) throws UrlFoundException {


//		If given Original Url already exit in db on this user then we throw UrlFoundException.

		if(urlRepository.findByOriginalUrlAndUserId(requestDTO.getOriginalUrl(), requestDTO.getUserID()).isPresent()){
			System.out.println("The Original Url already exist for userId");
			throw new UrlFoundException();
		}



		final Random random = new Random();
		StringBuffer newSmallUrl = new StringBuffer("www.shortUrl.com/");

		while(true) {
			for(int i=0 ; i<urlConfig.getShortUrlLength() ; i++) {
				int randomIndex = random.nextInt(urlConfig.getUrlHash().length());
				newSmallUrl.append(urlConfig.getUrlHash().charAt(randomIndex));
			}
			if( urlRepository.findBySmallUrl(newSmallUrl.toString()).isEmpty() )
				break;
		}


		UrlEntity urlEntity = new UrlEntity();

//		mapping it to userEntity
		urlEntity.setUserId(requestDTO.getUserID());
		urlEntity.setOriginalUrl(requestDTO.getOriginalUrl());
		urlEntity.setSmallUrl(newSmallUrl.toString());
		urlRepository.save(urlEntity);

		responseDTO = new ResponseDTO();
//		sending response
		responseDTO.setSmallUrl(newSmallUrl.toString());
		responseDTO.setOriginalUrl(requestDTO.getOriginalUrl());
		
		return responseDTO;

	}

	@Override
	public ResponseDTO getOriginalUrl(RequestDTO requestDTO) throws UrlNotFoundException {

			Optional<UrlEntity> urlEntity = urlRepository.findBySmallUrl(requestDTO.getSmallUrl());

//			If it returns an empty object then throw UrlNotFoundException
			if(urlEntity.isEmpty())
				throw new UrlNotFoundException();

//			Creating a responseDTO instance to set values and return it
			responseDTO = new ResponseDTO();
			responseDTO.setOriginalUrl((urlEntity.get()).getOriginalUrl());
			responseDTO.setSmallUrl(requestDTO.getSmallUrl());

			return responseDTO;

	}




}
