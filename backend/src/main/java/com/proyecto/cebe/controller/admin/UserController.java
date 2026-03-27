package com.proyecto.cebe.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.cebe.model.Seccion;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.SeccionService;
import com.proyecto.cebe.service.UsuarioService;

@Controller
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SeccionService seccionService;

    @Autowired PasswordEncoder passwordEncoder;

    @PostMapping("admin/useradd")
    public String guardarUsuario(
            @RequestParam("nombresApellidos") String nombresApellidos,
            @RequestParam("telefono") String telefono,
            @RequestParam("dni") String dni,
            @RequestParam("correo") String correo,
            @RequestParam("username") String username,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("rol") String rol) {
        Usuario usuario = new Usuario(nombresApellidos, telefono, dni, correo, username, rol, contrasena);
        usuarioService.guardar(usuario);
        return "redirect:/admin/useradd";
    }

    @GetMapping("admin/userlist")
    public String listarUsuarios(Model model) {

        model.addAttribute("usuarios", usuarioService.listar());
        return "admin/perslist";
    }

    @PostMapping("admin/userlist/eliminar/{id}")
    public String eliminarUsuarios(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/userlist";
    }

    @PostMapping("admin/userlist/editar/{id}")
    public String actualizarUsuario(
            @PathVariable Long id,
            @RequestParam("nombresApellidos") String nombresApellidos,
            @RequestParam("telefono") String telefono,
            @RequestParam("dni") String dni,
            @RequestParam("correo") String correo,
            @RequestParam("username") String username,
            @RequestParam(value = "contrasena", required = false) String contrasena,
            @RequestParam("rol") String rol,
            @RequestParam(value = "seccionId", required = false) Long seccionId) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null)
            return "redirect:/admin/userlist";

        usuario.setNombresApellidos(nombresApellidos);
        usuario.setDni(dni);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setUsuario(username);

        if (contrasena != null && !contrasena.isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(contrasena));
        }

        usuario.setRol(rol);

        if ("Docente".equalsIgnoreCase(rol)) {
            usuario.getSecciones().clear();
            if (seccionId != null) {
                Seccion seccion = seccionService.buscarPorId(seccionId);
                if (seccion != null) {
                    usuario.getSecciones().add(seccion);
                }
            }
        } else {
            usuario.getSecciones().clear();
        }

        usuarioService.guardar(usuario);
        return "redirect:/admin/userlist";
    }

    @GetMapping("admin/userlist/editar/{id}")
    public String mostrarEditarUsuario(@PathVariable Long id, Model model) {

        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return "redirect:/admin/userlist";
        }

        List<Seccion> secciones = seccionService.listar();

        model.addAttribute("usuario", usuario);
        model.addAttribute("secciones", secciones);

        return "admin/editpers";
    }
}
