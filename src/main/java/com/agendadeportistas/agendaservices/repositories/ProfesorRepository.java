package com.agendadeportistas.agendaservices.repositories;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.ProfesorEntity;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, String> {

    boolean existsByNombre(String nombre);

    Optional<ProfesorEntity> findByNombre(String nombre);

}
