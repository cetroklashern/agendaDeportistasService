package com.agendadeportistas.agendaservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class CursoEntity {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    private String nombre;
    @Column(nullable = false, length = 10)
    private String sexo;
    @Column(nullable = false, length = 20)
    private String edad;
    @Column(nullable = false, length = 10)
    private String clasificacionEdad;    
    @Column(nullable = false, length = 30)
    private String nivel;
    @Column(nullable = true, length = 20)
    private String subNivel;
    @Column(nullable = true, length = 10)
    private String modalidad;
    @Column(nullable = true, length = 10)
    private String categoria;
    @Column(nullable = false, length = 10)
    private int duracionClaseHoras;
    private int duracionClaseMinutos;
    private String color;
}
