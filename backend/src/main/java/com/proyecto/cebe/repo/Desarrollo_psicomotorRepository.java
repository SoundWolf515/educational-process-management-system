package com.proyecto.cebe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Desarrollo_psicomotor;
import com.proyecto.cebe.model.Estudiante;

public interface Desarrollo_psicomotorRepository extends JpaRepository <Desarrollo_psicomotor, Long>{
    List<Desarrollo_psicomotor> findByEstudiante(Estudiante estudiante);
}
