package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Curso;
import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.model.Notas;

public interface NotasService {
    Notas guardar(Notas notas);
    List<Notas> listar();
    void eliminar(Long id);
    Notas buscarPorId(Long id);
    Double obtenerNota(Long matriculaId, Long cursoId, int periodo);
    void guardarONueva(
        Matricula matricula,
        Curso curso,
        int periodo,
        Double valor
    );
}
