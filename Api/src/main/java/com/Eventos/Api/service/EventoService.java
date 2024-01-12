package com.Eventos.Api.service;

import com.Eventos.Api.model.Evento;
import com.Eventos.Api.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EmailService emailService; // Asumiendo que ya tienes un EmailService implementado

    @Autowired
    public EventoService(EventoRepository eventoRepository, EmailService emailService) {
        this.eventoRepository = eventoRepository;
        this.emailService = emailService;
    }

    public Evento saveEvento(Evento evento) {
        Evento savedEvento = eventoRepository.save(evento);
        emailService.enviarCorreo(evento.getAsistente().getEmail(), "Evento Creado/Actualizado",
                "El evento '" + evento.getNombre() + "' ha sido creado o actualizado.");
        return savedEvento;
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public void deleteEvento(Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            eventoRepository.deleteById(id);
            emailService.enviarCorreo(evento.get().getAsistente().getEmail(), "Evento Eliminado",
                    "El evento '" + evento.get().getNombre() + "' ha sido eliminado.");
        }
    }

}