package com.proyecto.cebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cebe.model.Apoderado;
import com.proyecto.cebe.model.Parentesco;
import com.proyecto.cebe.repo.ApoderadoRepository;
import com.proyecto.cebe.repo.ParentescoRepository;

@Service
public class ParentescoServiceImpl implements ParentescoService {

    @Autowired
    private ParentescoRepository parentescoRepository;

    @Autowired
    private ApoderadoRepository apoderadoRepository;


    @Override
    public Parentesco guardar(Parentesco parentesco) {
        return parentescoRepository.save(parentesco);
    }

    @Override
    public List<Parentesco> listar() {
        return parentescoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        Parentesco p = buscarPorId(id);
        if (p == null)
            return;

        Apoderado apo = p.getApoderado();
        parentescoRepository.deleteById(id);
        int numRelacion = parentescoRepository.countByApoderadoId(apo.getId());

        if (numRelacion == 0) {
            apoderadoRepository.deleteById(apo.getId());
        }
    }

    @Override
    public Parentesco buscarPorId(Long id) {
        return parentescoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Parentesco> listarPorEstudiante(Long estudianteId) {
        return parentescoRepository.findByEstudianteId(estudianteId);
    }

    @Override
    public Parentesco asignarPrincipal(Long parentescoId) {
        Parentesco nuevoPrincipal = buscarPorId(parentescoId);

        if (nuevoPrincipal == null) {
            return null;
        }

        Parentesco actual = parentescoRepository
                .findByEstudianteIdAndEsPrincipalTrue(nuevoPrincipal.getEstudiante().getId());

        if (actual != null && !actual.getId().equals(nuevoPrincipal.getId())) {
            actual.setEsPrincipal(false);
            parentescoRepository.save(actual);
        }

        nuevoPrincipal.setEsPrincipal(true);
        return parentescoRepository.save(nuevoPrincipal);
    }

    @Override
    public boolean existeRelacion(Long estudianteId, Long apoderadoId) {
        return parentescoRepository.existsByEstudianteIdAndApoderadoId(estudianteId, apoderadoId);
    }

}
