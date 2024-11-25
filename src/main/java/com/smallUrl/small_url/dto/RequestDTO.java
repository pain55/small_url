package com.smallUrl.small_url.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private  String userID;
    private  String originalUrl;
    private String smallUrl;
}
