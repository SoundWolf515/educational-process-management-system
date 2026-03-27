package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.DTO.EstudianteDocenteDTO;
import com.proyecto.cebe.model.Estudiante;

public interface EstudianteService {
    Estudiante guardar(Estudiante estudiante);
    List<Estudiante> listar();
    void eliminar(Long id);
    Estudiante buscarPorId(Long id);
    List<Estudiante> listarPorSeccion(Long idSeccion);
    List<EstudianteDocenteDTO> listarPorSeccionDTO(Long idSeccion);
}
