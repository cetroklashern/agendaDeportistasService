package com.agendadeportistas.agendaservices.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoLightDto {
    private Long idGrupo;

    private String dia;
    private String horaInicio;
    private String horaFin;
    private int cupos;
}
