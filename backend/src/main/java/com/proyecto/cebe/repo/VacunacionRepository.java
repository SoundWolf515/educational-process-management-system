package com.proyecto.cebe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Vacunacion;

public interface VacunacionRepository extends JpaRepository <Vacunacion, Long> {
    List<Vacunacion> findByEstudiante(Estudiante estudiante);
}
