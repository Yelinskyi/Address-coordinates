package yelinskyi.addresscoordinates.service.mappers;

import org.springframework.stereotype.Service;
import yelinskyi.addresscoordinates.dto.AddressResponseDto;
import yelinskyi.addresscoordinates.dto.CoordinatesResponseDto;
import yelinskyi.addresscoordinates.model.Address;
import yelinskyi.addresscoordinates.model.Coordinates;

@Service
public class ApiResponseMapper {
   public Coordinates toCoordinates(CoordinatesResponseDto apiResponseDto) {
       Coordinates coordinates = new Coordinates();
       coordinates.setLat(apiResponseDto.getLat());
       coordinates.setLon(apiResponseDto.getLon());
       return coordinates;
    }

    public CoordinatesResponseDto toCoordinatesResponseDto(Coordinates coordinates) {
        CoordinatesResponseDto coordinatesResponseDto = new CoordinatesResponseDto();
        coordinatesResponseDto.setLat(coordinates.getLat());
        coordinatesResponseDto.setLon(coordinates.getLon());
        return coordinatesResponseDto;
    }

    public AddressResponseDto toAddressResponseDto(Address address) {
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        addressResponseDto.setAddress(address.getAddress());
        return addressResponseDto;
    }
}
