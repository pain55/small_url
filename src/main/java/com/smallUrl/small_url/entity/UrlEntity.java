package com.smallUrl.small_url.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Entity
@Getter @Setter @NoArgsConstructor
public class UrlEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long urlId;

	private String userId;
	private String originalUrl;
	private String smallUrl;


	public UrlEntity(String userId, String originalUrl, String smallUrl) {
		this.userId = userId;
		this.originalUrl = originalUrl;
		this.smallUrl = smallUrl;
	}

	
	
}
