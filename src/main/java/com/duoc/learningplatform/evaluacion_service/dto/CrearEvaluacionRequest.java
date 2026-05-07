package com.duoc.learningplatform.evaluacion_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearEvaluacionRequest {

    @NotNull
    private Long actividadId;
}