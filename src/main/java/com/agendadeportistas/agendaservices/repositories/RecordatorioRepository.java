package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agendadeportistas.agendaservices.entities.RecordatorioEntity;

public interface RecordatorioRepository extends JpaRepository<RecordatorioEntity, Long> {
}