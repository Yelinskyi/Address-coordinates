package yelinskyi.addresscoordinates.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yelinskyi.addresscoordinates.dto.AddressResponseDto;
import yelinskyi.addresscoordinates.dto.CoordinatesResponseDto;
import yelinskyi.addresscoordinates.model.Address;
import yelinskyi.addresscoordinates.model.Coordinates;
import yelinskyi.addresscoordinates.service.interf.AddressService;
import yelinskyi.addresscoordinates.service.interf.CoordinatesService;
import yelinskyi.addresscoordinates.service.HttpClient;
import yelinskyi.addresscoordinates.service.mappers.ApiResponseMapper;

@RestController
public class AddressController {
    private final static String START_URL = "https://nominatim.openstreetmap.org/?addressdetails=1&q=";
    private final static String END_URL = "&format=json&limit=1";
    private final AddressService addressService;
    private final ApiResponseMapper apiResponseMapper;
    public final CoordinatesService coordinatesService;

    public AddressController(AddressService addressService,
                             ApiResponseMapper apiResponseMapper,
                             CoordinatesService coordinatesService) {
        this.addressService = addressService;
        this.apiResponseMapper = apiResponseMapper;
        this.coordinatesService = coordinatesService;
    }

    @GetMapping("/address-coordinates")
    public CoordinatesResponseDto getResponse(
            @RequestParam String country,
            @RequestParam String city,
            @RequestParam String street,
            @RequestParam String numberOfHouse) {
        Address address = new Address();
        address.setAddress(country + ", " + city + ", " + street + " " + numberOfHouse);
        HttpClient httpClient = new HttpClient();
        String forSearch = country + ",+" + city + ",+" + street + ",+" + numberOfHouse;
        CoordinatesResponseDto apiResponseDto = httpClient.get(START_URL + forSearch + END_URL);
        Coordinates coordinates = apiResponseMapper.toCoordinates(apiResponseDto);
        Optional<Coordinates> byCoordinates = coordinatesService.findByCoordinates(coordinates);
        Coordinates coordinatesFromDB;
        if (byCoordinates.isPresent()) {
            coordinatesFromDB = byCoordinates.get();
        } else {
            coordinatesFromDB = coordinatesService.save(coordinates);
        }
        address.setCoordinates(coordinatesFromDB);
        addressService.save(address);
        return apiResponseMapper.toCoordinatesResponseDto(address.getCoordinates());
    }

    @GetMapping("/address")
    public List<AddressResponseDto> findAllAddress() {
        return addressService.findAll()
                .stream()
                .map(apiResponseMapper::toAddressResponseDto)
                .collect(Collectors.toList());
    }
}
