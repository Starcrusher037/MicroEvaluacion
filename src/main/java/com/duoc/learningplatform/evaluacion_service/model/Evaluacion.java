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

    // referencia a la actividad (tarea/examen)
    @NotNull
    private Long actividadId;

    // alumno evaluado (viene de auth-service)
    @NotNull
    private Long alumnoId;

    // profesor que evalúa 
    @NotNull
    private Long profesorId;

    @PositiveOrZero
    private Double nota;

    private String comentarioProfesor;

    @NotNull
    private LocalDate fechaEvaluacion;
}