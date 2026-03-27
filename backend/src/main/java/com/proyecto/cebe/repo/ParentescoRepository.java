package com.proyecto.cebe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Parentesco;

public interface ParentescoRepository extends JpaRepository <Parentesco, Long> {

    List<Parentesco> findByEstudianteId(Long estudianteId);
    Parentesco findByEstudianteIdAndEsPrincipalTrue(Long estudianteId);
    boolean existsByEstudianteIdAndApoderadoId(Long estudianteId, Long apoderadoId);
    int countByApoderadoId(Long apoderadoId);
}
