package com.proyecto.cebe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Seccion;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {
    List<Seccion> findByNivel(String nivel);
}
