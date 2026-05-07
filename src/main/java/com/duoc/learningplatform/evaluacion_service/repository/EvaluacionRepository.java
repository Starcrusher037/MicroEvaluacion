package com.duoc.learningplatform.evaluacion_service.repository;

import com.duoc.learningplatform.evaluacion_service.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    List<Evaluacion> findByActividadId(Long actividadId);

    List<Evaluacion> findByAlumnoId(Long alumnoId);

    boolean existsByActividadIdAndAlumnoId(
            Long actividadId,
            Long alumnoId
    );
}