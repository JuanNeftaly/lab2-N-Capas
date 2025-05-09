package Services;
//
import Models.Entities.Area;
import Repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<Area> getAreaById(Long id) {
        return areaRepository.findById(id);
    }

    public Area createArea(Area area) {
        return areaRepository.save(area);
    }
}
