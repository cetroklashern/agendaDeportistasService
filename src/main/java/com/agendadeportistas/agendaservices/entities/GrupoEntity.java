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
@Table(name = "grupo")
public class GrupoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long idGrupo;

    private String dia;
    private String horaInicio;
    private String horaFin;
    private int cupos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    @JsonManagedReference
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profesor_id")
    @JsonManagedReference
    private ProfesorEntity profesor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ubicacion_id")
    @JsonManagedReference
    private UbicacionEntity ubicacion;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AgendaEntity> agendas;
}
