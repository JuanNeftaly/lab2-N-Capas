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
    private DepartamentoService departamentoService;


    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoService.getAllDepartamentos();
    }

    //Get department by id
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.createDepartamento(departamento);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        if (departamentoService.deleteDepartamento(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build(); // o notFound, según el caso
        }
    }


    @PostMapping("/{id}/empleados")
    public ResponseEntity<Empleado> agregarEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado nuevoEmpleado) {

        return departamentoService.agregarEmpleado(id, nuevoEmpleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/empleados/{empleadoId}/reasignar")
    public ResponseEntity<Empleado> reasignarEmpleado(
            @PathVariable Long id,
            @PathVariable Long empleadoId) {

        return departamentoService.reasignarEmpleado(id, empleadoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
