package com.duoc.learningplatform.evaluacion_service.controller;

import com.duoc.learningplatform.evaluacion_service.model.Actividad;
import com.duoc.learningplatform.evaluacion_service.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Actividad> crear(@RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.crearActividad(actividad));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")
    public ResponseEntity<List<Actividad>> listar() {
        return ResponseEntity.ok(actividadService.listarActividades());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")
    public ResponseEntity<Actividad> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(actividadService.buscarPorId(id));
    }

    @GetMapping("/curso/{cursoId}")
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")
    public ResponseEntity<List<Actividad>> porCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(actividadService.listarPorCurso(cursoId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        actividadService.eliminarActividad(id);
        return ResponseEntity.noContent().build();
    }
}