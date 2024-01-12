package com.Eventos.Api.service;

import com.Eventos.Api.model.Asistente;
import com.Eventos.Api.repository.AsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenteService {

    private final AsistenteRepository asistenteRepository;

    @Autowired
    public AsistenteService(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public Asistente saveAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
        // Enviar correo electrónico si es necesario
    }

    public Optional<Asistente> getAsistenteById(Long id) {
        return asistenteRepository.findById(id);
    }

    public List<Asistente> getAllAsistentes() {
        return asistenteRepository.findAll();
    }

    public void deleteAsistente(Long id) {
        asistenteRepository.deleteById(id);
        // Enviar correo electrónico si es necesario
    }
}
