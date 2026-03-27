package com.proyecto.cebe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.repo.UsuarioRepository;

@Service
public class SecurityService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getContrasena())
                .authorities(usuario.getRol())
                .build();
    }

    public boolean validarContrasena(String contrasena, String contrasenaEnBaseDeDatos) {
        return passwordEncoder.matches(contrasena, contrasenaEnBaseDeDatos);
    }

}
