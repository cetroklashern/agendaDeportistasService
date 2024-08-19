package com.agendadeportistas.agendaservices.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agendadeportistas.agendaservices.entities.ProfesorEntity;
import com.agendadeportistas.agendaservices.entities.DisponibilidadProfesorEntity;
import com.agendadeportistas.agendaservices.repositories.ProfesorRepository;
import com.agendadeportistas.agendaservices.shared.dto.DisponibilidadDto;
import com.agendadeportistas.agendaservices.shared.dto.ProfesorDto;
import com.agendadeportistas.agendaservices.repositories.DisponibilidadProfesorRepository;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private DisponibilidadProfesorRepository disponibilidadRepository;

    @Transactional
    public void crearProfesor(ProfesorDto profesorDto) {
        // mapear ProfesorDto a ProfesorEntity
        ProfesorEntity profesor = mapearToEntity(profesorDto);

        profesorRepository.save(profesor);

        // mapear disponibilidad a DisponibilidadEntity
        guardarDisponibilidadesProfesor(profesorDto.getDisponibilidades(), profesor);
    }

    @Transactional
    public void actualizarProfesor(ProfesorDto profesorDto) {
        ProfesorEntity profesor = profesorRepository.findById(profesorDto.getId())
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        profesor = updateEntity(profesor, profesorDto);

        // mapear disponibilidad a DisponibilidadEntity
        actualizarDispobilidades(profesorDto.getDisponibilidades(), profesor);
    }

    private void guardarDisponibilidadesProfesor(List<DisponibilidadDto> disponibilidadesDto,
            ProfesorEntity profesor) {
        for (DisponibilidadDto disponibilidadDto : disponibilidadesDto) {
            DisponibilidadProfesorEntity disponibilidad = new DisponibilidadProfesorEntity();
            disponibilidad.setDiaDisponibilidad(disponibilidadDto.getDiaDisponibilidad());
            disponibilidad.setHoraInicioDisponibilidad(disponibilidadDto.getHoraInicioDisponibilidad());
            disponibilidad.setHoraFinDisponibilidad(disponibilidadDto.getHoraFinDisponibilidad());
            disponibilidad.setProfesor(profesor);

            disponibilidadRepository.save(disponibilidad);
        }
    }

    private void actualizarDispobilidades(List<DisponibilidadDto> disponibilidadesDto,
            ProfesorEntity profesor) {
        List<DisponibilidadProfesorEntity> disponibilidadesActuales = profesor.getDisponibilidades();

        // Crear o actualizar disponibilidades existentes
        for (DisponibilidadDto disponibilidadDto : disponibilidadesDto) {
            Optional<DisponibilidadProfesorEntity> disponibilidadBD = disponibilidadesActuales.stream()
                    .filter(d -> d.getId() != null && d.getId().equals(disponibilidadDto.getId())
                            && d.getProfesor().getId().equals(profesor
                                    .getId()))
                    .findFirst();

            if (disponibilidadBD.isPresent()) {
                DisponibilidadProfesorEntity disponibilidad = disponibilidadBD.get();
                disponibilidad.setDiaDisponibilidad(disponibilidadDto.getDiaDisponibilidad());
                disponibilidad.setHoraInicioDisponibilidad(disponibilidadDto.getHoraInicioDisponibilidad());
                disponibilidad.setHoraFinDisponibilidad(disponibilidadDto.getHoraFinDisponibilidad());

                disponibilidadRepository.save(disponibilidad);
            } else {
                DisponibilidadProfesorEntity nuevaDisponibilidad = new DisponibilidadProfesorEntity();
                nuevaDisponibilidad.setDiaDisponibilidad(disponibilidadDto.getDiaDisponibilidad());
                nuevaDisponibilidad.setHoraInicioDisponibilidad(disponibilidadDto.getHoraInicioDisponibilidad());
                nuevaDisponibilidad.setHoraFinDisponibilidad(disponibilidadDto.getHoraFinDisponibilidad());
                nuevaDisponibilidad.setProfesor(profesor);
                disponibilidadesActuales.add(nuevaDisponibilidad);
                profesor.getDisponibilidades().add(nuevaDisponibilidad);

                disponibilidadRepository.save(nuevaDisponibilidad);
            }

        }

        // Eliminar disponibilidades que no están en el DTO
        Iterator<DisponibilidadProfesorEntity> iterator = disponibilidadesActuales.iterator();
        while (iterator.hasNext()) {
            DisponibilidadProfesorEntity disponibilidad = iterator.next();
            boolean existsInDto = disponibilidadesDto.stream()
                    .anyMatch(d -> d.getId() != null && d.getId().equals(disponibilidad.getId()));

            if (!existsInDto) {
                iterator.remove(); // Eliminar de la lista
            }
        }

        profesorRepository.save(profesor);
    }

    private ProfesorEntity mapearToEntity(ProfesorDto profesorDto) {
        ProfesorEntity profesor = new ProfesorEntity();

        profesor.setId(profesorDto.getId());
        profesor.setNombre(profesorDto.getNombre());
        profesor.setTipoId(profesorDto.getTipoId());
        profesor.setNumeroCelular(profesorDto.getNumeroCelular());
        profesor.setDireccion(profesorDto.getDireccion());
        profesor.setEps(profesorDto.getEps());
        profesor.setArl(profesorDto.getArl());
        profesor.setCorreoElectronico(profesorDto.getCorreoElectronico());
        profesor.setNombreContacto(profesorDto.getNombreContacto());
        profesor.setNumeroContacto(profesorDto.getNumeroContacto());

        return profesor;
    }

    private ProfesorEntity updateEntity(ProfesorEntity profesor, ProfesorDto profesorDto) {
        profesor.setNombre(profesorDto.getNombre());
        profesor.setTipoId(profesorDto.getTipoId());
        profesor.setNumeroCelular(profesorDto.getNumeroCelular());
        profesor.setDireccion(profesorDto.getDireccion());
        profesor.setEps(profesorDto.getEps());
        profesor.setArl(profesorDto.getArl());
        profesor.setCorreoElectronico(profesorDto.getCorreoElectronico());
        profesor.setNombreContacto(profesorDto.getNombreContacto());
        profesor.setNumeroContacto(profesorDto.getNumeroContacto());

        return profesor;
    }

    public void eliminarProfesor(String id) {
        profesorRepository.deleteById(id);
    }

    public void eliminarDisponibilidad(Long id) {
        disponibilidadRepository.deleteById(id);
    }

    public List<ProfesorEntity> findAll() {
        return profesorRepository.findAll();
    }

    public boolean existsById(String id) {
        return profesorRepository.existsById(id);
    }

    public Optional<ProfesorEntity> findById(String id) {
        return profesorRepository.findById(id);
    }

    public boolean existsByNombre(String nombre) {
        return profesorRepository.existsByNombre(nombre);
    }

    public Optional<ProfesorEntity> findByName(String nombre) {
        return profesorRepository.findByNombre(nombre);
    }
}
