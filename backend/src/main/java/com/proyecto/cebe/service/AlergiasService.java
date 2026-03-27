package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Alergias;
import com.proyecto.cebe.model.Estudiante;

public interface AlergiasService {
    Alergias guardar(Alergias alergias);
    List<Alergias> listar();
    void eliminar(Long id);
    Alergias buscarPorId(Long id);
    List<Alergias> buscarPorEstudiante(Estudiante estudiante);
    String obtenerAlergiasComoCadena(Estudiante estudiante);
}
