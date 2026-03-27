package com.proyecto.cebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.cebe.model.Mensaje;

public interface MensajeRepository extends JpaRepository <Mensaje, Long>{
    
}
