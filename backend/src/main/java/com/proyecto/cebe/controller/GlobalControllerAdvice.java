package com.proyecto.cebe.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.UsuarioService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UsuarioService usuarioService;

    @ModelAttribute
    public void addAttributes(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioService.buscarPorUsuario(principal.getName());
            if (usuario != null) {
                model.addAttribute("usuarioLogueado" , usuario);

                String nombreCompleto = usuario.getNombresApellidos();
                model.addAttribute("nombreCompleto", nombreCompleto);
            }
        }
    }

}
