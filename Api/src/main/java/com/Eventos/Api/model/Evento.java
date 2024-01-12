package com.Eventos.Api.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(nullable = false, length = 250)
    private String nombreEvento;

    @Column(nullable = false, length = 250)
    private String descripcionEvento;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idAsistente", referencedColumnName = "idAsistente")
    private Asistente asistente;

    // Getters y setters...
    // Getters
    public Long getIdEvento() {
        return idEvento;
    }

    public String getNombre() {
        return nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    // Setters
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

}
