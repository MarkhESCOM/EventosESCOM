package com.Eventos.Api.controller;

import com.Eventos.Api.model.Evento;
import com.Eventos.Api.service.EventoService;
import com.Eventos.Api.service.PdfService;
import org.springframework.http.MediaType;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }
    @Autowired
    private PdfService pdfService;

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.saveEvento(evento);
        return new ResponseEntity<>(nuevoEvento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(evento -> new ResponseEntity<>(evento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        List<Evento> eventos = eventoService.getAllEventos();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.getEventoById(id)
                .map(existingEvento -> {
                    existingEvento.setNombreEvento(evento.getNombre());
                    existingEvento.setDescripcionEvento(evento.getDescripcionEvento());
                    existingEvento.setFechaCreacion(evento.getFechaCreacion());
                    // Set other fields if needed
                    Evento updatedEvento = eventoService.saveEvento(existingEvento);
                    return new ResponseEntity<>(updatedEvento, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvento(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(evento -> {
                    eventoService.deleteEvento(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generarPdfParaEvento(@PathVariable Long id) {
        Optional<Evento> optionalEvento = eventoService.getEventoById(id);
        if (optionalEvento.isPresent()) {
            try {
                Evento evento = optionalEvento.get();
                byte[] pdfBytes = pdfService.generatePdf(evento);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "reporte_evento.pdf");
                headers.setContentLength(pdfBytes.length);

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } catch (DocumentException e) {
                // Manejo de errores en la generación del PDF
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
