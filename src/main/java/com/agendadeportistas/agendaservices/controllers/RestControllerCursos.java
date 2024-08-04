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

import com.agendadeportistas.agendaservices.entities.CursoEntity;
import com.agendadeportistas.agendaservices.services.CursoService;


@RestController
@RequestMapping("/api/cursos/")
public class RestControllerCursos {
    CursoService cursoService;

    @Autowired
    public RestControllerCursos(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    
    /*
     * Método para crear un curso
     * @param cursoRq: datos del curso a crear
     */
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearCurso(@RequestBody CursoEntity cursoRq) {
        if(cursoService.existsByNombre(cursoRq.getNombre())){
            return new ResponseEntity<>("el curso " + cursoRq.getNombre() + " ya existe en la BD", HttpStatus.BAD_REQUEST);
        }
        // Crear el curso
        cursoService.createCurso(cursoRq);

        return new ResponseEntity<>("Curso " + cursoRq.getNombre() + " creado con exito", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<CursoEntity> listarCelulares() {
        return cursoService.findAll();
    }

    //Petición para obtener curso mediante "nombre"
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "listarNombre/{nombre}", headers = "Accept=application/json")
    public Optional<CursoEntity> obtenerCelularPorId(@PathVariable String nombre) {
        return cursoService.findByName(nombre);
    }

    //Petición para actualizar un curso
    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarCurso(@RequestBody CursoEntity cursoRq) {
        cursoService.actualizarCurso(cursoRq);
    }

    //Petición para eliminar un curso    
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarCurso(@PathVariable Long id) {
        System.out.println("Curso a eliminar el id: " + id);
        cursoService.eliminarCurso(id);
    }
}
