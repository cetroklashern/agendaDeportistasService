package com.agendadeportistas.agendaservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperies {
    @Autowired
    private Environment environment;

    public String getSecretKey(){
        return environment.getProperty("app.secret.key");
    }
}
