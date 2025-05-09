package Controllers;

import Models.Entities.Empleado;
import Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        return empleadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setApellido(empleadoDetails.getApellido());
            empleado.setEmail(empleadoDetails.getEmail());
            empleado.setFechaIngreso(empleadoDetails.getFechaIngreso());
            empleado.setPuesto(empleadoDetails.getPuesto());
            empleado.setDepartamento(empleadoDetails.getDepartamento());
            return ResponseEntity.ok(empleadoRepository.save(empleado));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mentor/{mentorId}")
    public ResponseEntity<Empleado> asignarMentor(@PathVariable Long id, @PathVariable Long mentorId) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        Optional<Empleado> mentorOpt = empleadoRepository.findById(mentorId);

        if (empleadoOpt.isPresent() && mentorOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            Empleado mentor = mentorOpt.get();
            empleado.getMentores().add(mentor);
            return ResponseEntity.ok(empleadoRepository.save(empleado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

}
