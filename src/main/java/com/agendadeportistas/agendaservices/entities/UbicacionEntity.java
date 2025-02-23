package com.agendadeportistas.agendaservices.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ubicacion")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UbicacionEntity.class)
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
    private List<DisponibilidadEntity> disponibilidades;

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GrupoEntity> grupos;
}
