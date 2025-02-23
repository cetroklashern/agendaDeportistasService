package com.agendadeportistas.agendaservices.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendadeportistas.agendaservices.entities.RecordatorioEntity;
import com.agendadeportistas.agendaservices.repositories.RecordatorioRepository;

import jakarta.transaction.Transactional;

@Service
public class RecordatorioService {
    @Autowired
    private RecordatorioRepository recordatorioRepository;

    // Implementación de recordatorioService
    @Transactional
    public void crearRecordatorio(RecordatorioEntity recordatorioReq) {
        // Implementación de la lógica para crear un recordatorio
        RecordatorioEntity recordatorio = new RecordatorioEntity();
        recordatorio.setTitulo(recordatorioReq.getTitulo());
        recordatorio.setContenido(recordatorioReq.getContenido());
        recordatorio.setFechaVisible(recordatorioReq.getFechaVisible());
        recordatorio.setFechaFinVisible(recordatorioReq.getFechaFinVisible());
        recordatorio.setCreado(new Date());

        recordatorioRepository.save(recordatorio);
    }

    @Transactional
    public void eliminarRecordatorio(Long idRecordatorio) {
        // Implementación de la lógica para eliminar un recordatorio
        recordatorioRepository.deleteById(idRecordatorio);
    }

    // Implementación de otros métodos para actualizar
    public void actualizarRecordatorio(RecordatorioEntity recordatorioReq) {
        RecordatorioEntity recordatorioEntity = recordatorioRepository
                .findById(recordatorioReq.getIdRecordatorio())
                .orElseThrow(() -> new RuntimeException("Recordatorio not found"));

        // Implementación de la lógica para crear un recordatorio

        recordatorioEntity.setTitulo(recordatorioReq.getTitulo());
        recordatorioEntity.setContenido(recordatorioReq.getContenido());
        recordatorioEntity.setFechaVisible(recordatorioReq.getFechaVisible());
        recordatorioEntity.setFechaFinVisible(recordatorioReq.getFechaFinVisible());

        recordatorioRepository.save(recordatorioEntity);
    }

    public List<RecordatorioEntity> listarRecordatorios() {
        // Implementación de la lógica para listar todos los recordatorios
        return recordatorioRepository.findAll();
    }

    public List<RecordatorioEntity> listarRecordatoriosHoy() {
        // Implementación de la lógica para listar todos los recordatorios visibles
        return recordatorioRepository.findAll().stream()
                .filter(recordatorioEntity -> recordatorioEntity.getFechaVisible().before(new Date())
                        && recordatorioEntity.getFechaFinVisible().after(new Date()))
                .toList();
    }
}
