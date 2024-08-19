package com.agendadeportistas.agendaservices.shared.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UbicacionDto {
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;
    private String nombreContacto;
    private Boolean estado;
    private Date fechaInicioContrato;
    private Date fechaFinContrato;
    private List<DisponibilidadDto> disponibilidades;
}
