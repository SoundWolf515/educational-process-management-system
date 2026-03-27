package com.proyecto.cebe.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Notas;

public interface NotasRepository extends JpaRepository<Notas, Long> {
    Optional<Notas> findByMatriculaIdAndCursoIdAndPeriodo(
            Long matriculaId,
            Long cursoId,
            int periodo);
}
