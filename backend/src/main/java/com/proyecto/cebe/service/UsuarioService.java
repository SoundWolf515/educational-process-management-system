package com.proyecto.cebe.service;

import java.util.List;

import com.proyecto.cebe.model.Usuario;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    List<Usuario> listar();
    void eliminar(Long id);
    Usuario buscarPorId(Long id);
    Usuario buscarPorUsuario(String username);
}
