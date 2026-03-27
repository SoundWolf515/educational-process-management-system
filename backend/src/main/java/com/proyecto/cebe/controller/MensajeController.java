package com.proyecto.cebe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.cebe.model.Mensaje;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.MensajeService;
import com.proyecto.cebe.service.UsuarioService;

@Controller
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/contact")
    public String guardarMensaje(
            @RequestParam("nombre") String nombre,
            @RequestParam("contacto") String contacto,
            @RequestParam("contenido") String contenido) {
        Mensaje mensaje = new Mensaje(nombre, contacto, contenido);
        mensajeService.guardar(mensaje);
        return "redirect:/contact";
    }

    @GetMapping("/admin/mensajes")
    public String listarMensajes(Model model) {
        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        model.addAttribute("mensajes", mensajeService.listar());
        return "admin/mensajes";
    }

    @PostMapping("/admin/mensajes/eliminar/{id}")
    public String eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminar(id);
        return "redirect:/admin/mensajes";
    }

    @PostMapping("/admin/mensajes/leido/{id}")
    public String marcarLeido(@PathVariable Long id) {
        Mensaje mensaje = mensajeService.buscarPorId(id);
        mensaje.setLeido(!mensaje.isLeido());
        mensajeService.guardar(mensaje);
        return "redirect:/admin/mensajes";
    }

}
