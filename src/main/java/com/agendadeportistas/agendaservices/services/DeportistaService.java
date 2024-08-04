package com.agendadeportistas.agendaservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.agendadeportistas.agendaservices.entities.DeportistaEntity;
import com.agendadeportistas.agendaservices.repositories.DeportistaRepository;

@Service
public class DeportistaService {
    @Autowired
    private DeportistaRepository deportistaRepository;

    public void crearDeportista(DeportistaEntity deportista) {
        deportistaRepository.save(deportista);
    }

    public Optional<DeportistaEntity> findByNombre(String nombre) {
        deportistaRepository.findByNombre(nombre);
    }
    
    public List<DeportistaEntity> findAll(){
        return deportistaRepository.findAll();
    } 

    public DeportistaEntity findById(String id){
        deportistaRepository.findById(id);
    }

    public Boolean existsByNombre(String nombre) {
        return deportistaRepository.existsByNombre(nombre);
    }

    public void actualizarCurso(DeportistaEntity deportista) {
        deportistaRepository.save(deportista);
    }

    public void eliminarDeportista(String id) {
        deportistaRepository.deleteById(id);
    }
}