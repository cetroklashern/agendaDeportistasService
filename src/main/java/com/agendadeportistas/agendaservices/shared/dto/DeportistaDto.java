package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DeportistaDto {
    private String id;
    private String nombre;
    private Number edad;
    private Date fechaNacimiento;
    private String tipoId;
    private String direccion;
    private String eps;
    private String institucionEducativa;
    private Number grado;
    private String condicionImportante;
    private Boolean imagenPropia;
    private String fotoDeportistaUrl;
    private String fotoDocumentoUrl;
    private Boolean informacionMensualidad;
    private Boolean informacionReposicion;
    private Boolean informacionVacaciones;
    private Boolean comprobanteInscripcion;
}
