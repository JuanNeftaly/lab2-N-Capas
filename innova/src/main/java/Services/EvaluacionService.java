package Services;

import Models.Entities.Empleado;
import Models.Entities.Evaluacion;
import Repositories.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> getEvaluacionesByEmpleado(Empleado empleado) {
        return evaluacionRepository.findAll().stream()
                .filter(e -> e.getEmpleado().equals(empleado))
                .toList();
    }

    public Evaluacion registrarEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }
}