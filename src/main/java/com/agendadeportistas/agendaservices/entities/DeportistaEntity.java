package com.agendadeportistas.agendaservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deportista")
public class DeportistaEntity {
    @Id
    @Column(name = "id_deportista")
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
    @Lob
    private byte[] fotoDeportista;
    @Lob
    private byte[] fotoDocumento;
    private Boolean informacionMensualidad;
    private Boolean informacionReposicion;
    private Boolean informacionVacaciones;
    private Boolean comprobanteInscripcion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)    
    @JoinTable(name = "deportista_acudiente", joinColumns = @JoinColumn(name = "deportista_id", referencedColumnName = "id_deportista")
    ,inverseJoinColumns = @JoinColumn(name = "acudiente_id", referencedColumnName = "id_acudiente"))
    private List<AcudienteEntity> acudientes = new ArrayList<>();

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "deportista_curso", joinColumns = @JoinColumn(name = "deportista_id", referencedColumnName = "id_deportista")
    ,inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id_curso"))
    private List<CursoEntity> cursos = new ArrayList<>();*/
}
