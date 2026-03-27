package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.repo.MatriculaRepository;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public Matricula guardar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> listar() {
        return matriculaRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id).orElse(null);
    }

    public List<Matricula> obtenerPorSeccion(Long seccionId) {
        return matriculaRepository.findBySeccionId(seccionId);
    }

}
