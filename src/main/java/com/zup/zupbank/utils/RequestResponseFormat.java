package com.zup.zupbank.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RequestResponseFormat {
    public static HttpHeaders setHeaderLocation(String host, int port, String path ){
        URI location = UriComponentsBuilder.newInstance().scheme("http").host(host).port(port).path(path).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, location.toString());
        headers.setLocation(location);
        return headers;
    }
}
