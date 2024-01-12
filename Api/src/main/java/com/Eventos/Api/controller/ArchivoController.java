package com.Eventos.Api.controller;

import com.Eventos.Api.model.Archivo;
import com.Eventos.Api.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    private final ArchivoService archivoService;

    @Autowired
    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    @PostMapping("/subir")
    public ResponseEntity<Archivo> subirArchivo(@RequestParam("archivo") MultipartFile archivo) {
        try {
            Archivo archivoSubido = archivoService.subirArchivo(archivo);
            return new ResponseEntity<>(archivoSubido, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Archivo>> listarArchivos() {
        List<Archivo> archivos = archivoService.listarArchivos();
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivo> obtenerArchivo(@PathVariable Long id) {
        Archivo archivo = archivoService.obtenerArchivo(id);
        if (archivo != null) {
            return new ResponseEntity<>(archivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarArchivo(@PathVariable Long id) {
        archivoService.eliminarArchivo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
