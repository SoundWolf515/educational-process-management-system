package com.proyecto.cebe.config;

import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    public void run(String... args) throws Exception {
        
        if (usuarioService.buscarPorUsuario("perezjuan") == null) {
            
            Usuario admin = new Usuario();
            admin.setUsuario("perezjuan");

            admin.setContrasena("doc123");

            admin.setNombresApellidos("Juan Pérez Gonzáles");

            admin.setFoto("pfp.jpeg");

            admin.setRol("Docente"); 
            
            usuarioService.guardar(admin);
            
            System.out.println("-----------------------------------------");
            System.out.println("SISTEMA: Usuario administrativo creado.");
            System.out.println("Usuario: admin | Password: admin123");
            System.out.println("-----------------------------------------");
        }
    }
}