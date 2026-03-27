package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Expediente_clinico;
import com.proyecto.cebe.repo.Expediente_clinicoRepository;

@Service
public class Expediente_clinicoServiceImpl implements Expediente_clinicoService {

    @Autowired
    private Expediente_clinicoRepository expediente_clinicoRepository;

    @Override
    public Expediente_clinico guardar(Expediente_clinico expediente_clinico) {
        return expediente_clinicoRepository.save(expediente_clinico);
    }

    @Override
    public List<Expediente_clinico> listar() {
        return expediente_clinicoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        expediente_clinicoRepository.deleteById(id);
    }

    @Override
    public Expediente_clinico buscarPorId(Long id) {
        return expediente_clinicoRepository.findById(id).orElse(null);
    }

    @Override
    public Expediente_clinico buscarPorEstudiante(Estudiante estudiante) {
        return expediente_clinicoRepository.findByEstudiante(estudiante);
    }

}
