package com.agendadeportistas.agendaservices.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agendadeportistas.agendaservices.entities.DeportistaEntity;
import com.agendadeportistas.agendaservices.repositories.DeportistaRepository;
import com.agendadeportistas.agendaservices.services.DeportistaService;
import com.agendadeportistas.agendaservices.shared.dto.DeportistaDto;

@RestController
@RequestMapping("/api/deportistas/")
public class RestControllerDeportista {
    @Autowired
    DeportistaService deportistaService;
    @Autowired
    DeportistaRepository deportistaRepository;

    /*
     * Método para crear un deportista
     * 
     * @param deportistaRq: datos del deportista a crear
     */
    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> createDeportista(@RequestBody DeportistaDto deportistaDto) {
        DeportistaEntity deportista = deportistaService.createDeportista(deportistaDto);
        return ResponseEntity.ok("Deportista " + deportista.getNombre() + " creado en la base de datos");
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping(value = "actualizar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateDeportista(@RequestBody DeportistaDto deportistaDto) {
        DeportistaEntity deportista = deportistaService.updateDeportista(deportistaDto);

        return ResponseEntity.ok("Deportista " + deportista.getNombre() + " actualizado en la base de datos");
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("uploadFotoDeportista/{id}/fotoDeportista")
    public ResponseEntity<String> uploadFotoDeportista(@PathVariable String id,
            @RequestParam("file") MultipartFile file) {
        System.out.println("uploadFotoDeportista: " + file.getOriginalFilename());
        Optional<DeportistaEntity> deportistaOptional = deportistaService.findById(id);
        if (deportistaOptional.isPresent()) {
            // System.out.println("uploadFotoDeportista: " + file.getOriginalFilename());
            DeportistaEntity deportista = deportistaOptional.get();
            try {
                deportista.setFotoDeportista(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            deportistaService.guardarFoto(deportista);
            return ResponseEntity.ok("Foto de deportista actualizada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("uploadFotoDocumento/{id}/fotoDocumento")
    public ResponseEntity<String> uploadFotoDocumento(@PathVariable String id,
            @RequestParam("file") MultipartFile file) {
        System.out.println("uploadFotoDocumento: " + file.getOriginalFilename());
        Optional<DeportistaEntity> deportistaOptional = deportistaService.findById(id);
        if (deportistaOptional.isPresent()) {
            // System.out.println("uploadFotoDocumento: " + file.getOriginalFilename());
            DeportistaEntity deportista = deportistaOptional.get();
            try {
                deportista.setFotoDocumento(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            deportistaService.guardarFoto(deportista);
            return ResponseEntity.ok("Foto de deportista actualizada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<DeportistaDto>> listarDeportistas() {
        List<DeportistaDto> deportistasResp = deportistaService.findAll();

        return ResponseEntity.ok(deportistasResp);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}/fotoDeportista")
    public ResponseEntity<String> getFotoDeportista(@PathVariable String id) {
        System.out.println("getFotoDeportista: " + id);
        DeportistaEntity deportista = deportistaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Deportista no encontrado"));
        // System.out.println(deportista.getFotoDeportista());

        byte[] foto = deportista.getFotoDeportista();

        String base64Image = "";
        if (foto != null) {
            base64Image = Base64.getEncoder().encodeToString(foto);
        }

        // System.out.println("getFotoDeportista: " + base64Image);
        return ResponseEntity.ok(base64Image);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}/fotoDocumento")
    public ResponseEntity<String> getFotoDocumento(@PathVariable String id) {
        System.out.println("getFotoDocumento: " + id);
        DeportistaEntity deportista = deportistaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Deportista no encontrado"));
        byte[] foto = deportista.getFotoDocumento();

        String base64Image = "";
        if (foto != null) {
            base64Image = Base64.getEncoder().encodeToString(foto);
        }
        // System.out.println("getFotoDocumento: " + base64Image);
        return ResponseEntity.ok(base64Image);
    }

    // Petición para obtener deportista mediante "nombre"
    @CrossOrigin(origins = "http://locnalhost:5173")
    @GetMapping(value = "listarNombre/{nombre}", headers = "Accept=application/json")
    public Optional<DeportistaEntity> obtenerDeportistaPorNombre(@PathVariable String nombre) {
        return deportistaService.findByNombre(nombre);
    }

    // Petición para actualizar un deportista
    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarDeportista(@RequestBody DeportistaEntity deportistaRq) {
        deportistaService.actualizarDeportista(deportistaRq);
    }

    // Petición para eliminar un deportista
    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarDeportista(@PathVariable String id) {
        System.out.println("Deportista a eliminar el id: " + id);
        deportistaService.eliminarDeportista(id);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "buscarIdentificacion/{id}", headers = "Accept=application/json")
    public DeportistaDto searchDeportistas(@PathVariable String id) {
        List<DeportistaDto> deportistas = deportistaService.findByIdContaining(id);
        if (deportistas == null || deportistas.size() == 0) {
            return null;
        } else {
            return deportistas.get(0);
        }
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping(value = "buscarNombre/{nombre}", headers = "Accept=application/json")
    public List<DeportistaDto> searchDeportistasNombre(@PathVariable String nombre) {
        List<DeportistaDto> deportistas = deportistaService.findByNombreContaining(nombre);
        if (deportistas == null || deportistas.size() == 0) {
            return null;
        } else {
            return deportistas;
        }
    }
}
