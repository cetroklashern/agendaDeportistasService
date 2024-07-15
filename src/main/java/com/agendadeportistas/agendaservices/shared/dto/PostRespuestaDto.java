package com.agendadeportistas.agendaservices.shared.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostRespuestaDto {
    String titulo;
    String contenido;
    Date creado;
    Long exposureId;
    UsuarioRespuestaDto user;
}
