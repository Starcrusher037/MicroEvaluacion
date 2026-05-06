package com.duoc.learningplatform.evaluacion_service.service;

import com.duoc.learningplatform.evaluacion_service.exception.NotFoundException;
import com.duoc.learningplatform.evaluacion_service.model.Actividad;
import com.duoc.learningplatform.evaluacion_service.model.Evaluacion;
import com.duoc.learningplatform.evaluacion_service.repository.ActividadRepository;
import com.duoc.learningplatform.evaluacion_service.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final ActividadRepository actividadRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository,
                             ActividadRepository actividadRepository) {
        this.evaluacionRepository = evaluacionRepository;
        this.actividadRepository = actividadRepository;
    }

    public Evaluacion crearEvaluacion(Evaluacion evaluacion) {

        Actividad actividad = actividadRepository.findById(evaluacion.getActividadId())
                .orElseThrow(() -> new NotFoundException("Actividad no existe"));

        if (evaluacion.getNota() != null &&
            evaluacion.getNota() > actividad.getPuntajeMaximo()) {
            throw new RuntimeException("La nota excede el puntaje máximo");
        }

        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evaluación no encontrada"));
    }

    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public List<Evaluacion> listarPorActividad(Long actividadId) {
        return evaluacionRepository.findByActividadId(actividadId);
    }

    public List<Evaluacion> listarPorAlumno(Long alumnoId) {
        return evaluacionRepository.findByAlumnoId(alumnoId);
    }


    public Evaluacion actualizarNota(Long id, Double nota) {

        Evaluacion eval = buscarPorId(id);
        eval.setNota(nota);

        return evaluacionRepository.save(eval);
    }

    public void eliminarEvaluacion(Long id) {

        if (!evaluacionRepository.existsById(id)) {
            throw new NotFoundException("Evaluación no encontrada");
        }

        evaluacionRepository.deleteById(id);
    }
}