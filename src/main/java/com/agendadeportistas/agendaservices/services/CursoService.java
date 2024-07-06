package com.agendadeportistas.agendaservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendadeportistas.agendaservices.entities.CursoEntity;
import com.agendadeportistas.agendaservices.repositories.CursoRepository;

@Service
public class CursoService{

    CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
   
    public void createCurso(CursoEntity cursoEntity) {
        cursoRepository.save(cursoEntity);
    }    

    public List<CursoEntity> findAll(){
        return cursoRepository.findAll();
    }

    public Optional<CursoEntity> findByName(String nombre){
        return cursoRepository.findByNombre(nombre);
    }

    public Boolean existsByNombre(String nombre) {
        return cursoRepository.existsByNombre(nombre);
    }

    public void actualizarCurso(CursoEntity cursoEntity) {
        cursoRepository.save(cursoEntity);
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
