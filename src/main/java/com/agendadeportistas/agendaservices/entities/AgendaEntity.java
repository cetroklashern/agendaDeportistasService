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
@Table(name = "agenda")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAgenda", scope = AgendaEntity.class)
public class AgendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Long idAgenda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deportista_id")
    private DeportistaEntity deportista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_id")
    private GrupoEntity grupo;
}
