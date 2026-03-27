package com.proyecto.cebe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expediente_clinico")
public class Expediente_clinico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_parto;
    private String complicaciones;
    private String tipo_sangre;
    private String trauma = "Ninguna";
    private String lengua_materna;
    private String segunda_lengua;
    private Integer numero_hermanos = 0;
    private String discapacidad;
    private String tipo_discapacidad;
    private boolean certificado;
    private boolean informe_psicopedagogico;
    private boolean plan_educativo_personalizado;

    @OneToOne
    @JoinColumn(name = "id_estudiante", unique = true)
    private Estudiante estudiante;

    public Expediente_clinico(){

    }

    public Expediente_clinico(String tipo_parto, String complicaciones, String tipo_sangre, String trauma,
            String lengua_materna, String segunda_lengua, Integer numero_hermanos, String discapacidad, String tipo_discapacidad,
            boolean certificado, boolean informe_psicopedagogico, boolean plan_educativo_personalizado) {
        this.tipo_parto = tipo_parto;
        this.complicaciones = complicaciones;
        this.tipo_sangre = tipo_sangre;
        this.trauma = trauma;
        this.lengua_materna = lengua_materna;
        this.segunda_lengua = segunda_lengua;
        this.numero_hermanos = numero_hermanos;
        this.discapacidad = discapacidad;
        this.tipo_discapacidad = tipo_discapacidad;
        this.certificado = certificado;
        this.informe_psicopedagogico = informe_psicopedagogico;
        this.plan_educativo_personalizado = plan_educativo_personalizado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_parto() {
        return tipo_parto;
    }

    public void setTipo_parto(String tipo_parto) {
        this.tipo_parto = tipo_parto;
    }

    public String getComplicaciones() {
        return complicaciones;
    }

    public void setComplicaciones(String complicaciones) {
        this.complicaciones = complicaciones;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public String getTrauma() {
        return trauma;
    }

    public void setTrauma(String trauma) {
        this.trauma = trauma;
    }

    public String getLengua_materna() {
        return lengua_materna;
    }

    public void setLengua_materna(String lengua_materna) {
        this.lengua_materna = lengua_materna;
    }

    public String getSegunda_lengua() {
        return segunda_lengua;
    }

    public void setSegunda_lengua(String segunda_lengua) {
        this.segunda_lengua = segunda_lengua;
    }

    public Integer getNumero_hermanos() {
        return numero_hermanos;
    }

    public void setNumero_hermanos(Integer numero_hermanos) {
        this.numero_hermanos = numero_hermanos;
    }

    public String getTipo_discapacidad() {
        return tipo_discapacidad;
    }

    public void setTipo_discapacidad(String tipo_discapacidad) {
        this.tipo_discapacidad = tipo_discapacidad;
    }

    public boolean isCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    public boolean isInforme_psicopedagogico() {
        return informe_psicopedagogico;
    }

    public void setInforme_psicopedagogico(boolean informe_psicopedagogico) {
        this.informe_psicopedagogico = informe_psicopedagogico;
    }

    public boolean isPlan_educativo_personalizado() {
        return plan_educativo_personalizado;
    }

    public void setPlan_educativo_personalizado(boolean plan_educativo_personalizado) {
        this.plan_educativo_personalizado = plan_educativo_personalizado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    

}
