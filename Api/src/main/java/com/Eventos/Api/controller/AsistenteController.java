package com.Eventos.Api.controller;

import com.Eventos.Api.model.Asistente;
import com.Eventos.Api.service.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {

    private final AsistenteService asistenteService;

    @Autowired
    public AsistenteController(AsistenteService asistenteService) {
        this.asistenteService = asistenteService;
    }

    @PostMapping
    public ResponseEntity<Asistente> createAsistente(@RequestBody Asistente asistente) {
        Asistente nuevoAsistente = asistenteService.saveAsistente(asistente);
        return new ResponseEntity<>(nuevoAsistente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> getAsistenteById(@PathVariable Long id) {
        return asistenteService.getAsistenteById(id)
                .map(asistente -> new ResponseEntity<>(asistente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Asistente>> getAllAsistentes() {
        List<Asistente> asistentes = asistenteService.getAllAsistentes();
        return new ResponseEntity<>(asistentes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> updateAsistente(@PathVariable Long id, @RequestBody Asistente asistente) {
        return asistenteService.getAsistenteById(id)
                .map(existingAsistente -> {
                    existingAsistente.setEmail(asistente.getEmail());
                    existingAsistente.setNombre(asistente.getNombre());
                    existingAsistente.setPaterno(asistente.getPaterno());
                    existingAsistente.setMaterno(asistente.getMaterno());
                    // Set other fields if needed
                    Asistente updatedAsistente = asistenteService.saveAsistente(existingAsistente);
                    return new ResponseEntity<>(updatedAsistente, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsistente(@PathVariable Long id) {
        return asistenteService.getAsistenteById(id)
                .map(asistente -> {
                    asistenteService.deleteAsistente(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
