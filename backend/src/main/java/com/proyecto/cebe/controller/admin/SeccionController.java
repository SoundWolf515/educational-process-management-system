package com.proyecto.cebe.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.model.Seccion;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.MatriculaService;
import com.proyecto.cebe.service.SeccionService;
import com.proyecto.cebe.service.UsuarioService;

@Controller
public class SeccionController {
    
    @Autowired
    private SeccionService seccionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired MatriculaService matriculaService;

    @PostMapping("/admin/seccion")
    public String guardarSeccion(
        @RequestParam("letra") String letra,
        @RequestParam("anio") String anio,
        @RequestParam("nivel") String nivel
    ) {
        Seccion seccion = new Seccion(letra, anio, nivel);
        seccionService.guardar(seccion);
        return "redirect:/admin/seccion?success";
    }

    @GetMapping("admin/seccion")
    public String listarSecciones(Model model){
        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        model.addAttribute("secciones", seccionService.listar());
        return "admin/seccion";
    }

    @PostMapping("admin/seccion/eliminar/{id}")
    public String eliminarSeccion(@PathVariable Long id){

        Seccion seccion = seccionService.buscarPorId(id);

        for (Usuario usuario : seccion.getDocentes()) {
        usuario.getSecciones().remove(seccion);
    }

    for (Matricula matricula : seccion.getMatriculas()) {
        
        matricula.setSeccion(null);
        matriculaService.guardar(matricula);
    }

        seccionService.eliminar(id);
        return "redirect:/admin/seccion";
    }
}
