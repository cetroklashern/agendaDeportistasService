package com.agendadeportistas.agendaservices.entities;

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
@Table(name = "agenda")
public class AgendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Long idAgenda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deportista_id")
    @JsonManagedReference
    private DeportistaEntity deportista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_id")
    @JsonManagedReference
    private GrupoEntity grupo;
}
