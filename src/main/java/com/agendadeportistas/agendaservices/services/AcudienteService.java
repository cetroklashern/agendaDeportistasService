package com.agendadeportistas.agendaservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.entities.DeportistaAcudienteEntity;
import com.agendadeportistas.agendaservices.entities.DeportistaEntity;
import com.agendadeportistas.agendaservices.repositories.AcudienteRepository;
import com.agendadeportistas.agendaservices.repositories.DeportistaAcudienteRepository;

@Service
public class AcudienteService {
    @Autowired
    private AcudienteRepository acudienteRepository;

    @Autowired
    private DeportistaAcudienteRepository deportistaAcudienteRepository;

    public void crearAcudiente(AcudienteEntity acudiente) {
        acudienteRepository.save(acudiente);
    }

    public void crearDeportistaAcudiente(DeportistaAcudienteEntity deportistaAcudiente) {
        deportistaAcudienteRepository.save(deportistaAcudiente);
    }

    public void actualizarAcudiente(AcudienteEntity acudiente) {
        acudienteRepository.save(acudiente);
    }

    public Optional<AcudienteEntity> findByNombre(String nombre) {
        return acudienteRepository.findByNombre(nombre);
    }

    public List<AcudienteEntity> findAll() {
        return acudienteRepository.findAll();
    }

    public Optional<AcudienteEntity> findById(String id) {
        return acudienteRepository.findById(id);
    }

    public Boolean existsByNombre(String nombre) {
        return acudienteRepository.existsByNombre(nombre);
    }

    public void actualizarCurso(AcudienteEntity acudiente) {
        acudienteRepository.save(acudiente);
    }

    public void eliminarAcudiente(String id) {
        acudienteRepository.deleteById(id);
    }

    public void eliminarDeportistaAcudiente(Long id) {
        deportistaAcudienteRepository.deleteById(id);
    }

    public List<DeportistaEntity> getDeportistas(String id) {
        return acudienteRepository.getDeportistasById(id);
    }
}
