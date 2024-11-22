package com.smallUrl.small_url.repository_test;


import com.smallUrl.small_url.entity.UrlEntity;
import com.smallUrl.small_url.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource("classpath:test.properties")
@DisplayName("Testing UrlRepository Class Methods")
public class UrlRepoTest {


    UrlRepository urlRepository;

    @Autowired
    public UrlRepoTest(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Test
    @DisplayName("Testing FindByOriginalUrl")
    public void urlRepo_findByOriginalUrl_ReturnUrlEntity() {

        // Arrange
        UrlEntity urlEntity = UrlEntity.builder()
                .userId("sai")
                .originalUrl("https://mvnrepository.com/artifact/com.h2database/h2/2.2ss.224")
                .smallUrl("www.shortUrl.com/Sfs26v")
                .build();

        // Act
        UrlEntity urlEntity1 = urlRepository.save(urlEntity);
        UrlEntity savedUrlEntity = urlRepository.findByOriginalUrl("www.facebook.com/sfasfg232vvafsdvsdfgsd").get();


        Assertions.assertNotNull(savedUrlEntity);
        Assertions.assertEquals("www.shortUrl.com/sdf2sd",savedUrlEntity.getSmallUrl(),"The smallUrl should match");

    }

    @Test
    @DisplayName("Testing findBySmallUrl Method")
    public void urlRepo_findBySmallUrl_ReturnsUrlEntity() {

//    Act
        UrlEntity urlEntity = urlRepository.findBySmallUrl("www.shortUrl.com/sdf2sd").get();


//        Assert
        Assertions.assertNotNull(urlEntity,"Should not be null");
        Assertions.assertEquals("www.facebook.com/sfasfg232vvafsdvsdfgsd",urlEntity.getOriginalUrl(),"The Original Url should match");

    }


    @Test
    @DisplayName("Testing findByOriginalUrlAndUserId Method")
    public void urlRepo_findByOriginalUrlAndUserId_ReturnsUrlEntity() {

        UrlEntity urlEntity = urlRepository.findByOriginalUrlAndUserId("https://mvnrepository.com/artifact/com.h2database/h2/2.2.224","sai55").get();

        Assertions.assertNotNull(urlEntity,"Should not be null");
        Assertions.assertEquals("www.shortUrl.com/jhS22S",urlEntity.getSmallUrl(),"The shortUrl should match");

    }



}
