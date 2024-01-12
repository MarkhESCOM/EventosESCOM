package com.Eventos.Api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asistente")
public class Asistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistente;

    @Column(nullable = false, length = 200)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String paterno;

    @Column(length = 100)
    private String materno;

    @OneToMany(mappedBy = "asistente")
    private Set<Evento> eventos = new HashSet<>();

    // Getters y setters...
    // Getters
    public Long getIdAsistente() {
        return idAsistente;
    }

    public String getEmail() {
        return email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    // Setters
    public void setIdAsistente(Long idAsistente) {
        this.idAsistente = idAsistente;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

}
