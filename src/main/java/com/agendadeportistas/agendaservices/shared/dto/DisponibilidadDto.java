package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class DisponibilidadDto {
    private Long id;
    private String diaDisponibilidad;
    private int horaInicioDisponibilidad;
    private int horaFinDisponibilidad;
}
