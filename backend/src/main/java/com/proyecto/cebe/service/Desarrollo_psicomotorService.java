package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Desarrollo_psicomotor;
import com.proyecto.cebe.model.Estudiante;

public interface Desarrollo_psicomotorService {
    Desarrollo_psicomotor guardar(Desarrollo_psicomotor desarrollo_psicomotor);
    List<Desarrollo_psicomotor> listar();
    void eliminar(Long id);
    Desarrollo_psicomotor buscarPorId(Long id);
    List<Desarrollo_psicomotor> buscarPorEstudiante(Estudiante estudiante);
}
