package com.innova.Services;
/// /

import com.innova.Models.Entities.Departamento;
import com.innova.Models.Entities.Empleado;
import com.innova.Repositories.DepartamentoRepository;
import com.innova.Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> getDepartamentoById(Long id) {
        return departamentoRepository.findById(id);
    }

    public Departamento createDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public boolean deleteDepartamento(Long id) {
        Optional<Departamento> deptoOpt = departamentoRepository.findById(id);
        if (deptoOpt.isPresent() && deptoOpt.get().getEmpleados().isEmpty()) {
            departamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Empleado> agregarEmpleado(Long deptoId, Empleado nuevoEmpleado) {
        return departamentoRepository.findById(deptoId).map(departamento -> {
            nuevoEmpleado.setDepartamento(departamento);
            return empleadoRepository.save(nuevoEmpleado);
        });
    }

    public Optional<Empleado> reasignarEmpleado(Long deptoId, Long empleadoId) {
        Optional<Departamento> nuevoDeptoOpt = departamentoRepository.findById(deptoId);
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);

        if (nuevoDeptoOpt.isPresent() && empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            empleado.setDepartamento(nuevoDeptoOpt.get());
            return Optional.of(empleadoRepository.save(empleado));
        }
        return Optional.empty();
    }
}