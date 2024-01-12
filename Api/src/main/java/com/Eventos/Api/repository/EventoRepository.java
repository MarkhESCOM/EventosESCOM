package com.Eventos.Api.repository;

import com.Eventos.Api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Métodos personalizados si son necesarios
}
