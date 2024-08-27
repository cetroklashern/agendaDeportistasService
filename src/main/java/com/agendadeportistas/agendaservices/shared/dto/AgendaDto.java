package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class AgendaDto {
    Long idAgenda;
    DeportistaDto deportista;
    GrupoDto grupo;
}
