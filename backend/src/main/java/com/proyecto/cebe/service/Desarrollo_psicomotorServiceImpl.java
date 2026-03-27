package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Desarrollo_psicomotor;
import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.repo.Desarrollo_psicomotorRepository;

@Service
public class Desarrollo_psicomotorServiceImpl implements Desarrollo_psicomotorService {

    @Autowired
    private Desarrollo_psicomotorRepository desarrollo_psicomotorRepository;

    @Override
    public Desarrollo_psicomotor guardar(Desarrollo_psicomotor desarrollo_psicomotor) {
        return desarrollo_psicomotorRepository.save(desarrollo_psicomotor);
    }

    @Override
    public List<Desarrollo_psicomotor> listar() {
        return desarrollo_psicomotorRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        desarrollo_psicomotorRepository.deleteById(id);
    }

    @Override
    public Desarrollo_psicomotor buscarPorId(Long id) {
        return desarrollo_psicomotorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Desarrollo_psicomotor> buscarPorEstudiante(Estudiante estudiante) {
        return desarrollo_psicomotorRepository.findByEstudiante(estudiante);
    }
}
