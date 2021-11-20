package yelinskyi.addresscoordinates.service.interf;

import java.util.Optional;
import yelinskyi.addresscoordinates.model.Coordinates;

public interface CoordinatesService {
    Coordinates save(Coordinates coordinates);

    Optional<Coordinates> findByCoordinates(Coordinates currentCoordinates);
}
