package com.smallUrl.small_url.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "url_entity")
public class UrlEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "url_id")
	private long urlId;

	@Column(name = "user_id")
	private String userId;
	@Column(name = "original_url")
	private String originalUrl;
	@Column(name = "small_url")
	private String smallUrl;


	public UrlEntity(String userId, String originalUrl, String smallUrl) {
		this.userId = userId;
		this.originalUrl = originalUrl;
		this.smallUrl = smallUrl;
	}

	
	
}
