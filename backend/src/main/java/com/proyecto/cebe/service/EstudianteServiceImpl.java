package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.DTO.EstudianteDocenteDTO;
import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.repo.EstudianteRepository;
import com.proyecto.cebe.repo.MatriculaRepository;

@Service
public class EstudianteServiceImpl implements EstudianteService{
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private MatriculaService matriculaService;

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Estudiante> listarPorSeccion(Long idSeccion) {

        List<Matricula> matriculas = matriculaService.obtenerPorSeccion(idSeccion);

        return matriculas.stream()
                .map(Matricula::getEstudiante)
                .toList();
    }

    @Override
    public List<EstudianteDocenteDTO> listarPorSeccionDTO(Long idSeccion) {
        return matriculaRepository.listarEstudiantesConApoderado(idSeccion);
    }
}
