package com.proyecto.cebe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Alergias;
import com.proyecto.cebe.model.Estudiante;

public interface AlergiasRepository extends JpaRepository <Alergias, Long>{
    List<Alergias> findByEstudiante(Estudiante estudiante);
}
