package com.smallUrl.small_url.service;

public class UrlFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Url already exist for the given User Id";
    }
}
