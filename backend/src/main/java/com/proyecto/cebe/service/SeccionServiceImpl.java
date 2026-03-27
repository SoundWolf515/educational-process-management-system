package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Seccion;
import com.proyecto.cebe.repo.SeccionRepository;

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

    @Override
    public Seccion guardar(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    @Override
    public List<Seccion> listar() {
        return seccionRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        seccionRepository.deleteById(id);
    }

    @Override
    public Seccion buscarPorId(Long id) {
        return seccionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Seccion> listarPorNivel(String nivel) {
    return seccionRepository.findByNivel(nivel);
    }
    
}
