package com.proyecto.cebe.controller.admin;

import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

import com.proyecto.cebe.model.Apoderado;
import com.proyecto.cebe.model.Estudiante;
import com.proyecto.cebe.model.Parentesco;
import com.proyecto.cebe.model.Usuario;
import com.proyecto.cebe.service.ApoderadoService;
import com.proyecto.cebe.service.EstudianteService;
import com.proyecto.cebe.service.PDFService;
import com.proyecto.cebe.service.ParentescoService;
import com.proyecto.cebe.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class ApoderadoController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private ApoderadoService apoderadoService;

    @Autowired
    private ParentescoService parentescoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PDFService pdfService;

    @GetMapping("/apoderados")
    public String apoderados(
            @RequestParam(required = false) Long idEstudiante,
            Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        model.addAttribute("estudiantes", estudianteService.listar());

        if (idEstudiante != null) {
            model.addAttribute("estudianteSeleccionado", estudianteService.buscarPorId(idEstudiante));
            model.addAttribute("parentescos", parentescoService.listarPorEstudiante(idEstudiante));
        } else {
            model.addAttribute("parentescos", null);
            model.addAttribute("estudianteSeleccionado", null);
        }

        model.addAttribute("apoderado", new Apoderado());
        model.addAttribute("relaciones",
                List.of("Padre", "Madre", "Tío", "Tía", "Hermano", "Hermana", "Tutor", "Otro"));

        return "admin/apoderado";
    }

    @GetMapping("/listar/{idEstudiante}")
    @ResponseBody
    public List<Parentesco> listarPorEstudiante(@PathVariable Long idEstudiante, Model model) {
        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);
        return parentescoService.listarPorEstudiante(idEstudiante);
    }

    @PostMapping("/registrar")
    public String registrarApoderado(
            @RequestParam Long idEstudiante,
            @ModelAttribute Apoderado apoderado,
            @RequestParam String relacion,
            @RequestParam(defaultValue = "false") boolean esPrincipal) {
        Estudiante estudiante = estudianteService.buscarPorId(idEstudiante);
        Apoderado guardado = apoderadoService.guardar(apoderado);

        if (parentescoService.existeRelacion(idEstudiante, guardado.getId())) {
            return "redirect:/admin/apoderados?idEstudiante=" + idEstudiante + "&duplicado=true";
        }

        Parentesco p = new Parentesco(estudiante, guardado, relacion, esPrincipal);
        parentescoService.guardar(p);

        if (esPrincipal) {
            parentescoService.asignarPrincipal(p.getId());
        }

        return "redirect:/admin/apoderados?idEstudiante=" + idEstudiante;
    }

    @PostMapping("/principal/{idParentesco}")
    public String marcarPrincipal(@PathVariable Long idParentesco, @RequestParam Long idEstudiante) {
        parentescoService.asignarPrincipal(idParentesco);
        return "redirect:/admin/apoderados?idEstudiante=" + idEstudiante;
    }

    @PostMapping("/apoderados/eliminar/{idParentesco}")
    public String eliminarParentesco(@PathVariable Long idParentesco,
            @RequestParam Long idEstudiante) {
        parentescoService.eliminar(idParentesco);
        return "redirect:/admin/apoderados?idEstudiante=" + idEstudiante;
    }

    @GetMapping("/apoderados/editar/{idParentesco}")
    public String editarApoderado(
            @PathVariable Long idParentesco,
            Model model) {

        Usuario usuario = usuarioService.buscarPorId((long) 1);
        model.addAttribute("usuario", usuario);

        Parentesco parentesco = parentescoService.buscarPorId(idParentesco);
        Apoderado apoderado = parentesco.getApoderado();
        Estudiante estudiante = parentesco.getEstudiante();

        model.addAttribute("estudiantes", estudianteService.listar());
        model.addAttribute("estudianteSeleccionado", estudiante);
        model.addAttribute("parentescos", parentescoService.listarPorEstudiante(estudiante.getId()));

        model.addAttribute("apoderado", apoderado);
        model.addAttribute("relacionSeleccionada", parentesco.getRelacion());
        model.addAttribute("esPrincipal", parentesco.isEsPrincipal());

        model.addAttribute("modoEdicion", true);

        model.addAttribute("relaciones",
                List.of("Padre", "Madre", "Tío", "Tía", "Hermano", "Hermana", "Tutor", "Otro"));

        return "admin/apoderado";
    }

    @GetMapping("/apoderados/editar")
    public String editarApoderado(
            @RequestParam Long idParentesco,
            @RequestParam Long idEstudiante,
            Model model) {

        Parentesco parentesco = parentescoService.buscarPorId(idParentesco);
        Apoderado apoderado = parentesco.getApoderado();

        model.addAttribute("idParentesco", parentesco.getId());

        model.addAttribute("estudiantes", estudianteService.listar());
        model.addAttribute("estudianteSeleccionado", estudianteService.buscarPorId(idEstudiante));
        model.addAttribute("parentescos", parentescoService.listarPorEstudiante(idEstudiante));

        model.addAttribute("apoderado", apoderado);
        model.addAttribute("modoEdicion", true);

        model.addAttribute("relaciones",
                List.of("Padre", "Madre", "Tío", "Tía", "Hermano", "Hermana", "Tutor", "Otro"));

        model.addAttribute("relacionSeleccionada", parentesco.getRelacion());
        model.addAttribute("esPrincipal", parentesco.isEsPrincipal());

        return "admin/apoderado";
    }

    @PostMapping("/apoderados/actualizar")
    public String actualizarApoderado(
            @RequestParam Long idParentesco,
            @ModelAttribute Apoderado apoderado,
            @RequestParam String relacion,
            @RequestParam(defaultValue = "false") boolean esPrincipal,
            @RequestParam Long idEstudiante, Model model) {

                Usuario usuario = usuarioService.buscarPorId((long) 1);
                model.addAttribute("usuario", usuario);

        // Actualizamos datos del apoderado
        Apoderado existente = apoderadoService.buscarPorId(apoderado.getId());
        existente.setNombre(apoderado.getNombre());
        existente.setApellidos(apoderado.getApellidos());
        existente.setDni(apoderado.getDni());
        existente.setFechaNacimiento(apoderado.getFechaNacimiento());
        existente.setSexo(apoderado.getSexo());
        existente.setDireccion(apoderado.getDireccion());
        existente.setLugarNacimiento(apoderado.getLugarNacimiento());
        existente.setDepartamento(apoderado.getDepartamento());
        existente.setProvincia(apoderado.getProvincia());
        existente.setDistrito(apoderado.getDistrito());
        existente.setLenguaMaterna(apoderado.getLenguaMaterna());
        existente.setSegundaLengua(apoderado.getSegundaLengua());
        existente.setReligion(apoderado.getReligion());
        existente.setViveConEstudiante(apoderado.getViveConEstudiante());
        existente.setGradoInstruccion(apoderado.getGradoInstruccion());
        existente.setOcupacion(apoderado.getOcupacion());
        existente.setTelefono(apoderado.getTelefono());
        existente.setCorreo(apoderado.getCorreo());
        existente.setEstaVivo(apoderado.getEstaVivo());
        apoderadoService.guardar(existente);

        Parentesco p = parentescoService.buscarPorId(idParentesco);
        p.setRelacion(relacion);
        p.setEsPrincipal(esPrincipal);
        parentescoService.guardar(p);

        if (esPrincipal) {
            parentescoService.asignarPrincipal(p.getId());
        }

        return "redirect:/admin/apoderados?idEstudiante=" + idEstudiante;
    }

    private String limpiarNombreArchivo(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\s+", "_");
    }

    @GetMapping("/apoderados/pdf")
    public ResponseEntity<byte[]> generarPdfApoderados(
            @RequestParam Long idEstudiante) {

        Estudiante estudiante = estudianteService.buscarPorId(idEstudiante);
        List<Parentesco> parentescos = parentescoService.listarPorEstudiante(idEstudiante);

        Context context = new Context();
        context.setVariable("estudiante", estudiante);
        context.setVariable("parentescos", parentescos);

        byte[] pdf = pdfService.generarPdf("admin/apoderado-pdf", context);

        String nombreArchivo = "apoderados_" +
                limpiarNombreArchivo(
                        estudiante.getNombre() + "_" + estudiante.getApellido())
                + ".pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
