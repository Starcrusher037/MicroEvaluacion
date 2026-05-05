package com.duoc.learningplatform.evaluacion_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "actividad")
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // referencia al curso (microservicio externo)
    @NotNull
    private Long cursoId;

    @NotBlank
    private String nombre; // Ej:"Prueba 1"

    private String descripcion;

    @NotNull
    private Double puntajeMaximo;

    @NotNull
    private LocalDate fechaEntrega;
}