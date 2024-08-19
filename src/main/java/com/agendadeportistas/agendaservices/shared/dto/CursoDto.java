package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class CursoDto {

    private Long idCurso;
    private String nombre;
    private String sexo;
    private String clasificacionEdad;
    private String edad;
    private String nivel;
    private String subNivel;
    private String modalidad;
    private String categoria;
    private int duracionClaseHoras;
    private int duracionClaseMinutos;
    private String color;
}
