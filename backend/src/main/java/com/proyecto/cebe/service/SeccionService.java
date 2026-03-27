package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Seccion;

public interface SeccionService {
    Seccion guardar(Seccion seccion);
    List<Seccion> listar();
    void eliminar(Long id);
    Seccion buscarPorId(Long id);
    List<Seccion> listarPorNivel(String nivel);
}
