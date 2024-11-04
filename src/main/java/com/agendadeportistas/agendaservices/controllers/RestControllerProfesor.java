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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendadeportistas.agendaservices.shared.dto.ProfesorDto;
import com.agendadeportistas.agendaservices.entities.ProfesorEntity;
import com.agendadeportistas.agendaservices.services.ProfesorService;

@RestController
@RequestMapping("/api/profesores/")
public class RestControllerProfesor {
    @Autowired
    ProfesorService profesorService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearProfesor(@RequestBody ProfesorDto profesorRq) {
        if (profesorService.existsById(profesorRq.getId())) {
            return new ResponseEntity<>("el Profesor " + profesorRq.getNombre() + " ya existe en la BD",
                    HttpStatus.BAD_REQUEST);
        }
        // Crear el Profesor
        profesorService.crearProfesor(profesorRq);

        return new ResponseEntity<>("Profesor " + profesorRq.getNombre() + " creado con exito", HttpStatus.CREATED);
    }

    // Petición para actualizar un Profesor
    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarProfesor(@RequestBody ProfesorDto ProfesorRq) {
        profesorService.actualizarProfesor(ProfesorRq);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<ProfesorEntity> listarProfesores() {
        return profesorService.findAll();
    }

    // Petición para obtener Profesor mediante "nombre"
    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "listarNombre/{nombre}", headers = "Accept=application/json")
    public Optional<ProfesorEntity> obtenerProfesorsPorNombre(@PathVariable String nombre) {
        return profesorService.findByName(nombre);
    }

    // Petición para eliminar un Profesor
    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarProfesor(@PathVariable String id) {
        System.out.println("Profesor a eliminar el id: " + id);
        profesorService.eliminarProfesor(id);
    }
}
