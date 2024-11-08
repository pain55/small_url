package com.smallUrl.small_url.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter @NoArgsConstructor
@Component
public class RequestDTO {
    private  String userID;
    private  String originalUrl;
    private String smallUrl;
}
