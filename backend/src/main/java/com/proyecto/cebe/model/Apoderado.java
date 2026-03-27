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
import jakarta.persistence.Table;

@Entity
@Table(name = "apoderado")
public class Apoderado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String dni;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    private String sexo;
    private Boolean estaVivo;
    private String direccion;
    private String lugarNacimiento;
    private String departamento;
    private String provincia;
    private String distrito;
    private String lenguaMaterna;
    private String segundaLengua;
    private String religion;
    private String gradoInstruccion;
    private String ocupacion;
    private String telefono;
    private String correo;

    private Boolean viveConEstudiante;

    @OneToMany(mappedBy = "apoderado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parentesco> parentescos = new ArrayList<>();

    public Apoderado(){

    }

    public Apoderado(String nombre, String apellidos, String dni, Date fechaNacimiento, String sexo, Boolean estaVivo,
            String direccion, String lugarNacimiento, String departamento, String provincia, String distrito,
            String lenguaMaterna, String segundaLengua, String religion, String gradoInstruccion, String ocupacion,
            String telefono, String correo, Boolean viveConEstudiante) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estaVivo = estaVivo;
        this.direccion = direccion;
        this.lugarNacimiento = lugarNacimiento;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.lenguaMaterna = lenguaMaterna;
        this.segundaLengua = segundaLengua;
        this.religion = religion;
        this.gradoInstruccion = gradoInstruccion;
        this.ocupacion = ocupacion;
        this.telefono = telefono;
        this.correo = correo;
        this.viveConEstudiante = viveConEstudiante;
    }

    public Apoderado(String nombre, String apellidos, String dni, String telefono, String correo) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.dni = dni;
    this.telefono = telefono;
    this.correo = correo;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Boolean getEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(Boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLenguaMaterna() {
        return lenguaMaterna;
    }

    public void setLenguaMaterna(String lenguaMaterna) {
        this.lenguaMaterna = lenguaMaterna;
    }

    public String getSegundaLengua() {
        return segundaLengua;
    }

    public void setSegundaLengua(String segundaLengua) {
        this.segundaLengua = segundaLengua;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGradoInstruccion() {
        return gradoInstruccion;
    }

    public void setGradoInstruccion(String gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getViveConEstudiante() {
        return viveConEstudiante;
    }

    public void setViveConEstudiante(Boolean viveConEstudiante) {
        this.viveConEstudiante = viveConEstudiante;
    }

    public List<Parentesco> getParentescos() {
        return parentescos;
    }

    public void setParentescos(List<Parentesco> parentescos) {
        this.parentescos = parentescos;
    }

}
