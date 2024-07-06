package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class AuthRespuestaDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthRespuestaDto(String accessToken){
        this.accessToken = accessToken;
    }
}
