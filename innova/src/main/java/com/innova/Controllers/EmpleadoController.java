package com.innova.Controllers;
///

import com.innova.Models.Entities.Empleado;
import com.innova.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        return empleadoService.getEmpleadoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.createEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        return empleadoService.updateEmpleado(id, empleadoDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        if (empleadoService.deleteEmpleado(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mentor/{mentorId}")
    public ResponseEntity<Empleado> asignarMentor(@PathVariable Long id, @PathVariable Long mentorId) {
        return empleadoService.asignarMentor(id, mentorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}