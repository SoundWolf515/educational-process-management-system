package com.proyecto.cebe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Curso;
import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.model.Notas;
import com.proyecto.cebe.repo.NotasRepository;

@Service
public class NotasServiceImpl implements NotasService {

    @Autowired
    private NotasRepository notasRepository;

    @Override
    public Notas guardar(Notas notas) {
        return notasRepository.save(notas);
    }

    @Override
    public List<Notas> listar() {
        return notasRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        notasRepository.deleteById(id);
    }

    @Override
    public Notas buscarPorId(Long id) {
        return notasRepository.findById(id).orElse(null);
    }

    @Override
    public Double obtenerNota(
            Long matriculaId,
            Long cursoId,
            int periodo) {

        return notasRepository
                .findByMatriculaIdAndCursoIdAndPeriodo(
                        matriculaId, cursoId, periodo)
                .map(Notas::getNota)
                .orElse(null);
    }

    @Override
    public void guardarONueva(
            Matricula matricula,
            Curso curso,
            int periodo,
            Double valor) {

        if (valor == null) {
            return;
        }

        Optional<Notas> existente = notasRepository.findByMatriculaIdAndCursoIdAndPeriodo(
                matricula.getId(),
                curso.getId(),
                periodo);

        if (existente.isPresent()) {
            // UPDATE
            Notas nota = existente.get();
            nota.setNota(valor);
            notasRepository.save(nota);

        } else {
            // INSERT
            Notas nueva = new Notas();
            nueva.setMatricula(matricula);
            nueva.setCurso(curso);
            nueva.setPeriodo(periodo);
            nueva.setNota(valor);

            notasRepository.save(nueva);
        }
    }

}
