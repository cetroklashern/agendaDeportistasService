package com.agendadeportistas.agendaservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.entities.DeportistaAcudienteEntity;
import com.agendadeportistas.agendaservices.entities.DeportistaEntity;
import com.agendadeportistas.agendaservices.repositories.AcudienteRepository;
import com.agendadeportistas.agendaservices.repositories.DeportistaAcudienteRepository;
import com.agendadeportistas.agendaservices.repositories.DeportistaRepository;
import com.agendadeportistas.agendaservices.shared.dto.AcudienteDto;
import com.agendadeportistas.agendaservices.shared.dto.DeportistaDto;

@Service
public class DeportistaService {
    @Autowired
    private DeportistaRepository deportistaRepository;

    @Autowired
    private AcudienteRepository acudienteRepository;

    @Autowired
    private DeportistaAcudienteRepository deportistaAcudienteRepository;

    @Transactional
    public DeportistaEntity createDeportista(DeportistaDto deportistaDto) {
        DeportistaEntity deportista = convertToEntity(deportistaDto);
        DeportistaEntity savedDeportista = deportistaRepository.save(deportista);
        saveDeportistaAcudientes(savedDeportista, deportistaDto.getAcudientes());
        return savedDeportista;
    }

    @Transactional
    public DeportistaEntity updateDeportista(DeportistaDto deportistaDto) {
        DeportistaEntity deportista = deportistaRepository.findById(deportistaDto.getId())
                .orElseThrow(() -> new RuntimeException("Deportista not found"));

        // Actualizar campos del deportista
        updateEntity(deportista, deportistaDto);
        deportistaRepository.save(deportista);

        // Manejar acudientes
        saveDeportistaAcudientes(deportista, deportistaDto.getAcudientes());
        return deportista;
    }

    private void saveDeportistaAcudientes(DeportistaEntity deportista, List<AcudienteDto> acudientesDto) {
        deportistaAcudienteRepository.deleteByDeportistaId(deportista.getId());

        for (AcudienteDto acudienteDto : acudientesDto) {
            AcudienteEntity acudiente = convertToEntity(acudienteDto);
            acudienteRepository.save(acudiente);

            DeportistaAcudienteEntity deportistaAcudiente = new DeportistaAcudienteEntity();
            deportistaAcudiente.setDeportista(deportista);
            deportistaAcudiente.setAcudiente(acudiente);
            deportistaAcudiente.setParentesco(acudienteDto.getParentesco());

            deportistaAcudienteRepository.save(deportistaAcudiente);
        }
    }

    private DeportistaEntity convertToEntity(DeportistaDto deportistaDto) {
        DeportistaEntity deportista = new DeportistaEntity();
        deportista.setId(deportistaDto.getId());
        deportista.setNombre(deportistaDto.getNombre());
        deportista.setEdad(deportistaDto.getEdad());
        deportista.setFechaNacimiento(deportistaDto.getFechaNacimiento());
        deportista.setTipoId(deportistaDto.getTipoId());
        deportista.setDireccion(deportistaDto.getDireccion());
        deportista.setEps(deportistaDto.getEps());
        deportista.setInstitucionEducativa(deportistaDto.getInstitucionEducativa());
        deportista.setGrado(deportistaDto.getGrado());
        deportista.setCondicionImportante(deportistaDto.getCondicionImportante());
        deportista.setImagenPropia(deportistaDto.isImagenPropia());
        deportista.setInformacionMensualidad(deportistaDto.isInformacionMensualidad());
        deportista.setInformacionReposicion(deportistaDto.isInformacionReposicion());
        deportista.setInformacionVacaciones(deportistaDto.isInformacionVacaciones());
        deportista.setComprobanteInscripcion(deportistaDto.isComprobanteInscripcion());
        return deportista;
    }

    private AcudienteEntity convertToEntity(AcudienteDto acudienteDto) {
        AcudienteEntity acudiente = new AcudienteEntity();
        acudiente.setId(acudienteDto.getId());
        acudiente.setNombre(acudienteDto.getNombre());
        acudiente.setTipoId(acudienteDto.getTipoId());
        acudiente.setNumeroCelular(acudienteDto.getNumeroCelular().longValue());
        acudiente.setDireccion(acudienteDto.getDireccion());
        acudiente.setCorreoElectronico(acudienteDto.getCorreoElectronico());
        acudiente.setImagenPropia(acudienteDto.getImagenPropia());
        acudiente.setProfesionEmpresa(acudienteDto.getProfesionEmpresa());
        return acudiente;
    }

