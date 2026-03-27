package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Apoderado;

public interface ApoderadoService {
    Apoderado guardar(Apoderado apoderado);
    List<Apoderado> listar();
    void eliminar(Long id);
    Apoderado buscarPorId(Long id);
}
