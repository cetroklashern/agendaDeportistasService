package com.agendadeportistas.agendaservices.entities;

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
@Table(name = "deportista_acudiente")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = DeportistaAcudienteEntity.class)
public class DeportistaAcudienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deportista_id")
    private DeportistaEntity deportista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acudiente_id")
    private AcudienteEntity acudiente;

    private String parentesco;
}
