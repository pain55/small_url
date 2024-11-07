package com.smallUrl.small_url.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ResponseDTO {
    private @Getter @Setter String smallUrl;
    private @Getter @Setter String originalUrl;
}
