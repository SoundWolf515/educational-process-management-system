package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Curso;

public interface CursoService {
    Curso guardar(Curso curso);
    List<Curso> listar();
    void eliminar(Long id);
    Curso buscarPorId(Long id);
}
