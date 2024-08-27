package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.entities.DeportistaEntity;

@Repository
public interface DeportistaRepository extends JpaRepository<DeportistaEntity, String> {
    Optional<DeportistaEntity> findByNombre(String nombre);

    @SuppressWarnings("null")
    Optional<DeportistaEntity> findById(String id);

    Boolean existsByNombre(String nombre);

    @SuppressWarnings("null")
    boolean existsById(String id);

    List<DeportistaEntity> findByNombreContaining(String nombre);

    List<DeportistaEntity> findByIdContaining(String id);
}
