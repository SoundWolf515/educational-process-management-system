package com.proyecto.cebe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Vacunacion;
import com.proyecto.cebe.repo.VacunacionRepository;

@Service
public class VacunacionServiceImpl implements VacunacionService {

    @Autowired
    private VacunacionRepository vacunacionRepository;

    @Override
    public Vacunacion guardar(Vacunacion vacunacion) {
        return vacunacionRepository.save(vacunacion);
    }

    @Override
    public List<Vacunacion> listar() {
        return vacunacionRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        vacunacionRepository.deleteById(id);
    }

    @Override
    public Vacunacion buscarPorId(Long id) {
        return vacunacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vacunacion> buscarPorEstudiante(Estudiante estudiante) {
        return vacunacionRepository.findByEstudiante(estudiante);
    }

    @Override
    public String obtenerVacunasComoCadena(Estudiante estudiante) {
        List<Vacunacion> vacunas = vacunacionRepository.findByEstudiante(estudiante);
        return vacunas.stream()
                .map(Vacunacion::getVacuna)
                .collect(Collectors.joining(", "));
    }
}
