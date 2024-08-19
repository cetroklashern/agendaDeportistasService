package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class GrupoDto {
    private Long idGrupo;

    private String dia;
    private String horaInicio;
    private String horaFin;
    private int cupos;
    private CursoDto curso;
    private ProfesorDto profesor;
    private UbicacionDto ubicacion;
}
