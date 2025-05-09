package Controllers;


import Models.Entities.Departamento;
import Repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    //Get all deparments
    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    //Get department by id
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Add new department
    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/empleados")
    public ResponseEntity<Empleado> agregarEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado nuevoEmpleado,
            @Autowired EmpleadoRepository empleadoRepository) {

        return departamentoRepository.findById(id).map(departamento -> {
            nuevoEmpleado.setDepartamento(departamento);
            Empleado guardado = empleadoRepository.save(nuevoEmpleado);
            return ResponseEntity.ok(guardado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/empleados/{empleadoId}/reasignar")
    public ResponseEntity<Empleado> reasignarEmpleado(
            @PathVariable Long id,
            @PathVariable Long empleadoId,
            @Autowired EmpleadoRepository empleadoRepository) {

        return departamentoRepository.findById(id).map(nuevoDepto -> {
            return empleadoRepository.findById(empleadoId).map(empleado -> {
                empleado.setDepartamento(nuevoDepto);
                Empleado actualizado = empleadoRepository.save(empleado);
                return ResponseEntity.ok(actualizado);
            }).orElse(ResponseEntity.notFound().build());
        }).orElse(ResponseEntity.notFound().build());
    }


}
