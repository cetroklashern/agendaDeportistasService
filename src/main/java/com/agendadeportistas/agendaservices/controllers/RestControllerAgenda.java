package com.agendadeportistas.agendaservices.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendadeportistas.agendaservices.shared.dto.AgendaDto;
import com.agendadeportistas.agendaservices.shared.dto.AgendaLightDto;
import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.entities.AgendaEntity;
import com.agendadeportistas.agendaservices.repositories.AgendaRepository;
import com.agendadeportistas.agendaservices.services.AgendaService;

@RestController
@RequestMapping("/api/agendas/")
public class RestControllerAgenda {
    @Autowired
    AgendaService agendaService;

    @Autowired
    AgendaRepository agendaRepository;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearAgenda(@RequestBody AgendaDto agendaRq) {
        // Crear el Agenda
        agendaService.agregarAgenda(agendaRq);

        return new ResponseEntity<>("Agenda creado con exito", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<AgendaLightDto> listarAgendas() {
        return agendaService.findAll();
    }

    // Petición para eliminar un Agenda
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarAgenda(@PathVariable Long id) {
        System.out.println("Agenda a eliminar el id: " + id);
        agendaService.eliminarAgenda(id);
    }
}
