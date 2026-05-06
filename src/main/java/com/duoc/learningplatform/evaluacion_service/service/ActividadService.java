package com.duoc.learningplatform.evaluacion_service.service;

import com.duoc.learningplatform.evaluacion_service.client.CourseClient;
import com.duoc.learningplatform.evaluacion_service.exception.NotFoundException;
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

    public Actividad crearActividad(Actividad actividad) {

        Boolean existeCurso = courseClient.existsCourseById(actividad.getCursoId());

        if (existeCurso == null || !existeCurso) {
            throw new NotFoundException("El curso no existe");
        }

        return actividadRepository.save(actividad);
    }

    public List<Actividad> listarActividades() {
        return actividadRepository.findAll();
    }

    public Actividad buscarPorId(Long id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Actividad no encontrada"));
    }

    public List<Actividad> listarPorCurso(Long cursoId) {
        return actividadRepository.findByCursoId(cursoId);
    }

    public void eliminarActividad(Long id) {
        if (!actividadRepository.existsById(id)) {
            throw new NotFoundException("Actividad no encontrada");
        }
        actividadRepository.deleteById(id);
    }
}