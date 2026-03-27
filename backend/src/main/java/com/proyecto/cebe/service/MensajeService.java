package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Mensaje;

public interface MensajeService {
    Mensaje guardar(Mensaje mensaje);
    List<Mensaje> listar();
    void eliminar(Long id);
    Mensaje buscarPorId(Long id);
}
