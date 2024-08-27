package com.agendadeportistas.agendaservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendadeportistas.agendaservices.entities.AcudienteEntity;
import com.agendadeportistas.agendaservices.repositories.AcudienteRepository;

@RestController
@RequestMapping("/api/acudientes")
public class RestControllerAcudiente {

    @Autowired
    private AcudienteRepository acudienteRepository;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "buscarIdentificacion/{id}", headers = "Accept=application/json")
    public AcudienteEntity searchAcudientes(@PathVariable String id) {
        List<AcudienteEntity> acudientes = acudienteRepository.findByIdContaining(id);
        if (acudientes == null || acudientes.size() == 0) {
            return null;
        } else {
            return acudientes.get(0);
        }
    }
}
