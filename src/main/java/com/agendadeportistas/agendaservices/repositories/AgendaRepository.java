package com.agendadeportistas.agendaservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.entities.AgendaEntity;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

}
