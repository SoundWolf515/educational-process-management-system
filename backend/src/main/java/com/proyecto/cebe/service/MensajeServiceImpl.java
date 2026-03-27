package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Mensaje;
import com.proyecto.cebe.repo.MensajeRepository;

@Service
public class MensajeServiceImpl implements MensajeService {
    
    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public Mensaje guardar(Mensaje mensaje){
        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> listar() {
        return mensajeRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    public Mensaje buscarPorId(Long id){
        return mensajeRepository.findById(id).orElse(null);
    }

}
