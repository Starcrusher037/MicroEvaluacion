package com.duoc.learningplatform.evaluacion_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "evaluacion")
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long actividadId;

    @NotNull
    private Long alumnoId;

    @PositiveOrZero
    private Double nota;

    private String comentarioProfesor;

    @NotNull
    private LocalDate fechaEntrega;

    private LocalDate fechaEvaluacion;
}