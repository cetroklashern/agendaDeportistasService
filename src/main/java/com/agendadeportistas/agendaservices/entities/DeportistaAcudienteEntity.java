package com.agendadeportistas.agendaservices.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deportista_acudiente")
public class DeportistaAcudienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deportista_id")
    @JsonBackReference
    private DeportistaEntity deportista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acudiente_id")
    @JsonBackReference
    private AcudienteEntity acudiente;

    private String parentesco;
}
