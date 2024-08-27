package com.agendadeportistas.agendaservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agendadeportistas.agendaservices.entities.CursoEntity;
import com.agendadeportistas.agendaservices.entities.GrupoEntity;
import com.agendadeportistas.agendaservices.entities.ProfesorEntity;
import com.agendadeportistas.agendaservices.entities.UbicacionEntity;
import com.agendadeportistas.agendaservices.repositories.CursoRepository;
import com.agendadeportistas.agendaservices.repositories.GrupoRepository;
import com.agendadeportistas.agendaservices.repositories.ProfesorRepository;
import com.agendadeportistas.agendaservices.repositories.UbicacionRepository;
import com.agendadeportistas.agendaservices.shared.dto.GrupoDto;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public void crearGrupo(GrupoDto grupoDto) {
        GrupoEntity grupo = mapearToEntity(grupoDto);

        grupoRepository.save(grupo);

        Optional<CursoEntity> curso = cursoRepository.findById(grupoDto.getCurso().getIdCurso());

        if (curso.isPresent()) {
            curso.get().getGrupos().add(grupo);
            cursoRepository.save(curso.get());
        }

        Optional<ProfesorEntity> profesor = profesorRepository.findById(grupoDto.getProfesor().getId());

        if (profesor.isPresent()) {
            profesor.get().getGrupos().add(grupo);
            profesorRepository.save(profesor.get());
        }

        Optional<UbicacionEntity> ubicacion = ubicacionRepository.findById(grupoDto.getUbicacion().getId());

        if (ubicacion.isPresent()) {
            ubicacion.get().getGrupos().add(grupo);
            ubicacionRepository.save(ubicacion.get());
        }
    }

    @Transactional
    public void actualizarGrupo(GrupoDto grupoDto) {
        GrupoEntity grupo = grupoRepository.findById(grupoDto.getIdGrupo())
                .orElseThrow(() -> new RuntimeException("Grupo not found"));

        grupo = updateEntity(grupoDto, grupo);
        grupoRepository.save(grupo);
    }

    public GrupoEntity mapearToEntity(GrupoDto grupo) {
        GrupoEntity grupoEntity = new GrupoEntity();

        grupoEntity.setDia(grupo.getDia());
        grupoEntity.setHoraInicio(grupo.getHoraInicio());
        grupoEntity.setHoraFin(grupo.getHoraFin());
        grupoEntity.setCupos(grupo.getCupos());

        grupoEntity.setUbicacion(ubicacionRepository.findById(grupo.getUbicacion().getId()).get());
        grupoEntity.setProfesor(profesorRepository.findById(grupo.getProfesor().getId()).get());
        grupoEntity.setCurso(cursoRepository.findById(grupo.getCurso().getIdCurso()).get());

        return grupoEntity;
    }

    public GrupoEntity updateEntity(GrupoDto grupo, GrupoEntity grupoEntity) {
        grupoEntity.setDia(grupo.getDia());
        grupoEntity.setHoraInicio(grupo.getHoraInicio());
        grupoEntity.setHoraFin(grupo.getHoraFin());
        grupoEntity.setCupos(grupo.getCupos());

        grupoEntity.setUbicacion(ubicacionRepository.findById(grupo.getUbicacion().getId()).get());
        grupoEntity.setProfesor(profesorRepository.findById(grupo.getProfesor().getId()).get());
        grupoEntity.setCurso(cursoRepository.findById(grupo.getCurso().getIdCurso()).get());

        return grupoEntity;
    }

    public List<GrupoEntity> findAll() {
        return grupoRepository.findAll();
    }

    public void eliminarGrupo(long idGrupo) {
        grupoRepository.deleteById(idGrupo);
    }

}
