package com.agendadeportistas.agendaservices.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaLightDto {
    Long idAgenda;
    DeportistaLightDto deportista;
    GrupoLightDto grupo;
}
