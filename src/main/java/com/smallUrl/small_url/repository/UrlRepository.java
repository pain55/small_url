package com.smallUrl.small_url.repository;

import com.smallUrl.small_url.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    UrlEntity findByOriginalUrl(String originalUrl)  ;
    UrlEntity findBySmallUrl(String smallUrl);

}
