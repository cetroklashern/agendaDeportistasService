package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class RegistroDto {
    private String username;
    private String nombre;
    private String email;
    private String password;
}
