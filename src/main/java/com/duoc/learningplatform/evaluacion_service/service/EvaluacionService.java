package com.duoc.learningplatform.evaluacion_service.service;

import com.duoc.learningplatform.evaluacion_service.client.CourseClient;
import com.duoc.learningplatform.evaluacion_service.model.Evaluacion;
import com.duoc.learningplatform.evaluacion_service.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final CourseClient courseClient;

    public EvaluacionService(EvaluacionRepository evaluacionRepository,
                             CourseClient courseClient) {
        this.evaluacionRepository = evaluacionRepository;
        this.courseClient = courseClient;
    }

    // Crear evaluación (calificación del profesor)
    public Evaluacion crearEvaluacion(Evaluacion evaluacion) {

        // 🔗 VALIDACIÓN (opcional pero recomendable)
        Boolean existeCurso = courseClient.existsCourseById(evaluacion.getActividadId());

        if (existeCurso == null || !existeCurso) {
            throw new RuntimeException("El curso asociado no existe");
        }

        return evaluacionRepository.save(evaluacion);
    }

    // Buscar por ID
    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }

    // Listar todas las evaluaciones
    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    // Evaluaciones por actividad
    public List<Evaluacion> listarPorActividad(Long actividadId) {
        return evaluacionRepository.findByActividadId(actividadId);
    }

    // Evaluaciones por alumno
    public List<Evaluacion> listarPorAlumno(Long alumnoId) {
        return evaluacionRepository.findByAlumnoId(alumnoId);
    }

    // Evaluaciones por profesor
    public List<Evaluacion> listarPorProfesor(Long profesorId) {
        return evaluacionRepository.findByProfesorId(profesorId);
    }

    // Actualizar nota (corrección del profesor)
    public Evaluacion actualizarNota(Long id, Double nuevaNota) {
        Evaluacion eval = buscarPorId(id);
        eval.setNota(nuevaNota);
        return evaluacionRepository.save(eval);
    }

    // Eliminar evaluación
    public void eliminarEvaluacion(Long id) {
        evaluacionRepository.deleteById(id);
    }
}