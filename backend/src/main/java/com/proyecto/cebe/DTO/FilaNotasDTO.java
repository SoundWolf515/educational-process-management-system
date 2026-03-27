package com.proyecto.cebe.DTO;

import com.proyecto.cebe.model.Curso;

public class FilaNotasDTO {

    private Curso curso;
    private Double notaSem1;
    private Double notaSem2;
    private Double promedio;

    public FilaNotasDTO(){

    }

    public FilaNotasDTO(Curso curso, Double notaSem1, Double notaSem2) {
        this.curso = curso;
        this.notaSem1 = notaSem1;
        this.notaSem2 = notaSem2;
        
        if (notaSem1 != null && notaSem2 != null) {
            this.promedio = (notaSem1 + notaSem2) / 2;
        } else {
            this.promedio = null;
        }

    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Double getNotaSem1() {
        return notaSem1;
    }

    public void setNotaSem1(Double notaSem1) {
        this.notaSem1 = notaSem1;
    }

    public Double getNotaSem2() {
        return notaSem2;
    }

    public void setNotaSem2(Double notaSem2) {
        this.notaSem2 = notaSem2;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    
}
