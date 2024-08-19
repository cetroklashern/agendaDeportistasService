package com.agendadeportistas.agendaservices.repositories;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
    Optional<CursoEntity> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);
}