package com.agendadeportistas.agendaservices.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acudiente")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AcudienteEntity.class)
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
    private List<DeportistaAcudienteEntity> deportistaAcudientes;
}