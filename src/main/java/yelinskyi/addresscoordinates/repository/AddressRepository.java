package yelinskyi.addresscoordinates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yelinskyi.addresscoordinates.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
