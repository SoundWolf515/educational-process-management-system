package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Apoderado;
import com.proyecto.cebe.repo.ApoderadoRepository;

@Service
public class ApoderadoServiceImpl implements ApoderadoService {
    
    @Autowired
    private ApoderadoRepository apoderadoRepository;

    @Override
    public Apoderado guardar(Apoderado apoderado) {
        return apoderadoRepository.save(apoderado);
    }

    @Override
    public List<Apoderado> listar() {
        return apoderadoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        apoderadoRepository.deleteById(id);
    }

    @Override
    public Apoderado buscarPorId(Long id) {
        return apoderadoRepository.findById(id).orElse(null);
    }

}
