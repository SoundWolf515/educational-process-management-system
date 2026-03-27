package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Curso;
import com.proyecto.cebe.repo.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    
}
