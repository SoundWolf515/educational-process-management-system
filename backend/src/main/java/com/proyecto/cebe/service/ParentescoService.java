package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Parentesco;

public interface ParentescoService {
    Parentesco guardar(Parentesco parentesco);
    List<Parentesco> listar();
    void eliminar(Long id);
    Parentesco buscarPorId(Long id);
    List<Parentesco> listarPorEstudiante(Long estudianteId);
    Parentesco asignarPrincipal(Long parentescoId); 
    boolean existeRelacion(Long estudianteId, Long apoderadoId);
}
