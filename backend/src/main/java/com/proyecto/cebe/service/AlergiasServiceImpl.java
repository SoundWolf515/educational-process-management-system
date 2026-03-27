package com.proyecto.cebe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Alergias;
import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.repo.AlergiasRepository;

@Service
public class AlergiasServiceImpl implements AlergiasService {

    @Autowired
    private AlergiasRepository alergiasRepository;

    @Override
    public Alergias guardar(Alergias alergias) {
        return alergiasRepository.save(alergias);
    }

    @Override
    public List<Alergias> listar() {
        return alergiasRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        alergiasRepository.deleteById(id);
    }

    @Override
    public Alergias buscarPorId(Long id) {
        return alergiasRepository.findById(id).orElse(null);
    }

    @Override
    public List<Alergias> buscarPorEstudiante(Estudiante estudiante) {
        return alergiasRepository.findByEstudiante(estudiante);
    }

    @Override
    public String obtenerAlergiasComoCadena(Estudiante estudiante) {
        List<Alergias> alergias = alergiasRepository.findByEstudiante(estudiante);
        return alergias.stream()
                .map(Alergias::getTipoAlergia)
                .collect(Collectors.joining(", "));
    }
}
