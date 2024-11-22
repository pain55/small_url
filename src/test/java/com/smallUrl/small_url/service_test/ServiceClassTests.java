package com.smallUrl.small_url.service_test;


import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.repository.UrlRepository;
import com.smallUrl.small_url.service.UrlFoundException;
import com.smallUrl.small_url.service.UrlService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceClassTests {
    @Autowired
    UrlService urlService;

    @MockBean
    UrlRepository urlRepository;

//    @Test
//    public void generateShortUrlTest() throws Exception{
//
//
//        String originalUrl = "https://hianime.to/home";
////
//        when(urlRepository.findByOriginalUrl(originalUrl)).thenReturn((UrlEntity) Stream.of(new UrlEntity("jack","https://hianime.to/home","www.shortUrl.com/TV50s3W"),
//                new UrlEntity(null,"https://hianime.to/home","www.shortUrl.com/dSH8dA")).collect(Collectors.toList()));
//
//
//
//
//
//
//
//    }
}
