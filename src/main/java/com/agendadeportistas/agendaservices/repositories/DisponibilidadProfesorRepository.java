package com.agendadeportistas.agendaservices.repositories;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.DisponibilidadProfesorEntity;

@Repository
public interface DisponibilidadProfesorRepository extends JpaRepository<DisponibilidadProfesorEntity, Long> {
}
