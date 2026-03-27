package com.proyecto.cebe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "parentesco",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_estudiante", "id_apoderado"})
    }
)
public class Parentesco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_apoderado", nullable = false)
    private Apoderado apoderado;

    private String relacion;

    @Column(name = "es_principal")
    private boolean esPrincipal;

    public Parentesco(){

    }

    public Parentesco(Estudiante estudiante, Apoderado apoderado, String relacion, boolean esPrincipal) {
    this.estudiante = estudiante;
    this.apoderado = apoderado;
    this.relacion = relacion;
    this.esPrincipal = esPrincipal;
    }

    public Parentesco(Estudiante estudiante, Apoderado apoderado) {
    this.estudiante = estudiante;
    this.apoderado = apoderado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    
}
