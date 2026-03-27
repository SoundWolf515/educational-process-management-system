package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Vacunacion;

public interface VacunacionService {
    Vacunacion guardar(Vacunacion vacunacion);
    List<Vacunacion> listar();
    void eliminar(Long id);
    Vacunacion buscarPorId(Long id);
    List<Vacunacion> buscarPorEstudiante(Estudiante estudiante);
    String obtenerVacunasComoCadena(Estudiante estudiante);
}
