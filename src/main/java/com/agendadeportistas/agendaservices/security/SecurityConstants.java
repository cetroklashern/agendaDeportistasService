package com.agendadeportistas.agendaservices.security;

import com.agendadeportistas.agendaservices.SpringApplicationContext;

public final class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 3600000; // 1 hora
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/auth";

    public static String getSecretKey(){
        AppProperies appPropeties = (AppProperies)SpringApplicationContext.getBean("AppProperties");
        return appPropeties.getSecretKey();
    }
}
