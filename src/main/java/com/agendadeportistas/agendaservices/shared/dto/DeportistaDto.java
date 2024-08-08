package com.agendadeportistas.agendaservices.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeportistaDto {
    private String id;
    private String nombre;
    private int edad;
    private Date fechaNacimiento;
    private String tipoId;
    private String direccion;
    private String eps;
    private String institucionEducativa;
    private int grado;
    private String condicionImportante;
    private boolean imagenPropia;
    private boolean informacionMensualidad;
    private boolean informacionReposicion;
    private boolean informacionVacaciones;
    private boolean comprobanteInscripcion;
    private List<AcudienteDto> acudientes;
}
