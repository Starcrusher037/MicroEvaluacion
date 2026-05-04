package com.duoc.learningplatform.evaluacion_service.service;

import com.duoc.learningplatform.evaluacion_service.client.CourseClient;
import com.duoc.learningplatform.evaluacion_service.model.Actividad;
import com.duoc.learningplatform.evaluacion_service.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final CourseClient courseClient;

    public ActividadService(ActividadRepository actividadRepository,
                            CourseClient courseClient) {
        this.actividadRepository = actividadRepository;
        this.courseClient = courseClient;
    }

    // Crear actividad (tarea/examen)
    public Actividad crearActividad(Actividad actividad) {

        Boolean existeCurso = courseClient.existsCourseById(actividad.getCursoId());

        if (existeCurso == null || !existeCurso) {
            throw new RuntimeException("El curso no existe en course-service");
        }

        return actividadRepository.save(actividad);
    }

    // Listar todas las actividades
    public List<Actividad> listarActividades() {
        return actividadRepository.findAll();
    }

    // Buscar por ID
    public Actividad buscarPorId(Long id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
    }

    // Listar por curso
    public List<Actividad> listarPorCurso(Long cursoId) {
        return actividadRepository.findByCursoId(cursoId);
    }

    // Eliminar actividad
    public void eliminarActividad(Long id) {
        actividadRepository.deleteById(id);
    }
}