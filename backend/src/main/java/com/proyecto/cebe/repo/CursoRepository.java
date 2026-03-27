package com.proyecto.cebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.cebe.model.Curso;

public interface CursoRepository extends JpaRepository <Curso, Long> {
    
}
