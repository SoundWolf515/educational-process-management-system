package com.proyecto.cebe.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.cebe.model.Curso;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.CursoService;
import com.proyecto.cebe.service.UsuarioService;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/admin/cursos")
    public String guardarCursos(
            @RequestParam("nombre") String nombre, Model model) {
        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);
        Curso curso = new Curso(nombre);
        cursoService.guardar(curso);
        return "redirect:/admin/cursos?success";
    }

    @GetMapping("/admin/cursos")
    public String listarCursos(Model model) {
        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        model.addAttribute("cursos", cursoService.listar());
        return "admin/curso";
    }

    @PostMapping("/admin/cursos/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        cursoService.eliminar(id);
        return "redirect:/admin/cursos";
    }

}
