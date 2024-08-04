package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class AcudienteDto {
    private String id;
    private String nombre;
    private String tipoId;
    private Number numeroCelular;
    private String direccion;
    private String correoElectronico;
    private Boolean imagenPropia;
    private String profesionEmpresa;
    private String parentesco;
}