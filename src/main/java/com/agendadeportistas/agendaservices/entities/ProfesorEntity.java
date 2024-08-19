package com.agendadeportistas.agendaservices.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

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
@Table(name = "profesor")
public class ProfesorEntity {
    @Id
    @Column(name = "id_profesor")
    private String id;
    private String nombre;
    private String tipoId;
    private String numeroCelular;
    private String direccion;
    private String eps;
    private String arl;
    private String correoElectronico;
    private String nombreContacto;
    private String numeroContacto;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DisponibilidadProfesorEntity> disponibilidades;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<GrupoEntity> grupos;
}
