package yelinskyi.addresscoordinates.service.interf;

import yelinskyi.addresscoordinates.model.Coordinates;

public interface CoordinatesService {
    Coordinates save(Coordinates coordinates);

    Coordinates findByCoordinates(Coordinates currentCoordinates);
}
