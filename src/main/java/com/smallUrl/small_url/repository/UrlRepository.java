package com.smallUrl.small_url.repository;

import com.smallUrl.small_url.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findByOriginalUrl(String originalUrl);
    Optional<UrlEntity> findByOriginalUrlAndUserId(String originalUrl, String userId);
    Optional<UrlEntity> findBySmallUrl(String smallUrl);


}
