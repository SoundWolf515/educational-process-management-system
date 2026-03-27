package com.proyecto.cebe.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import com.proyecto.cebe.DTO.EstudianteDocenteDTO;
import com.proyecto.cebe.model.Alergias;
import com.proyecto.cebe.model.Desarrollo_psicomotor;
import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Expediente_clinico;
import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.model.Seccion;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.model.Vacunacion;
import com.proyecto.cebe.service.AlergiasService;
import com.proyecto.cebe.service.Desarrollo_psicomotorService;
import com.proyecto.cebe.service.EstudianteService;
import com.proyecto.cebe.service.Expediente_clinicoService;
import com.proyecto.cebe.service.MatriculaService;
import com.proyecto.cebe.service.PDFService;
import com.proyecto.cebe.service.SeccionService;
import com.proyecto.cebe.service.UsuarioService;
import com.proyecto.cebe.service.VacunacionService;

@Controller
@RequestMapping("/admin")
public class MatriculaController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/estudiantes/";

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private Expediente_clinicoService expediente_clinicoService;

    @Autowired
    private Desarrollo_psicomotorService desarrollo_psicomotorService;

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlergiasService alergiasService;

    @Autowired
    private VacunacionService vacunacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PDFService pdfService;

    @GetMapping("/matricula")
    public String mostrarFormulario(Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("secciones", seccionService.listar());
        return "admin/matricula";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("alergias", "vacunas");
    }

    @PostMapping("/matricula")
    public String guardarEstudiante(
            @ModelAttribute("estudiante") Estudiante estudiante,
            @RequestParam(required = false) String tipo_parto,
            @RequestParam(required = false) String tipo_sangre,
            @RequestParam(required = false) String trauma,
            @RequestParam(required = false) String lengua_materna,
            @RequestParam(required = false) String segunda_lengua,
            @RequestParam(required = false) Integer num_hermanos,
            @RequestParam(required = false) String discapacidad,
            @RequestParam(required = false) String tipo_discapacidad,
            @RequestParam(required = false) Boolean certificado,
            @RequestParam(required = false) Boolean informe_psicopedagogico,
            @RequestParam(required = false) Boolean plan_educativo_personalizado,
            @RequestParam(required = false) Boolean complicaciones,
            // Campos de desarrollo psicomotor
            @RequestParam(required = false) Integer levantar_cabeza,
            @RequestParam(required = false) Integer sentarse,
            @RequestParam(required = false) Integer gatear,
            @RequestParam(required = false) Integer pararse,
            @RequestParam(required = false) Integer caminar,
            @RequestParam(required = false) Integer esfinteres,
            @RequestParam(required = false) Integer primeras_palabras,
            @RequestParam(required = false) Integer hablar_fluido,
            // Matrícula
            @RequestParam("id_seccion") Long idSeccion,
            // Vacunas
            @RequestParam(required = false) List<String> vacunas,
            // Alergias
            @RequestParam(required = false) List<String> alergias,
            // Foto
            @RequestParam(value = "fotoArchivo", required = false) MultipartFile fotoArchivo,
            Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        // Guardar el estudiante
        Estudiante estGuardado = estudianteService.guardar(estudiante);

        // Crear expediente clínico
        Expediente_clinico expediente = new Expediente_clinico();
        expediente.setTipo_parto(tipo_parto);
        expediente.setTipo_sangre(tipo_sangre);
        expediente.setTrauma(trauma);
        expediente.setLengua_materna(lengua_materna);
        expediente.setSegunda_lengua(segunda_lengua);
        expediente.setNumero_hermanos(num_hermanos);
        expediente.setDiscapacidad(discapacidad);
        expediente.setTipo_discapacidad(tipo_discapacidad);
        expediente.setCertificado(Boolean.TRUE.equals(certificado));
        expediente.setInforme_psicopedagogico(Boolean.TRUE.equals(informe_psicopedagogico));
        expediente.setPlan_educativo_personalizado(Boolean.TRUE.equals(plan_educativo_personalizado));
        expediente.setComplicaciones(Boolean.TRUE.equals(complicaciones) ? "Sí" : "No");
        expediente.setEstudiante(estGuardado);
        estGuardado.setExpedienteClinico(expediente);
        expediente_clinicoService.guardar(expediente);

        // Guardar desarrollo psicomotor
        guardarDesarrolloPsicomotor(estGuardado, "Levantar cabeza", levantar_cabeza);
        guardarDesarrolloPsicomotor(estGuardado, "Sentarse", sentarse);
        guardarDesarrolloPsicomotor(estGuardado, "Gatear", gatear);
        guardarDesarrolloPsicomotor(estGuardado, "Pararse", pararse);
        guardarDesarrolloPsicomotor(estGuardado, "Caminar", caminar);
        guardarDesarrolloPsicomotor(estGuardado, "Control de esfínteres", esfinteres);
        guardarDesarrolloPsicomotor(estGuardado, "Primeras palabras", primeras_palabras);
        guardarDesarrolloPsicomotor(estGuardado, "Hablar fluido", hablar_fluido);

        if (fotoArchivo != null && !fotoArchivo.isEmpty()) {
            try {

                String fileName = fotoArchivo.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);

                Files.copy(fotoArchivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                estGuardado.setFoto(fileName);
                estudianteService.guardar(estGuardado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Crear matrícula
        Seccion seccion = seccionService.buscarPorId(idSeccion);
        Matricula matricula = new Matricula();
        matricula.setFechaMatricula(new Date());
        matricula.setEstado("Activo");
        matricula.setEstudiante(estGuardado);
        matricula.setSeccion(seccion);
        matriculaService.guardar(matricula);

        // Guardar alergias
        if (alergias != null) {
            for (String alergiaTxt : alergias) {
                if (!alergiaTxt.isBlank()) {
                    Alergias alergia = new Alergias();
                    alergia.setTipoAlergia(alergiaTxt);
                    alergia.setEstudiante(estGuardado);
                    alergiasService.guardar(alergia);
                }
            }
        }

        // Guardar vacunas
        if (vacunas != null) {
            for (String vacunaTxt : vacunas) {
                if (!vacunaTxt.isBlank()) {
                    Vacunacion v = new Vacunacion();
                    v.setVacuna(vacunaTxt);
                    v.setEstudiante(estGuardado);
                    vacunacionService.guardar(v);
                }
            }
        }

        return "redirect:/admin/matricula";
    }

    @GetMapping("listaestudiantes")
    public String listarEstudiantes(Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        List<Estudiante> estudiantes = estudianteService.listar();
        model.addAttribute("estudiantes", estudiantes);
        return "admin/studentlist";
    }

    @GetMapping("/misestudiantes")
    public String misEstudiantes(
            @RequestParam(required = false) Long idSeccion,
            Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        List<Seccion> secciones = seccionService.listar();
        model.addAttribute("secciones", secciones);

        if (idSeccion != null) {
            List<EstudianteDocenteDTO> estudiantes = estudianteService.listarPorSeccionDTO(idSeccion);

            model.addAttribute("estudiantes", estudiantes);
            model.addAttribute("seccionSeleccionada", idSeccion);
        }

        return "admin/studentasigned";
    }

    @GetMapping("/misestudiantes/pdf")
    public ResponseEntity<byte[]> generarPdfEstudiantes(@RequestParam Long idSeccion) {

        List<EstudianteDocenteDTO> estudiantes = estudianteService.listarPorSeccionDTO(idSeccion);
        Seccion seccion = seccionService.buscarPorId(idSeccion);

        estudiantes.sort(Comparator.comparing(EstudianteDocenteDTO::getNombreEstudiante));

        Context context = new Context();
        context.setVariable("estudiantes", estudiantes);
        context.setVariable("seccion", seccion);

        byte[] pdf = pdfService.generarPdf("admin/mis-estudiantes-pdf", context);

        String nombreArchivo = "lista_estudiantes_" + seccion.getAnio() + "-" + seccion.getLetra() + ".pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/listaestudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/admin/listaestudiantes";
    }

    private void guardarDesarrolloPsicomotor(Estudiante estudiante, String hito, Integer edad) {
        if (edad != null) {
            Desarrollo_psicomotor desarrollo = new Desarrollo_psicomotor(hito, edad, estudiante);
            desarrollo_psicomotorService.guardar(desarrollo);
        }
    }

    @GetMapping("/ficha-estudiante/{id}")
    public String mostrarFichaEstudiante(@PathVariable Long id, Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (estudiante == null) {
            return "redirect:/admin/listaestudiantes";
        }

        Expediente_clinico expediente = expediente_clinicoService.buscarPorEstudiante(estudiante);
        List<Desarrollo_psicomotor> desarrolloPsicomotor = desarrollo_psicomotorService.buscarPorEstudiante(estudiante);
        String alergias = alergiasService.obtenerAlergiasComoCadena(estudiante);
        String vacunas = vacunacionService.obtenerVacunasComoCadena(estudiante);
        Seccion seccion = seccionService.buscarPorId(estudiante.getMatricula().getSeccion().getId());

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("expediente", expediente);
        model.addAttribute("desarrolloPsicomotor", desarrolloPsicomotor);
        model.addAttribute("alergias", alergias);
        model.addAttribute("vacunas", vacunas);
        model.addAttribute("seccion", seccion);

        return "admin/estudiantedetail";
    }

    private String limpiarNombreArchivo(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\s+", "_");
    }

    @GetMapping("/ficha-estudiante/pdf/{id}")
    public ResponseEntity<byte[]> generarPdfEstudiante(@PathVariable Long id) {

        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (estudiante == null) {
            return ResponseEntity.notFound().build();
        }

        Seccion seccion = seccionService.buscarPorId(estudiante.getMatricula().getSeccion().getId());
        Expediente_clinico expediente = expediente_clinicoService.buscarPorEstudiante(estudiante);
        List<Desarrollo_psicomotor> desarrolloPsicomotor = desarrollo_psicomotorService.buscarPorEstudiante(estudiante);
        String alergias = alergiasService.obtenerAlergiasComoCadena(estudiante);
        String vacunas = vacunacionService.obtenerVacunasComoCadena(estudiante);

        Context context = new Context();
        context.setVariable("estudiante", estudiante);
        context.setVariable("seccion", seccion);
        context.setVariable("expediente", expediente);
        context.setVariable("desarrolloPsicomotor", desarrolloPsicomotor);
        context.setVariable("alergias", alergias);
        context.setVariable("vacunas", vacunas);

        byte[] pdfBytes = pdfService.generarPdf("admin/ficha-estudiante-pdf", context);

        String nombreArchivo = "Ficha_Estudiante_" +
                limpiarNombreArchivo(estudiante.getNombre() + "_" + estudiante.getApellido())
                + ".pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
