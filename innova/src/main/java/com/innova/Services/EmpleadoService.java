package com.innova.Services;
/// /

import com.innova.Models.Entities.Empleado;
import com.innova.Repositories.EmpleadoRepository;
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

    public Empleado createEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> updateEmpleado(Long id, Empleado empleadoDetails) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setApellido(empleadoDetails.getApellido());
            empleado.setEmail(empleadoDetails.getEmail());
            empleado.setFechaIngreso(empleadoDetails.getFechaIngreso());
            empleado.setPuesto(empleadoDetails.getPuesto());
            empleado.setDepartamento(empleadoDetails.getDepartamento());
            return empleadoRepository.save(empleado);
        });
    }

    public boolean deleteEmpleado(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Empleado> asignarMentor(Long empleadoId, Long mentorId) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
        Optional<Empleado> mentorOpt = empleadoRepository.findById(mentorId);

        if (empleadoOpt.isPresent() && mentorOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            Empleado mentor = mentorOpt.get();
            empleado.getMentores().add(mentor);
            empleadoRepository.save(empleado);
            return Optional.of(empleado);
        }
        return Optional.empty();
    }
}