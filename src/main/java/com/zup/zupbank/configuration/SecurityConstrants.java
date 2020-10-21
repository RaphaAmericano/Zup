package com.zup.zupbank.configuration;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstrants {

    @Value("${jwt.secret}")
    public static String SECRET;
    public static final long EXPIRATION_TIME = 7200000; // 2 horas
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";

}
