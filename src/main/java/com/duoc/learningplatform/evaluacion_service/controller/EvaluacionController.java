package com.duoc.learningplatform.evaluacion_service.controller;

import com.duoc.learningplatform.evaluacion_service.dto.CrearEvaluacionRequest;
import com.duoc.learningplatform.evaluacion_service.dto.NotaRequest;
import com.duoc.learningplatform.evaluacion_service.model.Evaluacion;
import com.duoc.learningplatform.evaluacion_service.service.EvaluacionService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<Evaluacion> crearEvaluacion(
        @RequestBody @Valid CrearEvaluacionRequest request,
        Authentication authentication) {

        Long alumnoId = Long.parseLong(authentication.getName());

        Evaluacion evaluacion = evaluacionService.crearEvaluacion(
                request,
                alumnoId
        );


        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacion);
    }

    @GetMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<Evaluacion>> listar() {
        return ResponseEntity.ok(evaluacionService.listarEvaluaciones());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")
    public ResponseEntity<Evaluacion> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.buscarPorId(id));
    }

    @GetMapping("/actividad/{actividadId}")
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")
    public ResponseEntity<List<Evaluacion>> porActividad(
            @PathVariable Long actividadId) {

        return ResponseEntity.ok(
                evaluacionService.listarPorActividad(actividadId)
        );
    }

    @GetMapping("/mis-evaluaciones")
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<List<Evaluacion>> misEvaluaciones(
            Authentication authentication) {

        Long alumnoId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(
                evaluacionService.listarPorAlumno(alumnoId)
        );
    }

    @PutMapping("/{id}/nota")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Evaluacion> actualizarNota(
            @PathVariable Long id,
            @RequestBody @Valid NotaRequest request) {

        return ResponseEntity.ok(
                evaluacionService.actualizarNota(id, request.getNota())
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        evaluacionService.eliminarEvaluacion(id);

        return ResponseEntity.noContent().build();
    }
}