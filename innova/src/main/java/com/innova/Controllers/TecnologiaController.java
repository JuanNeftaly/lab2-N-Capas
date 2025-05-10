package com.innova.Controllers;
//
import com.innova.Models.Entities.Tecnologia;
import com.innova.Repositories.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnologias")
public class TecnologiaController {

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @GetMapping
    public List<Tecnologia> getAllTecnologias() {
        return tecnologiaRepository.findAll();
    }

    @PostMapping
    public Tecnologia createTecnologia(@RequestBody Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologia);
    }
}
