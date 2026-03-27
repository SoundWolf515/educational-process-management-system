package com.proyecto.cebe.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="seccion")
public class Seccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivel;
    private String letra;
    private String anio;

    @ManyToMany(mappedBy = "secciones")
    private Set<Usuario> docentes = new HashSet<>();

    @OneToMany(mappedBy = "seccion")
    private List<Matricula> matriculas = new ArrayList<>();

    public Seccion(){

    }

    public Seccion(String letra, String anio, String nivel){
        this.letra = letra;
        this.anio = anio;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Set<Usuario> getDocentes() {
        return docentes;
    }

    public void setDocentes(Set<Usuario> docentes) {
        this.docentes = docentes;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    

}
