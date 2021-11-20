package yelinskyi.addresscoordinates.service;

import java.util.List;
import org.springframework.stereotype.Service;
import yelinskyi.addresscoordinates.model.Address;
import yelinskyi.addresscoordinates.repository.AddressRepository;
import yelinskyi.addresscoordinates.service.interf.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
