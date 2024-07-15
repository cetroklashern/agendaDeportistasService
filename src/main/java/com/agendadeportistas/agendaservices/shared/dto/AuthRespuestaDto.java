package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class AuthRespuestaDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String nombre;

    public AuthRespuestaDto(String accessToken, String nombre){
        this.accessToken = accessToken;
        this.nombre = nombre;
    }
}
