package com.proyecto.cebe.DTO;

public class EstudianteDocenteDTO {

    private Long idEstudiante;
    private String nombreEstudiante;
    private String direccion;

    private String estadoMatricula;

    private String nombreApoderado;
    private String telefonoApoderado;

    public EstudianteDocenteDTO(
        Long idEstudiante,
        String nombreEstudiante,
        String direccion,
        String estadoMatricula,
        String nombreApoderado,
        String telefonoApoderado
    ) {
        this.idEstudiante = idEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.direccion = direccion;
        this.estadoMatricula = estadoMatricula;
        this.nombreApoderado = nombreApoderado;
        this.telefonoApoderado = telefonoApoderado;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public String getNombreApoderado() {
        return nombreApoderado;
    }

    public void setNombreApoderado(String nombreApoderado) {
        this.nombreApoderado = nombreApoderado;
    }

    public String getTelefonoApoderado() {
        return telefonoApoderado;
    }

    public void setTelefonoApoderado(String telefonoApoderado) {
        this.telefonoApoderado = telefonoApoderado;
    }

    
}
