package Services;

import Models.Entities.Empleado;
import Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    public void asignarMentor(Long empleadoId, Empleado mentor) {
        Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow();
        empleado.getMentores().add(mentor);
        empleadoRepository.save(empleado);
    }
}
