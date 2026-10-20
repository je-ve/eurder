package org.switchfully.eurder.controller.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.controller.dto.CreateCustomerDto;
import org.switchfully.eurder.controller.dto.UserDto;
import org.switchfully.eurder.domain.Role;
import org.switchfully.eurder.domain.User;

import java.util.Collection;

@Component
public class UserMapper {

    private AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public User mapToUser(CreateCustomerDto createCustomerDto) {
        return new User(createCustomerDto.getFirstName(),
                createCustomerDto.getLastName(),
                createCustomerDto.getEmail(),
                addressMapper.mapToAddress(createCustomerDto.getAddressDto()),
                createCustomerDto.getPhoneNumber(),
                Role.CUSTOMER);
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                addressMapper.mapToDto(user.getAddress()),
                user.getPhoneNumber(), user.getRole());
    }

    public Collection<UserDto> mapToUserDto(Collection<User> users) {
        return users.stream().map(this::mapToUserDto).toList();
    }

}
