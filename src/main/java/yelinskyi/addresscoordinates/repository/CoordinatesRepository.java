package yelinskyi.addresscoordinates.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yelinskyi.addresscoordinates.model.Coordinates;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {

     @Query("SELECT c FROM Coordinates c WHERE c.lon = ?1 and c.lat = ?2")
     Optional <Coordinates> findCoordinatesByLonAndLat(String lon, String lat);
}
