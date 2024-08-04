package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;

@Repository
public interface AcudienteRepository extends JpaRepository<AcudienteEntity, String> {
    Optional<AcudienteEntity> findByNombre(String nombre);
    Optional<AcudienteEntity> findById(String id);
    Boolean existsByNombre(String nombre);
    List<AcudienteEntity> findByIdentificacionContaining(String identificacion);
}
