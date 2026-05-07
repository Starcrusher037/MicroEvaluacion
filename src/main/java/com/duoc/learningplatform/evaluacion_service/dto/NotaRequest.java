package com.duoc.learningplatform.evaluacion_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotaRequest {

    @NotNull
    @Min(0)
    @Max(100)
    private Double nota;
}