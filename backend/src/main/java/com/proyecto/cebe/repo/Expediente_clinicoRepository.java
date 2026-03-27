package com.proyecto.cebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Expediente_clinico;

public interface Expediente_clinicoRepository extends JpaRepository <Expediente_clinico, Long>{
    Expediente_clinico findByEstudiante(Estudiante estudiante);
}
