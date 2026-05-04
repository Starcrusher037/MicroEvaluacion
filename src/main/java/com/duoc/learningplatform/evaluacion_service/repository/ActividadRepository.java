package com.duoc.learningplatform.evaluacion_service.repository;

import com.duoc.learningplatform.evaluacion_service.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    // listar actividades por curso
    List<Actividad> findByCursoId(Long cursoId);
}