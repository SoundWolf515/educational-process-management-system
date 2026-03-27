package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Matricula;

public interface MatriculaService {
    Matricula guardar(Matricula matricula);
    List<Matricula> listar();
    void eliminar(Long id);
    Matricula buscarPorId(Long id);
    List<Matricula> obtenerPorSeccion(Long idSeccion);
}
