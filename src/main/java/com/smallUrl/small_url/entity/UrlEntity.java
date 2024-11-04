package com.smallUrl.small_url.entity;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
public class UrlEntity {

	private String userId;
	private String originalUrl;
	private String smallUrl;
	
//	<dependency>
//	<groupId>org.springframework.boot</groupId>
//	<artifactId>spring-boot-starter-data-jpa</artifactId>
//</dependency>
	
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getOriginalUrl() {
		return originalUrl;
	}



	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}



	public String getSmallUrl() {
		return smallUrl;
	}



	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public UrlEntity() {
		
	}

	public UrlEntity(String userId, String originalUrl, String smallUrl) {
		this.userId = userId;
		this.originalUrl = originalUrl;
		this.smallUrl = smallUrl;
	}

	
	
}
