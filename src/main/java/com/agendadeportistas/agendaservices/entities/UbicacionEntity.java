package com.agendadeportistas.agendaservices.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ubicacion")
public class UbicacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;
    private String nombreContacto;
    private Boolean estado;
    private Date fechaInicioContrato;
    private Date fechaFinContrato;

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DisponibilidadEntity> disponibilidades;

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<GrupoEntity> grupos;
}
