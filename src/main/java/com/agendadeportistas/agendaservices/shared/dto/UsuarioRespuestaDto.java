package com.agendadeportistas.agendaservices.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioRespuestaDto {
    private String username;
    private String nombre;
    private String email;
}
