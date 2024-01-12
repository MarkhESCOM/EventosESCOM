package com.Eventos.Api.repository;

import com.Eventos.Api.model.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    // Aquí puedes definir métodos personalizados de consulta, si los necesitas
}
