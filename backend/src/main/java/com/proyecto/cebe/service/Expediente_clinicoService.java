package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Expediente_clinico;

public interface Expediente_clinicoService {
    Expediente_clinico guardar(Expediente_clinico expediente_clinico);

    List<Expediente_clinico> listar();

    void eliminar(Long id);

    Expediente_clinico buscarPorId(Long id);

    Expediente_clinico buscarPorEstudiante(Estudiante estudiante);
}
