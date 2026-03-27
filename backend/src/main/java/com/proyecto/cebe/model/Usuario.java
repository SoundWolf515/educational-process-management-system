package com.proyecto.cebe.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombresApellidos;
    private String telefono;
    private String dni;
    private String correo;
    private String rol;
    private String foto;
    private String usuario;
    private String contrasena;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "usuario_seccion", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "section_id"))
    private Set<Seccion> secciones = new HashSet<>();

    public Usuario() {

    }

    public Usuario(String nombresApellidos, String telefono, String dni,
            String correo, String usuario, String rol, String contrasena) {
        this.nombresApellidos = nombresApellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.correo = correo;
        this.usuario = usuario;
        this.rol = rol;
        this.contrasena = contrasena;
        this.foto = "pfp.jpeg";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(Set<Seccion> secciones) {
        this.secciones = secciones;
    }

}
