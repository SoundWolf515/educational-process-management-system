package com.proyecto.cebe.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/usuarios/";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {

        Usuario usuario = usuarioService.buscarPorUsuario(principal.getName());
        model.addAttribute("usuario", usuario);
        return "admin/index";
    }

    @GetMapping("/useradd")
    public String colaboradoradd(Model model, Principal principal) {
        Usuario usuario = usuarioService.buscarPorUsuario(principal.getName());
        model.addAttribute("usuario", usuario);
        return "admin/addpers";
    }

    @GetMapping("/photo")
    public String cambiarFoto(Model model, Principal principal) {
        Usuario usuario = usuarioService.buscarPorUsuario(principal.getName());
        model.addAttribute("usuario", usuario);
        return "admin/changepfp";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(
            @RequestParam("imagen") MultipartFile imagen,
            @ModelAttribute Usuario usuario,
            Model model, Principal principal) {

        Usuario usuarioExistente = usuarioService.buscarPorUsuario(principal.getName());
        model.addAttribute("usuario", usuarioExistente);

        if (usuarioExistente == null) {
            model.addAttribute("mensaje", "Usuario no encontrado");
            return "admin/changepfp";
        }

        if (!imagen.isEmpty()) {
            try {
                String fileName = imagen.getOriginalFilename();

                Path path = Paths.get(UPLOAD_DIR + fileName);

                Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                usuarioExistente.setFoto(fileName);

                usuarioService.guardar(usuarioExistente);
                model.addAttribute("mensaje", "Usuario guardado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("mensaje", "Error al guardar la imagen");
                return "admin/changepfp";
            }
        }
        return "redirect:/admin/index";
    }

    @GetMapping("/password")
    public String mostrarCambioPassword() {
        return "admin/password";
    }

    @PostMapping("/password/update")
    public String actualizarPassword(@RequestParam("nuevaContrasena") String nuevaContrasena, 
                                     Principal principal, 
                                     RedirectAttributes redirectAttributes) {

        Usuario usuario = usuarioService.buscarPorUsuario(principal.getName());
        
        if (usuario != null && !nuevaContrasena.isEmpty()) {
            
            usuario.setContrasena(passwordEncoder.encode(nuevaContrasena));

            usuarioService.guardar(usuario);
            
            redirectAttributes.addFlashAttribute("success", true);
        }
        
        return "redirect:/admin/password";
    }


}
