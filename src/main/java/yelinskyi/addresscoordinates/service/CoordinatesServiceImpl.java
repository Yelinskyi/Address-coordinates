package yelinskyi.addresscoordinates.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import yelinskyi.addresscoordinates.model.Coordinates;
import yelinskyi.addresscoordinates.repository.CoordinatesRepository;
import yelinskyi.addresscoordinates.service.interf.CoordinatesService;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    public CoordinatesServiceImpl(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public Coordinates save(Coordinates coordinates) {
        return coordinatesRepository.save(coordinates);
    }

    @Override
    public Optional<Coordinates> findByCoordinates(Coordinates currentCoordinates) {
        return coordinatesRepository.findCoordinatesByLonAndLat(
                currentCoordinates.getLon(), currentCoordinates.getLat());
    }
}
