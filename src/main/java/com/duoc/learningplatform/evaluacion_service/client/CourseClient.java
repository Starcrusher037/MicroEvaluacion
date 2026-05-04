package com.duoc.learningplatform.evaluacion_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "course-service", url = "http://localhost:8082")
public interface CourseClient {

    @GetMapping("/cursos/{id}/exists")
    Boolean existsCourseById(@PathVariable("id") Long id);

    @GetMapping("/cursos/{id}")
    Object getCourseById(@PathVariable("id") Long id);
}