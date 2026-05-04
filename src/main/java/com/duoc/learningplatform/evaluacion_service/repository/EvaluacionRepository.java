package com.duoc.learningplatform.evaluacion_service.repository;

import com.duoc.learningplatform.evaluacion_service.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    // evaluaciones por actividad
    List<Evaluacion> findByActividadId(Long actividadId);

    // evaluaciones por alumno
    List<Evaluacion> findByAlumnoId(Long alumnoId);

    // evaluaciones por profesor
    List<Evaluacion> findByProfesorId(Long profesorId);
}