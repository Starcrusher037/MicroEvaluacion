package com.duoc.learningplatform.evaluacion_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.duoc.learningplatform.evaluacion_service.config.FeignConfig;

@FeignClient(name = "curso-service", url = "http://localhost:8082",configuration = FeignConfig.class)
public interface CourseClient {

    @GetMapping("/api/cursos/{id}/exists")
    Boolean existsCourseById(@PathVariable("id") Long id);

    @GetMapping("/api/cursos/{id}")
    Object getCourseById(@PathVariable("id") Long id);
}