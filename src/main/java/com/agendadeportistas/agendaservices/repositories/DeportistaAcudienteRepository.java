package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.DeportistaAcudienteEntity;

public interface DeportistaAcudienteRepository extends JpaRepository<DeportistaAcudienteEntity, Long> {
    void deleteByDeportistaId(String deportistaId);
}
