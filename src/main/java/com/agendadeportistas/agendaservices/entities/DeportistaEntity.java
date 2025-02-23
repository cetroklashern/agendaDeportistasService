package com.agendadeportistas.agendaservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deportista")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = DeportistaEntity.class)
public class DeportistaEntity {
    @Id
    @Column(name = "id_deportista")
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
    private Boolean imagenPropia;
    @Lob
    @JsonIgnore
    private byte[] fotoDeportista;
    @Lob
    @JsonIgnore
    private byte[] fotoDocumento;
    private Boolean informacionMensualidad;
    private Boolean informacionReposicion;
    private Boolean informacionVacaciones;
    private Boolean comprobanteInscripcion;

    @OneToMany(mappedBy = "deportista", cascade = CascadeType.ALL)
    private List<DeportistaAcudienteEntity> deportistaAcudientes;

    @OneToMany(mappedBy = "deportista", cascade = CascadeType.ALL)
    private List<AgendaEntity> agendas;
}
