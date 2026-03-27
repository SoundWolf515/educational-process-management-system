package com.proyecto.cebe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String foto = "pfp.jpeg";

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String sexo;
    private String telefono;
    private String direccion;
    private String religion;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Expediente_clinico expedienteClinico;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Desarrollo_psicomotor> desarrollos;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Alergias> alergias = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Vacunacion> vacunas = new ArrayList<>();

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Matricula matricula;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Parentesco> parentescos;

    public Estudiante() {

    }

    public Estudiante(java.lang.String nombre, java.lang.String apellido, java.lang.String dni,
            java.util.Date fechaNacimiento, java.lang.String sexo, java.lang.String telefono,
            java.lang.String direccion, java.lang.String religion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.religion = religion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Expediente_clinico getExpedienteClinico() {
        return expedienteClinico;
    }

    public void setExpedienteClinico(Expediente_clinico expedienteClinico) {
        this.expedienteClinico = expedienteClinico;
    }

    public List<Desarrollo_psicomotor> getDesarrollos() {
        return desarrollos;
    }

    public void setDesarrollos(List<Desarrollo_psicomotor> desarrollos) {
        this.desarrollos = desarrollos;
    }

    public List<Alergias> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<Alergias> alergias) {
        this.alergias = alergias;
    }

    public List<Vacunacion> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacunacion> vacunas) {
        this.vacunas = vacunas;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public List<Parentesco> getParentescos() {
        return parentescos;
    }

    public void setParentescos(List<Parentesco> parentescos) {
        this.parentescos = parentescos;
    }

}
