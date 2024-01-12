package com.Eventos.Api.controller;

import com.Eventos.Api.model.Evento;
import com.Eventos.Api.service.EventoService;
import com.Eventos.Api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EmailController {

    private final EventoService eventoService;
    private final EmailService emailService;

    @Autowired
    public EmailController(EventoService eventoService, EmailService emailService) {
        this.eventoService = eventoService;
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.saveEvento(evento);

        // Enviar correo electrónico
        String destinatario = "ejemplo@gmail.com"; // Cambiar a la dirección de correo real
        String asunto = "Nuevo evento creado";
        String cuerpo = "Se ha creado un nuevo evento: " + evento.getNombre();

        emailService.enviarCorreo(destinatario, asunto, cuerpo);

        return new ResponseEntity<>(nuevoEvento, HttpStatus.CREATED);
    }
}
