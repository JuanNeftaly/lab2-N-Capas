package com.innova.Controllers;
///

import com.innova.Models.Entities.Evaluacion;
import com.innova.Services.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionService.getAllEvaluaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable Long id) {
        return evaluacionService.getEvaluacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Evaluacion>> getEvaluacionesByEmpleado(@PathVariable Long empleadoId) {
        return evaluacionService.getEvaluacionesByEmpleadoId(empleadoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{empleadoId}")
    public ResponseEntity<Evaluacion> crearEvaluacion(
            @PathVariable Long empleadoId,
            @RequestBody Evaluacion evaluacion) {

        return evaluacionService.createEvaluacion(empleadoId, evaluacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}