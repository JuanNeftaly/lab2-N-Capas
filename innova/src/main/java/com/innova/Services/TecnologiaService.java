package com.innova.Services;
//

import com.innova.Models.Entities.Tecnologia;
import com.innova.Repositories.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnologiaService {
    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    public List<Tecnologia> getAllTecnologias() {
        return tecnologiaRepository.findAll();
    }

    public Tecnologia createTecnologia(Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologia);
    }
}
