package com.proyecto.cebe.controller.admin;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.proyecto.cebe.DTO.FilaNotasDTO;
import com.proyecto.cebe.DTO.NotaFormDTO;
import com.proyecto.cebe.DTO.NotasFormDTO;
import com.proyecto.cebe.model.Curso;
import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Matricula;
import com.proyecto.cebe.model.Seccion;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.CursoService;
import com.proyecto.cebe.service.EstudianteService;
import com.proyecto.cebe.service.MatriculaService;
import com.proyecto.cebe.service.NotasService;
import com.proyecto.cebe.service.PDFService;
import com.proyecto.cebe.service.SeccionService;
import com.proyecto.cebe.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class NotasController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private NotasService notasService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PDFService pdfService;

    @GetMapping("/notas")
    public String notas(
            @RequestParam(required = false) Long idSeccion,
            @RequestParam(required = false) Long idEstudiante,
            Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        model.addAttribute("secciones", seccionService.listar());

        if (idSeccion != null) {

            Seccion seccion = seccionService.buscarPorId(idSeccion);
            model.addAttribute("seccionSeleccionada", seccion);

            List<Matricula> matriculas = matriculaService.obtenerPorSeccion(idSeccion);

            List<Estudiante> estudiantes = matriculas.stream()
                    .map(Matricula::getEstudiante)
                    .toList();

            model.addAttribute("estudiantes", estudiantes);

            if (idEstudiante != null) {
                Estudiante estudiante = estudianteService.buscarPorId(idEstudiante);
                model.addAttribute("estudianteSeleccionado", estudiante);

                Matricula matricula = estudiante.getMatricula();

                List<Curso> cursos = cursoService.listar();

                List<FilaNotasDTO> filas = new ArrayList<>();

                for (Curso curso : cursos) {

                    Double n1 = notasService.obtenerNota(
                            matricula.getId(),
                            curso.getId(),
                            1);

                    Double n2 = notasService.obtenerNota(
                            matricula.getId(),
                            curso.getId(),
                            2);

                    filas.add(new FilaNotasDTO(curso, n1, n2));
                }

                model.addAttribute("filasNotas", filas);
            }
        }

        return "admin/notas";
    }

    @GetMapping("/ver")
    public String verNotas(@RequestParam Long matriculaId, Model model) {

        model.addAttribute("matriculaId", matriculaId);

        return "admin/notas";
    }

    @PostMapping("/guardar")
    public String guardarNotas() {

        return "redirect:/admin/notas";
    }

    @GetMapping("/notasedit")
    public String notasedit(@RequestParam Long matriculaId, Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        Matricula matricula = matriculaService.buscarPorId(matriculaId);
        Estudiante estudiante = matricula.getEstudiante();

        model.addAttribute("matricula", matricula);
        model.addAttribute("estudiante", estudiante);

        List<Curso> cursos = cursoService.listar();
        List<FilaNotasDTO> filas = new ArrayList<>();

        for (Curso curso : cursos) {

            Double n1 = notasService.obtenerNota(
                    matriculaId, curso.getId(), 1);

            Double n2 = notasService.obtenerNota(
                    matriculaId, curso.getId(), 2);

            filas.add(new FilaNotasDTO(curso, n1, n2));
        }

        model.addAttribute("filasNotas", filas);

        return "admin/notasedit";
    }

    @PostMapping("/notas/guardar")
    public String guardarNotas(NotasFormDTO form) {

        Matricula matricula = matriculaService.buscarPorId(form.getMatriculaId());

        for (NotaFormDTO n : form.getNotas()) {

            Curso curso = cursoService.buscarPorId(n.getCursoId());

            notasService.guardarONueva(matricula, curso, 1, n.getNotaSem1());
            notasService.guardarONueva(matricula, curso, 2, n.getNotaSem2());

        }

        return "redirect:/admin/notas?idSeccion="
                + matricula.getSeccion().getId()
                + "&idEstudiante="
                + matricula.getEstudiante().getId();
    }

    private String limpiarNombreArchivo(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\s+", "_");
    }

    @GetMapping("/notas/pdf")
    public ResponseEntity<byte[]> generarPdfNotas(
            @RequestParam Long matriculaId) {
        Matricula matricula = matriculaService.buscarPorId(matriculaId);
        Estudiante estudiante = matricula.getEstudiante();

        List<Curso> cursos = cursoService.listar();
        List<FilaNotasDTO> filas = new ArrayList<>();

        String nombreEstudiante = limpiarNombreArchivo(estudiante.getNombre() + "_" + estudiante.getApellido());

        String nombreArchivo = "reporte_notas_" + nombreEstudiante + ".pdf";

        for (Curso curso : cursos) {
            Double n1 = notasService.obtenerNota(matriculaId, curso.getId(), 1);
            Double n2 = notasService.obtenerNota(matriculaId, curso.getId(), 2);
            filas.add(new FilaNotasDTO(curso, n1, n2));
        }

        Context context = new Context();
        context.setVariable("estudiante", estudiante);
        context.setVariable("filasNotas", filas);

        byte[] pdf = pdfService.generarPdf("admin/notas-pdf", context);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
