package com.innova.Services;
/// /

import com.innova.Models.Entities.Capacitacion;
import com.innova.Repositories.CapacitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapacitacionService {
    @Autowired
    private CapacitacionRepository capacitacionRepository;

    public List<Capacitacion> getAllCapacitaciones() {
        return capacitacionRepository.findAll();
    }

    public Capacitacion createCapacitacion(Capacitacion capacitacion) {
        return capacitacionRepository.save(capacitacion);
    }
}
