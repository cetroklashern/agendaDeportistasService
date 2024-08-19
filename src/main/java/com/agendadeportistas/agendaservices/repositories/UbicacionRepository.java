package com.agendadeportistas.agendaservices.repositories;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.UbicacionEntity;

@Repository
public interface UbicacionRepository extends JpaRepository<UbicacionEntity, Long> {

    boolean existsByNombre(String nombre);

    Optional<UbicacionEntity> findByNombre(String nombre);

}
