package com.duoc.learningplatform.evaluacion_service.controller;

import com.duoc.learningplatform.evaluacion_service.model.Evaluacion;
import com.duoc.learningplatform.evaluacion_service.service.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.ok(evaluacionService.crearEvaluacion(evaluacion));
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> listar() {
        return ResponseEntity.ok(evaluacionService.listarEvaluaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.buscarPorId(id));
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Evaluacion>> porActividad(@PathVariable Long actividadId) {
        return ResponseEntity.ok(evaluacionService.listarPorActividad(actividadId));
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Evaluacion>> porAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(evaluacionService.listarPorAlumno(alumnoId));
    }

    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<Evaluacion>> porProfesor(@PathVariable Long profesorId) {
        return ResponseEntity.ok(evaluacionService.listarPorProfesor(profesorId));
    }

    @PutMapping("/{id}/nota")
    public ResponseEntity<Evaluacion> actualizarNota(
            @PathVariable Long id,
            @RequestParam Double nota) {

        return ResponseEntity.ok(
                evaluacionService.actualizarNota(id, nota)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}