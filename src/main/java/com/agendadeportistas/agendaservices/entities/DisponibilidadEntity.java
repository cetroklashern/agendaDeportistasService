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
@Table(name = "disponibilidad")
public class DisponibilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Long id;

    private String diaDisponibilidad;
    private int horaInicioDisponibilidad;
    private int horaFinDisponibilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id")
    @JsonBackReference
    private UbicacionEntity ubicacion;
}
