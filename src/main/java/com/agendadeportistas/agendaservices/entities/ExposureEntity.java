package com.agendadeportistas.agendaservices.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exposure")
public class ExposureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposure")
    Long idExposure;
    String tipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="exposure")
    private List<PostEntity> posts = new ArrayList<>();
}
