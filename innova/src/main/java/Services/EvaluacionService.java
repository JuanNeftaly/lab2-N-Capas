package Services;


import Models.Entities.Evaluacion;
import Models.Entities.Empleado;
import Repositories.EvaluacionRepository;
import Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Obtener todas las evaluaciones
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    // Obtener evaluación por ID
    public Optional<Evaluacion> getEvaluacionById(Long id) {
        return evaluacionRepository.findById(id);
    }

    // Obtener evaluaciones por empleado
    public Optional<List<Evaluacion>> getEvaluacionesByEmpleadoId(Long empleadoId) {
        return empleadoRepository.findById(empleadoId)
                .map(empleado -> evaluacionRepository.findAll().stream()
                        .filter(e -> e.getEmpleado().getId().equals(empleado.getId()))
                        .collect(Collectors.toList()));
    }

    // Crear evaluación para un empleado
    public Optional<Evaluacion> createEvaluacion(Long empleadoId, Evaluacion evaluacion) {
        return empleadoRepository.findById(empleadoId).map(empleado -> {
            evaluacion.setEmpleado(empleado);
            return evaluacionRepository.save(evaluacion);
        });
    }
}