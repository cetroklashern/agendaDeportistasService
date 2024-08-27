package com.agendadeportistas.agendaservices.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agendadeportistas.agendaservices.entities.AgendaEntity;
import com.agendadeportistas.agendaservices.entities.DeportistaEntity;
import com.agendadeportistas.agendaservices.entities.GrupoEntity;
import com.agendadeportistas.agendaservices.repositories.AgendaRepository;
import com.agendadeportistas.agendaservices.repositories.DeportistaRepository;
import com.agendadeportistas.agendaservices.repositories.GrupoRepository;
import com.agendadeportistas.agendaservices.shared.dto.AcudienteDto;
import com.agendadeportistas.agendaservices.shared.dto.AgendaDto;
import com.agendadeportistas.agendaservices.shared.dto.AgendaLightDto;
import com.agendadeportistas.agendaservices.shared.dto.DeportistaDto;
import com.agendadeportistas.agendaservices.shared.dto.DeportistaLightDto;
import com.agendadeportistas.agendaservices.shared.dto.GrupoDto;
import com.agendadeportistas.agendaservices.shared.dto.GrupoLightDto;

@Service
public class AgendaService {
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private DeportistaRepository deportistaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public void agregarAgenda(AgendaDto agendaDto) {
        DeportistaEntity deportista = deportistaRepository.findById(agendaDto.getDeportista().getId())
                .orElseThrow(() -> new RuntimeException("Deportista no encontrado"));

        GrupoEntity grupo = grupoRepository.findById(agendaDto.getGrupo().getIdGrupo())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        AgendaEntity agenda = new AgendaEntity();
        agenda.setDeportista(deportista);
        agenda.setGrupo(grupo);

        agendaRepository.save(agenda);

        deportista.getAgendas().add(agenda);

        deportistaRepository.save(deportista);

        grupo.getAgendas().add(agenda);

        grupoRepository.save(grupo);
    }

    @Transactional
    public void eliminarAgenda(Long idAgenda) {
        agendaRepository.deleteById(idAgenda);
    }

    public List<AgendaLightDto> findAll() {
        List<AgendaEntity> agendas = agendaRepository.findAll();

        return agendas.stream().map(agenda -> new AgendaLightDto(
                agenda.getIdAgenda(),
                new DeportistaLightDto(
                        agenda.getDeportista().getId(),
                        agenda.getDeportista().getNombre()),
                new GrupoLightDto(
                        agenda.getGrupo().getIdGrupo(),
                        agenda.getGrupo().getDia(),
                        agenda.getGrupo().getHoraInicio(),
                        agenda.getGrupo().getHoraFin(),
                        agenda.getGrupo().getCupos())))
                .collect(Collectors.toList());
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
}
