package com.agendadeportistas.agendaservices.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.GrupoEntity;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {

}
