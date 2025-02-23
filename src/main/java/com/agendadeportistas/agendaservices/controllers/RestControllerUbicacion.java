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

import com.agendadeportistas.agendaservices.shared.dto.UbicacionDto;
import com.agendadeportistas.agendaservices.entities.UbicacionEntity;
import com.agendadeportistas.agendaservices.services.UbicacionService;

@RestController
@RequestMapping("/api/ubicaciones/")
public class RestControllerUbicacion {
    @Autowired
    UbicacionService ubicacionService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearUbicacion(@RequestBody UbicacionDto ubicacionRq) {
        if (ubicacionService.existsByNombre(ubicacionRq.getNombre())) {
            return new ResponseEntity<>("el Ubicacion " + ubicacionRq.getNombre() + " ya existe en la BD",
                    HttpStatus.BAD_REQUEST);
        }
        // Crear el Ubicacion
        ubicacionService.crearUbicacion(ubicacionRq);

        return new ResponseEntity<>("Ubicacion " + ubicacionRq.getNombre() + " creado con exito", HttpStatus.CREATED);
    }

    // Petición para actualizar un Ubicacion
    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarUbicacion(@RequestBody UbicacionDto UbicacionRq) {
        ubicacionService.actualizarUbicacion(UbicacionRq);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<UbicacionEntity> listarUbicaciones() {
        return ubicacionService.findAll();
    }

    // Petición para obtener Ubicacion mediante "nombre"
    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "listarNombre/{nombre}", headers = "Accept=application/json")
    public Optional<UbicacionEntity> obtenerUbicacionsPorNombre(@PathVariable String nombre) {
        return ubicacionService.findByName(nombre);
    }

    // Petición para eliminar un Ubicacion
    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarUbicacion(@PathVariable Long id) {
        System.out.println("Ubicacion a eliminar el id: " + id);
        ubicacionService.eliminarUbicacion(id);
    }
}
