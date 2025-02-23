package com.agendadeportistas.agendaservices.entities;

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
@Table(name = "grupo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idGrupo", scope = GrupoEntity.class)
public class GrupoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long idGrupo;

    private String dia;
    private String horaInicio;
    private String horaFin;
    private int cupos;

    // Relación con CursoEntity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    // Relación con ProfesorEntity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profesor_id")
    private ProfesorEntity profesor;

    // Relación con UbicacionEntity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ubicacion_id")
    private UbicacionEntity ubicacion;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AgendaEntity> agendas;
}
