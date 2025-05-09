package Controllers;

import Models.Entities.Evaluacion;
import Models.Entities.Empleado;
import Repositories.EvaluacionRepository;
import Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Obtener todas las evaluaciones
    @GetMapping
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    // Obtener una evaluación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable Long id) {
        return evaluacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todas las evaluaciones de un empleado
    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Evaluacion>> getEvaluacionesByEmpleado(@PathVariable Long empleadoId) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            List<Evaluacion> evaluaciones = evaluacionRepository.findAll()
                    .stream()
                    .filter(e -> e.getEmpleado().getId().equals(empleado.getId()))
                    .toList();
            return ResponseEntity.ok(evaluaciones);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una evaluación para un empleado
    @PostMapping("/{empleadoId}")
    public ResponseEntity<Evaluacion> crearEvaluacion(
            @PathVariable Long empleadoId,
            @RequestBody Evaluacion evaluacion) {

        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
        if (empleadoOpt.isPresent()) {
            evaluacion.setEmpleado(empleadoOpt.get());
            return ResponseEntity.ok(evaluacionRepository.save(evaluacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
