package com.smallUrl.small_url.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlConfig {

    private @Getter static final int shortUrlLength =6;
    private @Getter static final String urlHash = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";




}
