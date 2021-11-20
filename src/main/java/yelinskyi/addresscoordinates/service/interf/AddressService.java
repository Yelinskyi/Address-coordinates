package yelinskyi.addresscoordinates.service.interf;

import yelinskyi.addresscoordinates.model.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);

    List<Address> findAll();
}
