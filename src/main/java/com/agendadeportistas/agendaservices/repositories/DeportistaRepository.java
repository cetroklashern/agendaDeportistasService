package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.agendadeportistas.agendaservices.entities.DeportistaEntity;

@Repository
public interface DeportistaRepository extends JpaRepository<DeportistaEntity, String> {
    Optional<DeportistaEntity> findByNombre(String nombre);
    Optional<DeportistaEntity> findById(String id);
    Boolean existsByNombre(String nombre);
}