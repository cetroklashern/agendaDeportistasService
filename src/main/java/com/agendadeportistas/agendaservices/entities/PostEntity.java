package com.agendadeportistas.agendaservices.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    long idPost;
    
    String titulo;
    String contenido;

    @CreatedDate
    Date creado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    UsuariosEntity usuario;

    @ManyToOne
    @JoinColumn(name = "exposure_id")
    ExposureEntity exposure;
}
