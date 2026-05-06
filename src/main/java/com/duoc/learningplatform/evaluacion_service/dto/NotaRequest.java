package com.duoc.learningplatform.evaluacion_service.dto;

public class NotaRequest {

    private Double nota;

    public NotaRequest() {
    }

    public NotaRequest(Double nota) {
        this.nota = nota;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}