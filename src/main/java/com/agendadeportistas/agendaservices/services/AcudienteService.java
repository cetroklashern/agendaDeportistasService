package com.agendadeportistas.agendaservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.repositories.AcudienteRepository;

@Service
public class AcudienteService {
    @Autowired
    private AcudienteRepository acudienteRepository;

    public void crearAcudiente(AcudienteEntity acudiente) {
        acudienteRepository.save(acudiente);
    }

    public Optional<AcudienteEntity> findByNombre(String nombre) {
        acudienteRepository.findByNombre(nombre);
    }
    
    public List<AcudienteEntity> findAll(){
        return acudienteRepository.findAll();
    } 

    public AcudienteEntity findById(String id){
        acudienteRepository.findById(id);
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
}
