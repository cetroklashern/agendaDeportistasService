package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendadeportistas.agendaservices.entities.AgendaEntity;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

}
