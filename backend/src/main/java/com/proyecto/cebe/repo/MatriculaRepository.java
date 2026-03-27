package com.proyecto.cebe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.cebe.DTO.EstudianteDocenteDTO;
import com.proyecto.cebe.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findBySeccionId(Long seccionId);

    @Query("""
                SELECT new com.proyecto.cebe.DTO.EstudianteDocenteDTO(
                    e.id,
                    CONCAT(e.nombre, ' ', e.apellido),
                    e.direccion,
                    m.estado,
                    CONCAT(a.nombre, ' ', a.apellidos),
                    a.telefono
                )
                FROM Matricula m
                JOIN m.estudiante e
                JOIN m.seccion s
                LEFT JOIN Parentesco p ON p.estudiante = e AND p.esPrincipal = true
                LEFT JOIN p.apoderado a
                WHERE s.id = :idSeccion
            """)
    List<EstudianteDocenteDTO> listarEstudiantesConApoderado(@Param("idSeccion") Long idSeccion);
}
