package com.agendadeportistas.agendaservices.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agendadeportistas.agendaservices.entities.UbicacionEntity;
import com.agendadeportistas.agendaservices.entities.DisponibilidadEntity;
import com.agendadeportistas.agendaservices.repositories.UbicacionRepository;
import com.agendadeportistas.agendaservices.shared.dto.DisponibilidadDto;
import com.agendadeportistas.agendaservices.shared.dto.UbicacionDto;
import com.agendadeportistas.agendaservices.repositories.DisponibilidadRepository;

@Service
public class UbicacionService {
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Transactional
    public void crearUbicacion(UbicacionDto ubicacionDto) {
        // mapear ubicacionDto a UbicacionEntity
        UbicacionEntity ubicacion = mapearToEntity(ubicacionDto);

        ubicacionRepository.save(ubicacion);

        // mapear disponibilidad a DisponibilidadEntity
        guardarDisponibilidadesUbicacion(ubicacionDto.getDisponibilidades(), ubicacion);
    }

    @Transactional
    public void actualizarUbicacion(UbicacionDto ubicacionDto) {
        UbicacionEntity ubicacion = ubicacionRepository.findById(ubicacionDto.getId())
                .orElseThrow(() -> new RuntimeException("Ubicacion not found"));

        ubicacion = updateEntity(ubicacion, ubicacionDto);

        // mapear disponibilidad a DisponibilidadEntity
        actualizarDispobilidades(ubicacionDto.getDisponibilidades(), ubicacion);
    }

    private void guardarDisponibilidadesUbicacion(List<DisponibilidadDto> disponibilidadesDto,
            UbicacionEntity ubicacion) {
        for (DisponibilidadDto disponibilidadDto : disponibilidadesDto) {
            DisponibilidadEntity disponibilidad = new DisponibilidadEntity();
            disponibilidad.setDiaDisponibilidad(disponibilidadDto.getDiaDisponibilidad());
            disponibilidad.setHoraInicioDisponibilidad(disponibilidadDto.getHoraInicioDisponibilidad());
            disponibilidad.setHoraFinDisponibilidad(disponibilidadDto.getHoraFinDisponibilidad());
            disponibilidad.setUbicacion(ubicacion);

            disponibilidadRepository.save(disponibilidad);
        }
    }

    private void actualizarDispobilidades(List<DisponibilidadDto> disponibilidadesDto,
            UbicacionEntity ubicacion) {
        List<DisponibilidadEntity> disponibilidadesActuales = ubicacion.getDisponibilidades();

        // Crear o actualizar disponibilidades existentes
        for (DisponibilidadDto disponibilidadDto : disponibilidadesDto) {
            Optional<DisponibilidadEntity> disponibilidadBD = disponibilidadesActuales.stream()
                    .filter(d -> d.getId() != null && d.getId().equals(disponibilidadDto.getId())
                            && d.getUbicacion().getId().equals(ubicacion
                                    .getId()))
                    .findFirst();

            if (disponibilidadBD.isPresent()) {
                DisponibilidadEntity disponibilidad = disponibilidadBD.get();
                disponibilidad.setDiaDisponibilidad(disponibilidadDto.getDiaDisponibilidad());
                disponibilidad.setHoraInicioDisponibilidad(disponibilidadDto.getHoraInicioDisponibilidad());
                disponibilidad.setHoraFinDisponibilidad(disponibilidadDto.getHoraFinDisponibilidad());

                disponibilidadRepository.save(disponibilidad);
            } else {
                DisponibilidadEntity nuevaDisponibilidad = new DisponibilidadEntity();
                nuevaDisponibilidad.setDiaDisponibilidad(disponibilidadDto.getDiaDisponibilidad());
                nuevaDisponibilidad.setHoraInicioDisponibilidad(disponibilidadDto.getHoraInicioDisponibilidad());
                nuevaDisponibilidad.setHoraFinDisponibilidad(disponibilidadDto.getHoraFinDisponibilidad());
                nuevaDisponibilidad.setUbicacion(ubicacion);
                disponibilidadesActuales.add(nuevaDisponibilidad);
                ubicacion.getDisponibilidades().add(nuevaDisponibilidad);

                disponibilidadRepository.save(nuevaDisponibilidad);
            }

        }

        // Eliminar disponibilidades que no están en el DTO
        Iterator<DisponibilidadEntity> iterator = disponibilidadesActuales.iterator();
        while (iterator.hasNext()) {
            DisponibilidadEntity disponibilidad = iterator.next();
            boolean existsInDto = disponibilidadesDto.stream()
                    .anyMatch(d -> d.getId() != null && d.getId().equals(disponibilidad.getId()));

            if (!existsInDto) {
                iterator.remove(); // Eliminar de la lista
            }
        }

        ubicacionRepository.save(ubicacion);
    }

    private UbicacionEntity mapearToEntity(UbicacionDto ubicacionDto) {
        UbicacionEntity ubicacion = new UbicacionEntity();
        ubicacion.setNombre(ubicacionDto.getNombre());
        ubicacion.setDireccion(ubicacionDto.getDireccion());
        ubicacion.setTelefono(ubicacionDto.getTelefono());
        ubicacion.setNombreContacto(ubicacionDto.getNombreContacto());
        ubicacion.setEstado(ubicacionDto.getEstado());
        ubicacion.setFechaInicioContrato(ubicacionDto.getFechaInicioContrato());
        ubicacion.setFechaFinContrato(ubicacionDto.getFechaFinContrato());
        return ubicacion;
    }

    private UbicacionEntity updateEntity(UbicacionEntity ubicacion, UbicacionDto ubicacionDto) {
        ubicacion.setNombre(ubicacionDto.getNombre());
        ubicacion.setDireccion(ubicacionDto.getDireccion());
        ubicacion.setTelefono(ubicacionDto.getTelefono());
        ubicacion.setNombreContacto(ubicacionDto.getNombreContacto());
        ubicacion.setEstado(ubicacionDto.getEstado());
        ubicacion.setFechaInicioContrato(ubicacionDto.getFechaInicioContrato());
        ubicacion.setFechaFinContrato(ubicacionDto.getFechaFinContrato());
        return ubicacion;
    }

    public void eliminarUbicacion(Long id) {
        ubicacionRepository.deleteById(id);
    }

    public void eliminarDisponibilidad(Long id) {
        disponibilidadRepository.deleteById(id);
    }

    public List<UbicacionEntity> findAll() {
        return ubicacionRepository.findAll();
    }

    public Optional<UbicacionEntity> findById(Long id) {
        return ubicacionRepository.findById(id);
    }

    public boolean existsByNombre(String nombre) {
        return ubicacionRepository.existsByNombre(nombre);
    }

    public Optional<UbicacionEntity> findByName(String nombre) {
        return ubicacionRepository.findByNombre(nombre);
    }
}
