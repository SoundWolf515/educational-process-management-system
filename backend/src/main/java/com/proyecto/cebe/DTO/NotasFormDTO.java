package com.proyecto.cebe.DTO;

import java.util.List;

public class NotasFormDTO {

    private Long matriculaId;
    private List<NotaFormDTO> notas;
    public Long getMatriculaId() {
        return matriculaId;
    }
    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }
    public List<NotaFormDTO> getNotas() {
        return notas;
    }
    public void setNotas(List<NotaFormDTO> notas) {
        this.notas = notas;
    }

    
}