    private void updateEntity(DeportistaEntity deportista, DeportistaDto deportistaDto) {
        deportista.setNombre(deportistaDto.getNombre());
        deportista.setEdad(deportistaDto.getEdad());
        deportista.setFechaNacimiento(deportistaDto.getFechaNacimiento());
        deportista.setTipoId(deportistaDto.getTipoId());
        deportista.setDireccion(deportistaDto.getDireccion());
        deportista.setEps(deportistaDto.getEps());
        deportista.setInstitucionEducativa(deportistaDto.getInstitucionEducativa());
        deportista.setGrado(deportistaDto.getGrado());
        deportista.setCondicionImportante(deportistaDto.getCondicionImportante());
        deportista.setImagenPropia(deportistaDto.isImagenPropia());
        deportista.setInformacionMensualidad(deportistaDto.isInformacionMensualidad());
        deportista.setInformacionReposicion(deportistaDto.isInformacionReposicion());
        deportista.setInformacionVacaciones(deportistaDto.isInformacionVacaciones());
        deportista.setComprobanteInscripcion(deportistaDto.isComprobanteInscripcion());
    }

    public Optional<DeportistaEntity> findByNombre(String nombre) {
        return deportistaRepository.findByNombre(nombre);
    }

    public List<DeportistaDto> findAll() {
        List<DeportistaEntity> deportistas = deportistaRepository.findAll();
        List<DeportistaDto> deportistaDTOs = mapearEntityToDto(deportistas);

        return deportistaDTOs;
    }

    private List<DeportistaDto> mapearEntityToDto(List<DeportistaEntity> deportistas) {
        List<DeportistaDto> deportistaDTOs = deportistas.stream()
                .map(deportista -> new DeportistaDto(
                        deportista.getId(),
                        deportista.getNombre(),
                        deportista.getEdad(),
                        deportista.getFechaNacimiento(),
                        deportista.getTipoId(),
                        deportista.getDireccion(),
                        deportista.getEps(),
                        deportista.getInstitucionEducativa(),
                        deportista.getGrado(),
                        deportista.getCondicionImportante(),
                        deportista.getImagenPropia(),
                        deportista.getInformacionMensualidad(),
                        deportista.getInformacionReposicion(),
                        deportista.getInformacionVacaciones(),
                        deportista.getComprobanteInscripcion(),
                        deportista.getDeportistaAcudientes().stream().map(
                                deportistaAcudiente -> new AcudienteDto(
                                        deportistaAcudiente.getAcudiente().getId(),
                                        deportistaAcudiente.getAcudiente().getNombre(),
                                        deportistaAcudiente.getAcudiente().getTipoId(),
                                        deportistaAcudiente.getAcudiente().getNumeroCelular(),
                                        deportistaAcudiente.getAcudiente().getDireccion(),
                                        deportistaAcudiente.getAcudiente().getCorreoElectronico(),
                                        deportistaAcudiente.getAcudiente().getImagenPropia(),
                                        deportistaAcudiente.getAcudiente().getProfesionEmpresa(),
                                        deportistaAcudiente.getParentesco()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        return deportistaDTOs;
    }

    public void guardarFoto(DeportistaEntity deportista) {
        deportistaRepository.save(deportista);
    }

    public Optional<DeportistaEntity> findById(String id) {
        return deportistaRepository.findById(id);
    }

    public Boolean existsByNombre(String nombre) {
        return deportistaRepository.existsByNombre(nombre);
    }

    public Boolean existsById(String id) {
        return deportistaRepository.existsById(id);
    }

    public void actualizarDeportista(DeportistaEntity deportista) {
        deportistaRepository.save(deportista);
    }

    public void eliminarDeportista(String id) {
        deportistaRepository.deleteById(id);
    }

    public List<DeportistaDto> findByNombreContaining(String nombre) {
        List<DeportistaEntity> deportistas = deportistaRepository.findByNombreContaining(nombre);

        List<DeportistaDto> deportistaDTOs = mapearEntityToDto(deportistas);
        if (deportistaDTOs == null || deportistaDTOs.size() == 0) {
            return null;
        } else {
            return deportistaDTOs;
        }
    }

    public List<DeportistaDto> findByIdContaining(String id) {
        List<DeportistaEntity> deportistas = deportistaRepository.findByIdContaining(id);

        List<DeportistaDto> deportistaDTOs = mapearEntityToDto(deportistas);
        if (deportistaDTOs == null || deportistaDTOs.size() == 0) {
            return null;
        } else {
            return deportistaDTOs;
        }
    }
}