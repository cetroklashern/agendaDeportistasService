package com.agendadeportistas.agendaservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendadeportistas.agendaservices.entities.DeportistaEntity;
import com.agendadeportistas.agendaservices.services.DeportistaService;

@RestController
@RequestMapping("/api/deportistas/")
public class RestControllerDeportista {
    @Autowired
    DeportistaService deportistaService;

    /*
     * Método para crear un deportista
     * @param deportistaRq: datos del deportista a crear
     */
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearDeportista(@RequestBody DeportistaEntity deportistaRq) {
        if (deportistaService.existsById(deportistaRq.getId())) {
            return new ResponseEntity<>("el deportista " + deportistaRq.getNombre() + " ya existe en la BD", HttpStatus.BAD_REQUEST);
        }
        // Crear el deportista
        deportistaService.crearDeportista(deportistaRq);

        return new ResponseEntity<>("Deportista " + deportistaRq.getNombre() + " creado con exito", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<DeportistaEntity> listarDeportistas() {
        return deportistaService.findAll();
    }

    //Petición para obtener deportista mediante "nombre"
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "listarNombre/{nombre}", headers = "Accept=application/json")
    public Optional<DeportistaEntity> obtenerDeportistaPorNombre(@PathVariable String nombre) {
        return deportistaService.findByNombre(nombre);
    }

    //Petición para actualizar un deportista
    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarDeportista(@RequestBody DeportistaEntity deportistaRq) {
        deportistaService.actualizarDeportista(deportistaRq);
    }

    //Petición para eliminar un deportista    
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarDeportista(@PathVariable String id) {
        System.out.println("Deportista a eliminar el id: " + id);
        deportistaService.eliminarDeportista(id);
    }
}
