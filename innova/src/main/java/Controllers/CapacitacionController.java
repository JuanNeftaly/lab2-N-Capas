package Controllers;

import Models.Entities.Capacitacion;
import Repositories.CapacitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capacitaciones")
public class CapacitacionController {

    @Autowired
    private CapacitacionRepository capacitacionRepository;

    @GetMapping
    public List<Capacitacion> getAllCapacitaciones() {
        return capacitacionRepository.findAll();
    }

    @PostMapping
    public Capacitacion createCapacitacion(@RequestBody Capacitacion capacitacion) {
        return capacitacionRepository.save(capacitacion);
    }
}