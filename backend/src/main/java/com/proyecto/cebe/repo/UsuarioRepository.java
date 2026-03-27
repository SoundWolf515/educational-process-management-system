package com.proyecto.cebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cebe.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    Usuario findByUsuario(String usuario);
}
