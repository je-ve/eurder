package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.AddressDto;
import org.switchfully.eurder.domain.Address;

@Component
public class AddressMapper {

    public AddressDto mapToDto(Address address) {
        return new AddressDto(address.getStreetName(), address.getHouseNumber(), address.getPostalCode(), address.getCity());
    }

    public Address mapToAddress(AddressDto addressDto) {
        return new Address(addressDto.getStreetName(), addressDto.getHouseNumber(), addressDto.getPostalCode(), addressDto.getCity());
    }
}
