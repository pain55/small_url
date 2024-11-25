package com.smallUrl.small_url.service_test;


import com.smallUrl.small_url.dto.RequestDTO;
import com.smallUrl.small_url.dto.ResponseDTO;
import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.repository.UrlRepository;
import com.smallUrl.small_url.service.UrlFoundException;
import com.smallUrl.small_url.service.UrlNotFoundException;
import com.smallUrl.small_url.service.implementation.UrlServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceClassTests {

    @Mock
    UrlRepository urlRepository;

    @InjectMocks
    UrlServiceImpl urlService;

    @InjectMocks
    ResponseDTO responseDTO;


    @Nested
    @DisplayName("This class Tests generateSmallUrl() with multiple unit test cases -->")
    class UrlService_GenerateSmallUrlTests {

        @Test
        @DisplayName("Case 1: where originalUrl exist in db with userId")
        public void urlService_GenerateSmallUrl_ReturnsSmallUrlDto() {

//          creating urlEntity so that when urlRepo's findByOriginalUrl Method is called we can return this mock data.
            UrlEntity urlEntity = UrlEntity.builder().originalUrl("https://mvnrepository.com/artifact/com.h2database/h2/2.2.224").smallUrl("www.shortUrl.com/jhS22S").userId("sai55").build();

//          creating a dummy requestDto request
            RequestDTO requestDTO = RequestDTO.builder().userID("sai55").originalUrl("https://mvnrepository.com/artifact/com.h2database/h2/2.2.224").smallUrl("www.shortUrl.com/jhS22S").build();

//          It means that when urlRepo's findByOriginalUrl() is called instead of hitting the db when return above made mock data(urlEntity).
//          This increases the execution of unit tests and makes test non-deterministic in nature(i.e for same input we should return same output) this is not possible with db becoz the data is updating frequently.
            Mockito.when(urlRepository.findByOriginalUrlAndUserId("https://mvnrepository.com/artifact/com.h2database/h2/2.2.224",requestDTO.getUserID())).thenReturn(Optional.ofNullable(urlEntity));

            Assertions.assertThrows(UrlFoundException.class, () -> {urlService.generateShortUrl(requestDTO);},"Should throw UrlFoundException");
        }


        @Test
        @DisplayName("Case 2: When the originalUrl doesn't exist and it will generate smallUrl")
        public void urlService_GenerateSmallUrl_ReturnsSmallUrlDto_2() throws UrlFoundException {

//          creating urlEntity so that when urlRepo's findByOriginalUrl Method is called we can return this mock data.
            UrlEntity urlEntity=null;
//
//          creating a dummy requestDto request
            RequestDTO requestDTO = RequestDTO.builder().userID("jack").originalUrl("https://www.youtube.com/watch?v=PIoI6JVoq-Q").smallUrl("www.shortUrl.com/jSfFF2").build();


            Mockito.when(urlRepository.findByOriginalUrlAndUserId(requestDTO.getOriginalUrl(), requestDTO.getUserID())).thenReturn(Optional.ofNullable(urlEntity));

            responseDTO = urlService.generateShortUrl(requestDTO);

            Assertions.assertNotNull(responseDTO,"responseDto should not be null");
            Assertions.assertEquals(23, requestDTO.getSmallUrl().length(),"The generated smallUrl should be of length 23--> In which hash is of 6 digits");

        }


    }

    @Nested
    @DisplayName("Testing getOriginalUrl() with variety of test cases!!!")
    class UrlService_GetOriginalUrlTests {

        @Test
        @DisplayName("Case 1: Where smallUrl doesn't exist in the db -> returns UrlNotFoundException.")
        public void urlService_getOriginalUrl_ReturnsResponseDto() {

//          Creating urlEntity obj
            UrlEntity urlEntity = null;

//          creating a dummy requestDto request
            RequestDTO requestDTO = RequestDTO.builder().userID("jack").originalUrl("https://www.youtube.com/watch?v=PIoI6JVoq-Q").smallUrl("www.shortUrl.com/jSfFF2").build();


//            Mocking the urlRepo findBySmallUrl() ----> returns null
            Mockito.when(urlRepository.findBySmallUrl(requestDTO.getSmallUrl())).thenReturn(Optional.ofNullable(urlEntity));

            Assertions.assertThrows(UrlNotFoundException.class, ()-> {urlService.getOriginalUrl(requestDTO);} );

        }

        @Test
        @DisplayName("Case 2: Where smallUrl exist --> returns ResponseDto")
        public void urlService_getOriginalUrl_ReturnsResponseDto_2() throws UrlNotFoundException {
//            Creating dummy urlEntity repo to save into db mock
            UrlEntity urlEntity = UrlEntity.builder().userId("jack").originalUrl("https://www.youtube.com/watch?v=PIoI6JVoq-Q").smallUrl("www.shortUrl.com/jSfFF2").build();
            urlRepository.save(urlEntity);

//          Creating requestDto to mock realtime request to serviceLayer
            RequestDTO requestDTO = RequestDTO.builder().userID("jack").originalUrl("https://www.youtube.com/watch?v=PIoI6JVoq-Q").smallUrl("www.shortUrl.com/jSfFF2").build();

            Mockito.when(urlRepository.findBySmallUrl(requestDTO.getSmallUrl())).thenReturn(Optional.ofNullable(urlEntity));

            ResponseDTO responseDTO = urlService.getOriginalUrl(requestDTO);

//          Asserting the values to expected output.
            Assertions.assertNotNull(responseDTO);
            Assertions.assertEquals(requestDTO.getOriginalUrl(),responseDTO.getOriginalUrl());
        }


    }
}
