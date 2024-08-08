package com.agendadeportistas.agendaservices.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acudiente")
public class AcudienteEntity {
    @Id
    @Column(name = "id_acudiente")
    private String id;
    private String nombre;
    private String tipoId;
    private Long numeroCelular;
    private String direccion;
    private String correoElectronico;
    private Boolean imagenPropia;
    private String profesionEmpresa;

    @OneToMany(mappedBy = "acudiente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DeportistaAcudienteEntity> deportistaAcudientes;
}