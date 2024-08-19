package com.agendadeportistas.agendaservices.repositories;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.DisponibilidadEntity;

@Repository
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadEntity, Long> {
    void deleteByUbicacionId(Long ubicacionId);
}
